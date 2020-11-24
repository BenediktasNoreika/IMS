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
			System.out.println(item.toString());
		}
		return items;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Item create() {
		System.out.println("Please enter a item name");
		String name = utils.getString();
		System.out.println("Please enter a model");
		String model = utils.getString();
		System.out.println("Please enter the stock level");
		Long stock = utils.getLong();
		Item item = itemDAO.create(new Item(name, model, stock));
		System.out.println("Item created");
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		System.out.println("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		System.out.println("Please enter a new name");
		String name = utils.getString();
		System.out.println("Please enter a new model");
		String model = utils.getString();
		System.out.println("Please enter the new stock level");
		Long stock = utils.getLong();
		Item item = itemDAO.update(new Item(id, name, model, stock));
		System.out.println("Customer Updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		System.out.println("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}