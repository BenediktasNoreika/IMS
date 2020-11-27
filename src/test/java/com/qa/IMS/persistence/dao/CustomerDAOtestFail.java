package com.qa.IMS.persistence.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.IMS.persistence.domain.Customer;
import com.qa.IMS.utils.DBUtils;

public class CustomerDAOtestFail {
	
	private CustomerDAO customerDAO = new CustomerDAO();
	
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
		assertEquals(new ArrayList<>(), customerDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		
		assertEquals(null, customerDAO.readLatest());
	}
	@Test
	public void testCreate() {
		Customer created = new Customer("ben", "noreika");
		
		assertEquals(null, customerDAO.create(created));
	}
	@Test
	public void testUpdate() {
		Customer created = new Customer(1l,"ben", "noreika");
		
		assertEquals(null, customerDAO.update(created));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, customerDAO.delete(1));
		
	}
	}