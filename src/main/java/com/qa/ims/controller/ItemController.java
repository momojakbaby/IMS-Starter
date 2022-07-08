package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemsDAO itemsDAO;
	private Utils utils;

	public ItemController(ItemsDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}

	
	 //Reads all items to the logger
	 
	@Override
	public List<Items> readAll() {
		List<Items> items = itemsDAO.readAll();
		for (Items item : items) {
			LOGGER.info(item);
		}
		return items;
	}


	
	 //Creates an item by taking in user input
	 
	@Override
	public Items create() {

		Items items = null;

		while (items == null) {

			LOGGER.info("Please enter an item -\nPress '=' to cancel");
			String orderItem = utils.getString();
			if (orderItem.equals("=")) {
				LOGGER.info("REQUEST CANCELLED");
				return null;
			}
			LOGGER.info("Please enter an items value -\nPress 0 to return to previous step");
			Double value = utils.getDouble();
			if (value.equals(0d)) {
				continue;
			}
			if (value.equals(-1)) {
				LOGGER.info("REQUEST CANCELLED");
				return null;
			}
			items = itemsDAO.create(new Items(orderItem, value));
			LOGGER.info("Item created");
		}
		return items;
	}
	
	
	 //Updates an existing item by taking in user input
	 
	@Override
	public Items update() {

		Items items = null;
		Items orderItems = null;

		while (items == null) {

			LOGGER.info("Please enter the itemname of the item you would like to update \nSelect 0 to cancel request");
			Long itemname = utils.getLong();
			if (itemname == 0) {
				LOGGER.info("Request Cancelled");
				return null;
			}
			while (orderItems == null) {

				LOGGER.info("Please enter an item name \nSelect R to return to previous step!");
				String orderItem = utils.getString();
				if (orderItem.equals("0")) {
					continue;
				}
				

					LOGGER.info("Please enter the items value -\nPlease enter 0 to return to previous step \nSelect -1 To Cancel Request");
					Double value = utils.getDouble();
					if (value == 0d) {
						continue;
					}
					if (value == -1d) {
						LOGGER.info("REQUEST CANCELLED");
						return null;
					}

					items = itemsDAO.update(new Items(itemname, orderItem, value));
					LOGGER.info("Item Updated");
					return null;
			}

		}
		return items;
		
	}

	
	 //Deletes an existing item by the itemname of the customer
	 
	 //@return
	 
	@Override
	public int delete() {
		
		
		LOGGER.info("Please enter the itemname of the item you would like to delete");
		Long itemname = utils.getLong();
		if (itemname == 0) {
			LOGGER.info("Request Canelled");
			return 0; 
			}
		return itemsDAO.delete(itemname);

	}

}