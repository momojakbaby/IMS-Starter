package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Orders> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("orderID");
		Long customerID = resultSet.getLong("customerID");
		Long itemname = resultSet.getLong("itemname");
		Long quantity = resultSet.getLong("quantity");
		Double cost = quantity * getCost(itemname);
		return new Orders (orderID, customerID, itemname, quantity,cost);
	}

	public Double getCost(Long itemname) {
		
		
		ItemsDAO items = new ItemsDAO();

		
		Double cost = items.read(itemname).getvalue();
		return cost;
		
	}

	 //This commands reads all the customers that are in the database
	 
	 //@return A list of customers
	 
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY orderID DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public List<Orders> readIndividual() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT ? FROM orders ORDER BY orderID DESC LIMIT 1");) {
			LOGGER.info("Please enter an OrderID");
			resultSet.next();
			return (List<Orders>) modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}



	 //This command creates a customer in the database
	 //@parameter customer - takes in a customer object. id will be ignored
	
	@Override
	public Orders create(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(CustomerID, itemname, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, order.getCustomerID());
			statement.setLong(2, order.getitemname());
			statement.setLong(3, order.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders read(Long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE orderID = ?");) {
			statement.setLong(1, orderID);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	 //Ths commands updates a customer's information in the database
	  
	 //@parametereter customer - takes in a customer object, the id field will be used to
	 //                update that customer in the database
	
	@Override
	public Orders update(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET customerID = ?, itemname = ?, quantity = ? WHERE orderID = ?");) {
			statement.setLong(1, order.getOrderID());
			statement.setLong(2, order.getCustomerID());
			statement.setLong(3, order.getitemname());
			statement.setLong(4,order.getQuantity()); 
			return read(order.getCustomerID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	 //This command deletes a customer in the database
	
	 //@parametereter id - id of the customer
	
	@Override
	public int delete(long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE orderID = ?");) {
			statement.setLong(1, orderID);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}


}
