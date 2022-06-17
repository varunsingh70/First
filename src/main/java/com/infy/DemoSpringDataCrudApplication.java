package com.infy;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.CustomerDTO;
import com.infy.service.CustomerServiceImpl;

@SpringBootApplication
public class DemoSpringDataCrudApplication implements CommandLineRunner {
	
	public static final Log LOGGER = LogFactory.getLog(DemoSpringDataCrudApplication.class);

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataCrudApplication.class, args);

	}

	public void run(String... args) throws Exception {
		//addCustomer();
		getCustomer();
		findAllCustomers();
	}
	
	public void addCustomer() {

		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(4);
		customer.setEmailId("harry@infy.com");
		customer.setName("Harry");
		customer.setDateOfBirth(LocalDate.now());

		try {
			customerService.addCustomer(customer);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}
	public void getCustomer() {
		try {

			CustomerDTO customer = customerService.getCustomer(1);
			LOGGER.info(customer);
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void findAllCustomers() {
		try {
			customerService.findAll().forEach(LOGGER::info);

		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}
}
