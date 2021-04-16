package api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Repository;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@Repository("api.repository")
public class AirPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirPlanApplication.class, args);
	}

}
