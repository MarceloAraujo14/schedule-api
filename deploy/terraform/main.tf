resource "aws_vpc" "cap_vpc_1" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    "Name" = "cap_vpc_1"
  }
}


resource "aws_subnet" "cap_subnet_pub_1a" {
  vpc_id                  = aws_vpc.cap_vpc_1.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    "Name" = "cap_subnet_pub_1a"
  }

}

resource "aws_internet_gateway" "cap_igw_1a" {
  vpc_id = aws_vpc.cap_vpc_1.id

  tags = {
    "Name" = "cap_igw_1a"
  }
}

resource "aws_route_table" "cap_rtb_pub" {
  vpc_id = aws_vpc.cap_vpc_1.id

  tags = {
    "Name" = "cap_rtb_pub"
  }
}

resource "aws_route" "cap_default_rtb" {
  route_table_id         = aws_route_table.cap_rtb_pub.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.cap_igw_1a.id
}

resource "aws_route_table_association" "cap_rtba_pub_1a" {
  route_table_id = aws_route_table.cap_rtb_pub.id
  subnet_id      = aws_subnet.cap_subnet_pub_1a.id
}

resource "aws_security_group" "cap_sg" {
  name        = "cap_sg"
  description = "cap security group"
  vpc_id      = aws_vpc.cap_vpc_1.id

  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 0
    protocol    = "-1"
    to_port     = 0
  }

  egress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
  }

  tags = {
    "Name" = "cap_sg"
  }
}

resource "aws_key_pair" "cap_key_2" {
  key_name   = "cap_key_2"
  public_key = file("~/.ssh/cap_key.pub")
}

resource "aws_instance" "cap_ec2_inst" {
  instance_type = "t2.micro"
  ami           = data.aws_ami.cap_server_ami.id
  key_name               = aws_key_pair.cap_key_2.id
  vpc_security_group_ids = [aws_security_group.cap_sg.id]
  subnet_id              = aws_subnet.cap_subnet_pub_1a.id
  user_data = file("userdata.tpl")
  
  root_block_device {
    volume_size = 8
  }

  tags = {
    "Name" = "cap_ec2_inst"
  }

  provisioner "local-exec" {
    command = templatefile("windows-ssh-config.tpl", {
      hostname = self.public_ip,
      user = "ubuntu",
      identityfile = "~/.ssh/cap_key"
    })
    interpreter = ["Powershell", "-Command"]
    
  }
}
