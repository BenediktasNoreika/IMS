package com.qa.IMS.persistence.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.IMS.persistence.domain.Customer;
import com.qa.IMS.utils.DBUtils;

public class CustomerDAOtest {
	
	private CustomerDAO customerDAO = new CustomerDAO();
	
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
		List<Customer> expected = new ArrayList<>();
		Customer customer = new Customer(1l, "jordan", "harrison");
		expected.add(customer);
		
		assertEquals(expected, customerDAO.readAll());
	
		
	}
	
	@Test
	public void testReadLatest() {
		Customer expected = new Customer(1l, "jordan", "harrison");
		assertEquals(expected, customerDAO.readLatest());
	}
	
	@Test
	public void testCreate() {
		Customer created = new Customer("ben", "noreika");
		Customer expected = new Customer(2l, "ben", "noreika");
		assertEquals(expected, customerDAO.create(created));
	}
	
	@Test
	public void testUpdate() {
		Customer created = new Customer(1l,"ben", "noreika");
		Customer expected = new Customer(1l, "ben", "noreika");
		assertEquals(expected, customerDAO.update(created));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, customerDAO.delete(1));
		
	}
	
	
	

}