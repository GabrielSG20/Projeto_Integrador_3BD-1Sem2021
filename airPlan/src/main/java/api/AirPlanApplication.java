package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class AirPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirPlanApplication.class, args);
	}

}
