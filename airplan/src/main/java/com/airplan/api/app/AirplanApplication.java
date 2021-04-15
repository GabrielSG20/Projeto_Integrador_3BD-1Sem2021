package com.airplan.api.app;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class AirplanApplication {

	public static void main(String[] args)  throws SQLException {
		SpringApplication.run(AirplanApplication.class, args);


	}

}
