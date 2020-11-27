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

import com.qa.IMS.persistence.dao.ItemDAO;
import com.qa.IMS.persistence.domain.Item;
import com.qa.IMS.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	
	@Mock
	private ItemDAO itemDAO;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private ItemController itemController;
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1l,"hoover", "hkfoor",10l,20.00));
		
		//you tell it what to expect, not connecting to db
		when(itemDAO.readAll()).thenReturn(items);
		
		assertEquals(items, itemController.readAll());
		
		verify(itemDAO, times(1)).readAll();			
	}
	
	@Test
	public void testCreate() {
		String name = "hoover";
		String model = "swqea";
		Long stock = 20l;
		double price = 50.00;
		Item created = new Item(name, model, stock, price);		
		Item expected = new Item(2L,name, model, stock, price);	
		
		when(utils.getString()).thenReturn(name);
		when(utils.getString()).thenReturn(model);
		when(utils.getLong()).thenReturn(stock);
		when(utils.getDouble()).thenReturn(price);
		when(itemDAO.create(any(Item.class))).thenReturn(expected);
		
		assertEquals(expected, itemController.create());
		
		verify(utils, times(2)).getString();
		verify(utils, times(1)).getLong();
		verify(utils, times(1)).getDouble();
		verify(itemDAO, times(1)).create(any(Item.class));
		
		
	}
	
	@Test
	public void testUpdate() {
		Long id = 2l;
		String name = "hoover2";
		String model = "swqeaa";
		Long stock = 10l;
		double price = 40.00;
		Item created = new Item(id,name, model, stock, price);		
		Item expected = new Item(2L,name, model, stock, price);	
		
		when(utils.getLong()).thenReturn(id);
		when(utils.getString()).thenReturn(name);
		when(utils.getString()).thenReturn(model);
		when(utils.getLong()).thenReturn(stock);
		when(utils.getDouble()).thenReturn(price);
		when(itemDAO.update(any(Item.class))).thenReturn(expected);
		
		assertEquals(expected, itemController.update());
		
		
		verify(utils, times(2)).getString();
		verify(utils, times(2)).getLong();
		verify(utils, times(1)).getDouble();
		verify(itemDAO, times(1)).update(any(Item.class));
		
		
	}
	
	@Test
	public void testDelete() {
		Long id = 1l;
		when(utils.getLong()).thenReturn(id);
		
		when(itemDAO.delete(id)).thenReturn(1);
		
		assertEquals(1, itemController.delete());
		
		verify(itemDAO, times(1)).delete(id);
	}
	
	

}
