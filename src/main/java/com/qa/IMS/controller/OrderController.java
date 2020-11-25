package com.qa.IMS.controller;

import java.sql.ResultSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.IMS.persistence.dao.ItemDAO;
import com.qa.IMS.persistence.dao.OrderDAO;
import com.qa.IMS.persistence.domain.Item;
import com.qa.IMS.persistence.domain.Order;
import com.qa.IMS.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			System.out.println(order.toString());
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		System.out.println("Please enter a customer ID");
		Long id = utils.getLong();
		System.out.println("Please enter a order date (in the fromat YYYY-MM-DD)");
		String date = utils.getString();
		Order order = orderDAO.create(new Order(id, date));
		System.out.println("order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		System.out.println("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		System.out.println("Please enter a product ID to add");
		Long product_id = utils.getLong();
		System.out.println("Please enter the quantity of product to order");
		Long quantity = utils.getLong();
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.readItem(product_id);
		Long total = item.getPrice() * quantity;
		Order order = orderDAO.update(new Order(id, product_id, quantity, total));
		System.out.println(order.toString());
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		System.out.println("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}