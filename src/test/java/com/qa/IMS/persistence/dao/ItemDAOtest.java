package com.qa.IMS.persistence.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.IMS.persistence.domain.Item;
import com.qa.IMS.utils.DBUtils;

public class ItemDAOtest {
	
	private ItemDAO itemDAO = new ItemDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root");		
	}
	
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		Item item = new Item(1l,"hoover", "hkfoor",10L,20.00);
		expected.add(item);
		
		assertEquals(expected, itemDAO.readAll());
	
		
	}
	
	@Test
	public void testReadLatest() {
		Item expected = new Item(1l,"hoover", "hkfoor",10L,20.00);
		assertEquals(expected, itemDAO.readLatest());
	}
	
	@Test
	public void testCreate() {
		Item created = new Item("shovel", "aaaa",20L,10.00);
		Item expected = new Item(2l,"shovel", "aaaa",20L,10.00);
		assertEquals(expected, itemDAO.create(created));
	}
	
	@Test
	public void testUpdate() {
		Item created = new Item(1l,"shovel", "aaaa",20L,10.00);
		Item expected = new Item(1l,"shovel", "aaaa",20L,10.00);
		assertEquals(expected, itemDAO.update(created));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, itemDAO.delete(1));
		
	}
	
	
	

}
