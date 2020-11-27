package com.qa.IMS.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qa.IMS.persistence.dao.ItemDAO;
import com.qa.IMS.persistence.domain.Item;
import com.qa.IMS.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter a item name");
		String name = utils.getString();
		LOGGER.info("Please enter a model");
		String model = utils.getString();
		LOGGER.info("Please enter the stock level");
		Long stock = utils.getLong();
		LOGGER.info("Please enter the price of the item");
		Double price = utils.getDouble();
		Item item = itemDAO.create(new Item(name, model, stock, price));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a new name");
		String name = utils.getString();
		LOGGER.info("Please enter a new model");
		String model = utils.getString();
		LOGGER.info("Please enter the new stock level");
		Long stock = utils.getLong();
		LOGGER.info("Please enter the new price");
		Double price = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, model, stock, price));
		LOGGER.info("Customer Updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}