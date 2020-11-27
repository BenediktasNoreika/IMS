package com.qa.IMS.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.IMS.persistence.domain.Order;
import com.qa.IMS.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		
		Long order_id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("customer_id");
		String date = resultSet.getString("order_date");
		Double total = resultSet.getDouble("total");
		return new Order(order_id, customer_id, date, total);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			List<Order> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders(customer_id, order_date) values('" + order.getCustomer_id()
					+ "','" + order.getDate() + "')");
			return readLatest();
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Order readItem(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where order_id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
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
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders_items(order_id, item_id, quantity, total) values('" + order.getOrder_id()
			+ "','" + order.getProduct_id()+ "','" + order.getQuantity() + "','" + order.getTotal() + "')");
			statement.executeUpdate("UPDATE orders SET total = (SELECT SUM(total) FROM orders_items WHERE order_id =" +order.getOrder_id()  + ") WHERE order_id=" + order.getOrder_id());
			System.out.println("Order Updated");
			return readItem(order.getOrder_id());
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
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
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders_items where order_id = " + id);
			return statement.executeUpdate("delete from orders where order_id = " + id);
			
					
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public int deleteIndividual(Long id, long itemId) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders_items where order_id = " + id + " and item_id = " + itemId );
			statement.executeUpdate("UPDATE orders SET total = (SELECT SUM(total) FROM orders_items WHERE order_id =" + id  + ") WHERE order_id=" + id);
			return 1;
			
			
					
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		return 0;
	}

}