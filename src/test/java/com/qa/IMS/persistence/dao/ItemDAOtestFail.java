package com.qa.IMS.persistence.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.IMS.persistence.domain.Item;
import com.qa.IMS.utils.DBUtils;

public class ItemDAOtestFail {
	
	private ItemDAO itemDAO = new ItemDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "fail");			
	}
	
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		
	}
	
	@Test
	public void testReadAll() {
		assertEquals(new ArrayList<>(), itemDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		
		assertEquals(null, itemDAO.readLatest());
	}
	@Test
	public void testCreate() {
		Item created = new Item(null, "ben", "noreika", null, null);
		
		assertEquals(null, itemDAO.create(created));
	}
	@Test
	public void testUpdate() {
		Item created = new Item(1l,"ben", "noreika", null, null);
		
		assertEquals(null, itemDAO.update(created));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, itemDAO.delete(1));
		
	}
	}