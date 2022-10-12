package integration.schedule.save;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SaveScheduleTest.class)
@ActiveProfiles(value = "test")
class SaveScheduleTest {

    @Value("${server.port}")
    String serverPort;

    @Test
    void test(){
       assertEquals("8082", serverPort);
    }

}
