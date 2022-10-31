package tue.student.ed.potapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@ComponentScan(basePackages = {
        "tue.student.ed.service",
        "tue.student.ed.potapi"
})
@MapperScan("tue.student.ed.dao")
@EnableTransactionManagement
@SpringBootApplication
public class PotApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PotApiApplication.class, args);
    }

}
