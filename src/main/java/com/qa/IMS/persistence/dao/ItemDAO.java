package com.qa.IMS.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.IMS.persistence.domain.Item;
import com.qa.IMS.utils.DBUtils;

public class ItemDAO implements Dao<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long product_id = resultSet.getLong("item_id");
		String name = resultSet.getString("name");
		String model = resultSet.getString("model");
		Long stock = resultSet.getLong("stock");
		Double price = resultSet.getDouble("price");
		return new Item(product_id, name, model, stock, price);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from product");) {
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			
			LOGGER.info(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM product ORDER BY item_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Item create(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO product(name, model, stock, price) values('" + item.getName()
					+ "','" + item.getModel() + "','" + item.getStock() + "','" + item.getPrice() + "')");
			return readLatest();
		} catch (Exception e) {
			
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	public Item readItem(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM product where item_id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Item update(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update product set name ='" + item.getName() + "', model ='"
					+ item.getModel() + "', stock = '" + item.getStock() + "', price = '" + item.getPrice() + "' where item_id =" + item.getProduct_id());
			return readItem(item.getProduct_id());
		} catch (Exception e) {
			
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement()) {
			statement.executeUpdate("delete from orders_items where item_id = " + id);
			statement.executeUpdate("delete from product where item_id = " + id);
			return 1;
		} catch (Exception e) {
			
			LOGGER.info(e.getMessage());
		}
		return 0;
	}

}