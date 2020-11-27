package com.qa.IMS.persistence.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.IMS.persistence.domain.Order;
import com.qa.IMS.utils.DBUtils;

public class OrderDAOtestFail {
	
	private OrderDAO orderDAO = new OrderDAO();
	
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
		assertEquals(new ArrayList<>(), orderDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		
		assertEquals(null, orderDAO.readLatest());
	}
	@Test
	public void testCreate() {
		Order created = new Order(1l, "noreika");
		
		assertEquals(null, orderDAO.create(created));
	}
	@Test
	public void testUpdate() {
		Order created = new Order(1l, "noreika");
		
		assertEquals(null, orderDAO.update(created));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, orderDAO.delete(1));
		
	}
	@Test
	public void testReadItem() {
		assertEquals(null, orderDAO.readItem(1l));
		
	}
	}