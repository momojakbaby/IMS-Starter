package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	
	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;
	private ItemsDAO itemsDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Orders> readAll() {
		List<Orders> orders = orderDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	public List<Orders> readIndividual(Long orderID) {

		List<Orders> order = orderDAO.readIndividual();
		for (Orders orders : order) {
			LOGGER.info(orders);
		}
		return order;

	}

	@Override
	public Orders create() {

		Orders order = null;
		Orders order1 = null;

		while (order == null) {

			LOGGER.info("Please enter a customerID \nEnter -1 to Cancel");
			Long customerID = utils.getLong();
			if (customerDAO == null) {
				if (customerID == -1) {
					break;
				}
				if (customerID == null) {
				LOGGER.info("Please enter a valid customerID");
				continue;
			}
			}
			while (order1 == null) {
				
				LOGGER.info("Please enter an itemname");
				Long itemname = utils.getLong();
				if (itemsDAO == null) {
					
					if(itemname == null) {
					LOGGER.info("Please Enter A Valid ItemsID");
				}
				if (itemname == 0) {
					continue;
				}
				if (itemname == -1) {
					LOGGER.info("REQUEST CANCELLED");
					break;
				}
				LOGGER.info("Please enter a quantity \nSelect 0 to go back to Previous /nSelect -1 to Cancel");
				Long quantity = utils.getLong();
				if (quantity == 0) {
					LOGGER.info("BACK TO PREVIOUS STEP");
					continue;
				}
				if (quantity == -1) {
					LOGGER.info("REQUEST CANCELLED");
					break;
				}
				order = orderDAO.create(new Orders(customerID, itemname, quantity));
				LOGGER.info("Customer created");
				return order;
			}
		}
		}
		return order;
	}

	@Override
	public Orders update() {

		Orders order = null;
		Orders order1 = null;
		Orders order2 = null;

		while (order == null) {

			LOGGER.info("Please enter the orderID of the order you would like to update");
			Long orderID = utils.getLong();
			if (orderDAO == null) {
				LOGGER.info("Please enter a valid OrderID");
				continue;
			}

			if (orderID == -1) {
				break;
			}
			while (order1 == null) {

				LOGGER.info(
						"Please enter a customerID \nSelect 0 To Go Back To Previous Step \nSelect -1 to Cancel Request");
				Long customerID = utils.getLong();
				if (customerID == 0) {
					LOGGER.info("Returning to Previous Step");
					continue;
				}
				if (customerID == -1) {
					LOGGER.info("REQUEST CANCELLED");
					break;
				}
				while (order2 == null) {

					LOGGER.info("Please enter an itemname \nSelect 0 To Go Back To Previous Step");
					Long itemname = utils.getLong();
					if (itemname == 0) {
						LOGGER.info("Returned to setting CustomerID");
						continue;
					}
					if (itemname == -1) {
						LOGGER.info("REQUEST CANCELLED");
						break;
					}

					LOGGER.info("Please enter the quantity");
					Long quantity = utils.getLong();
					if (quantity == 0) {
						LOGGER.info("Returned To Setting itemname");
						continue;
					}
					if (quantity == -1) {
						LOGGER.info("REQUEST CANCELLED");
						break;
					}
					order = orderDAO.update(new Orders(orderID, customerID, itemname, quantity, 0d));
					LOGGER.info("Customer Updated");
				}
			}

		}
		return order;
	}

	@Override
	public int delete() {
		
		 LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderID = utils.getLong();
		if (orderID == 0) {
			LOGGER.info("REQUEST CANCELLED");
			return 0;
		}
		if(orderDAO == null && orderID.equals("")) {
			LOGGER.info("No entries to delete");
			return 0;
		}

		return orderDAO.delete(orderID);
	}
}