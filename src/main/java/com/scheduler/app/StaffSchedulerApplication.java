package com.scheduler.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Staff Scheduler Application's Spring Boot Entry Point.
 */
@SpringBootApplication
public class StaffSchedulerApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {

		//Runs the application with the arguments provided.
		SpringApplication.run(StaffSchedulerApplication.class, args);

	}

}
