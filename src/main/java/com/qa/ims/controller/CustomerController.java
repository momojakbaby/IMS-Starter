package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;


 //Takes in customer details for CRUD functionality


public class CustomerController implements CrudController<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CustomerDAO customerDAO;
	private Utils utils;

	public CustomerController(CustomerDAO customerDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.utils = utils;
	}


	 //Reads all customers to the logger

	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer);
		}
		return customers;
	}


	 //Creates a customer by taking in user input

	@Override
	public Customer create() {
		Customer customer = null;

		while (customer == null) {

			LOGGER.info("Please enter a first name\n======================= \nSelect '=' to Cancel ");
			String firstName = utils.getString();
			if (firstName.equals("=")) {
				LOGGER.info("REQUEST CANCELLED");
				break;
			}
			LOGGER.info("Please enter a surname \n Select '=' to Return To Cancel Request \nSelect '-' To Cancel Request");
			String surname = utils.getString();
			if (surname.equals("=")) {
				LOGGER.info("Returned To Previous Step\n==============================");
				continue;
			}
			if(surname.equals("-")) {
				LOGGER.info("REQUEST CANCELLED");
				break;
			}
			customer = customerDAO.create(new Customer(firstName, surname));
			LOGGER.info("Customer created");
			return customer;
		}
		return customer;
	}


	 //Updates an existing customer by taking in user input

	@Override
	public Customer update() {

		Customer customer = null;
		Customer firstNames = null;

		while (customer == null) {

			LOGGER.info("Please enter the ID of the customer you would like to update");
			Long id = utils.getLong();
			if (id == 0) {
				LOGGER.info("REQUEST CANCELLED");
				return null;
			}

				while (firstNames == null) {

				LOGGER.info("Please enter a first name \nSelect R to return to previous step");
				String firstName = utils.getString();
				if (firstName.equals("r")) {
					continue;
				}

				LOGGER.info("Please enter a surname");
				String surname = utils.getString();
				if (surname.equals("r")) {
					continue;
				}
				customer = customerDAO.update(new Customer(id, firstName, surname));
				LOGGER.info("Customer Updated");
				return null;
			}

		}

		return customer;
	}


	 //Deletes an existing customer by the id of the customer

	 //@return

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		if (id == 0) {
			LOGGER.info("REQUEST CANCELLED");
			return 0;
		}
		return customerDAO.delete(id);
	}

}
