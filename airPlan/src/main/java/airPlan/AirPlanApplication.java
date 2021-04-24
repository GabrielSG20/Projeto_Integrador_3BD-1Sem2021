package airPlan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import airPlan.data.JdbcCodeListRepository;
import airPlan.data.SpringJdbcConfig;

@SpringBootApplication
public class AirPlanApplication {

	public static void main(String[] args) {
		SpringJdbcConfig jdbcConfig = new SpringJdbcConfig();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcConfig.mysqlDataSource());
		JdbcCodeListRepository codeListRepository = new JdbcCodeListRepository(jdbcTemplate);
		System.out.println(codeListRepository.list());
		
		SpringApplication.run(AirPlanApplication.class, args);
	}

}
