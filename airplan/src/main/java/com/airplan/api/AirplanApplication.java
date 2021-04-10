package com.airplan.api;

import com.airplan.api.model.CodeListModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class AirplanApplication {

	public static void main(String[] args)  throws SQLException {
		SpringApplication.run(AirplanApplication.class, args);


	}


}
