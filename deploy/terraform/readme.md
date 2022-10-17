Basic AWS infra structure:

- Creates a VPC
- Creates public and private subnets under the VPC
- Creates a Internet Gateway under the VPC
- Creates a public Route Table
- Creates a Route on the public route table
- Associates the public route with the public subnet
- Creates a Security Group
- Retrieve a key pair information
- Creates a EC2 Instance under the public Subnet and Security Group

---
To run the project locally you will need to create an aws account, install and config the 
aws CLI, create and provide a secret key pair. To connect the ec2 instance you will need to
configure ssh on your system.

After:
- Use the commands: 
  ``` terraform plan ```
  to visualize what resources will be provisioned
  If everythin is ok:
  ``` terraform apply ```
  if everything is ok, type: "yes"

  After use:
  ``` terraform destroy ```
  to shut all the infrastructure.