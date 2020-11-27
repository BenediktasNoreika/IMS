package com.qa.IMS.controller;


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
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
			LOGGER.info("**** This customer has ordered the following items ****");
			List<Order> ordersItems = orderDAO.readAllitems(order.getOrder_id());
			for(Order ordersitems : ordersItems) {
				LOGGER.info(ordersitems.toStringLong());
				
			}
			LOGGER.info("**********************");
			
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long id = utils.getLong();
		LOGGER.info("Please enter a order date (in the fromat YYYY-MM-DD)");
		String date = utils.getString();
		Order order = orderDAO.create(new Order(id, date));
		LOGGER.info("order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a product ID to add");
		Long product_id = utils.getLong();
		LOGGER.info("Please enter the quantity of product to order");
		Long quantity = utils.getLong();
		ItemDAO itemDAO = new ItemDAO();
		Item item = itemDAO.readItem(product_id);
		double total = item.getPrice() * quantity;
		Order order = orderDAO.update(new Order(id, product_id, quantity, total));
		LOGGER.info(order.toString());
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Delete full order or individual items from an order? (1)Full order (2)For individual items");
		Long decision = utils.getLong();
		if(decision == 1) {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
		}
		else {
			LOGGER.info("Please enter the id of the order you would like to delete from");
			Long id = utils.getLong();
			LOGGER.info("Please enter the id of the item you would like to delete");
			Long product_id = utils.getLong();
			return orderDAO.deleteIndividual(id, product_id);
		}
	}

}