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

import com.qa.IMS.persistence.dao.CustomerDAO;
import com.qa.IMS.persistence.domain.Customer;
import com.qa.IMS.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
	@Mock
	private CustomerDAO customerDAO;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private CustomerController customerController;
	
	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1l,"hoover", "hkfoor"));
		
		//you tell it what to expect, not connecting to db
		when(customerDAO.readAll()).thenReturn(customers);
		
		assertEquals(customers, customerController.readAll());
		
		verify(customerDAO, times(1)).readAll();			
	}
	
	@Test
	public void testCreate() {
		String name = "hoover";
		String surname = "swqea";
		Long stock = 20l;
		double price = 50.00;
		Customer created = new Customer(name, surname);		
		Customer expected = new Customer(2L,name, surname);	
		
		when(utils.getString()).thenReturn(name);
		when(utils.getString()).thenReturn(surname);
		
		when(customerDAO.create(any(Customer.class))).thenReturn(expected);
		
		assertEquals(expected, customerController.create());
		
		verify(utils, times(2)).getString();
		
		verify(customerDAO, times(1)).create(any(Customer.class));
		
		
	}
	
	@Test
	public void testUpdate() {
		Long id = 2l;
		String name = "hoover2";
		String surname = "swqeaa";
		
		Customer created = new Customer(id,name, surname);		
		Customer expected = new Customer(2L,name, surname);	
		
		when(utils.getLong()).thenReturn(id);
		when(utils.getString()).thenReturn(name);
		when(utils.getString()).thenReturn(surname);
		
		when(customerDAO.update(any(Customer.class))).thenReturn(expected);
		
		assertEquals(expected, customerController.update());
		
		
		verify(utils, times(2)).getString();
		verify(utils, times(1)).getLong();
		
		verify(customerDAO, times(1)).update(any(Customer.class));
		
		
	}
	
	@Test
	public void testDelete() {
		Long id = 1l;
		when(utils.getLong()).thenReturn(id);
		
		when(customerDAO.delete(id)).thenReturn(1);
		
		assertEquals(1, customerController.delete());
		
		verify(customerDAO, times(1)).delete(id);
	}
	
	

}