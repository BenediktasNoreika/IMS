package com.qa.IMS.persistence.dao;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.IMS.persistence.domain.Order;
import com.qa.IMS.utils.DBUtils;

public class OrderDAOtest {
	
	private OrderDAO orderDAO = new OrderDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root");				
	}
	
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/main/resources/sql-schema.sql", "src/main/resources/sql-data.sql");
		
	}
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		Order order = new Order(1l,1L,"2020-10-10",0.00 );
		expected.add(order);
		
		assertEquals(expected, orderDAO.readAll());
	
		
	}
	
	@Test
	public void testReadLatest() {
		Order expected = new Order(1l,1L,"2020-10-10",0.00 );
		assertEquals(expected, orderDAO.readLatest());
	}
	
	@Test
	public void testCreate() {
		Order created = new Order(1L,"2020-10-10" );
		Order expected =  new Order(2l,1L,"2020-10-10",0.00 );
		assertEquals(expected, orderDAO.create(created));
	}
	
	@Test
	public void testUpdate() {
		Order created = new Order(1l,1L,1l,0.00 );
		Order expected = new Order(1l,1L,20l,400.00 );
		assertEquals(null, orderDAO.update(created));
	}
	@Test
	public void testReadItem() {
		Order expected = new Order(1l,1L,"2020-10-10",0.00 );
		assertEquals(expected, orderDAO.readItem(1l));
	}
	
	
	@Test
	public void testDelete() {
		assertEquals(1, orderDAO.delete(1));
		
	}
	
	
	

}