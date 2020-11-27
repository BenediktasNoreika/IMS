package com.qa.IMS.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.IMS.persistence.dao.OrderDAO;
import com.qa.IMS.persistence.domain.Order;
import com.qa.IMS.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private OrderDAO orderDAO;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private OrderController orderController;
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1l,1l,"2020-10-10",20.00));
		
		//you tell it what to expect, not connecting to db
		when(orderDAO.readAll()).thenReturn(orders);
		
		assertEquals(orders, orderController.readAll());
		
		verify(orderDAO, times(1)).readAll();			
	}
	
	@Test
	public void testCreate() {
		
		String date = "swqea";
		Long customer_id = 1l;
		
		Order created = new Order(customer_id, date);		
		Order expected = new Order(2l, customer_id, date, 0.00);			
		
		when(utils.getLong()).thenReturn(customer_id);
		when(utils.getString()).thenReturn(date);
		
		when(orderDAO.create(any(Order.class))).thenReturn(expected);
		
		assertEquals(expected, orderController.create());
		
		verify(utils, times(1)).getString();	
		verify(utils, times(1)).getLong();
		verify(orderDAO, times(1)).create(any(Order.class));
		
		
	}
	
	@Test
	public void testUpdate() {
		Long id = 1l;
		Long product_id = 2l;
		Long quantity = 20l;
		Double total = 20.00;
		Order created = new Order(id,product_id, quantity, total);		
		Order expected = new Order(id,product_id, quantity, total);		
		
		when(utils.getLong()).thenReturn(id);
		when(utils.getLong()).thenReturn(product_id);
		when(utils.getLong()).thenReturn(quantity);
		
		
		when(orderDAO.update(any(Order.class))).thenReturn(expected);
		
		assertEquals(expected, orderController.update());
		
		verify(utils, times(3)).getLong();
	
		verify(orderDAO, times(1)).update(any(Order.class));
		
	}
	
	@Test
	public void testDelete() {
		Long id = 1l;
		when(utils.getLong()).thenReturn(id);
		
		when(orderDAO.delete(id)).thenReturn(1);
		
		assertEquals(1, orderController.delete());
		
		verify(orderDAO, times(1)).delete(id);
	}
	
	

}