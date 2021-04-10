package com.airplan.api;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AirplanApplication {

	public static void main(String[] args)  throws SQLException {
		SpringApplication.run(AirplanApplication.class, args);


	}


}
