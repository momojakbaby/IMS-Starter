package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrdersTest {
	
	Orders order= new Orders(1L,1L,1L);

	
	@Test
	public void OrdersConstructorTest() {
		Orders order = new Orders(1L, 1L, 1L);
		
		assertTrue(order instanceof Orders);
	}
	@Test
	public void OrdersConstructorTest1() {
		Orders order = new Orders(1L, 1L, 1L,1L);
		
		assertTrue(order instanceof Orders);
	}

	@Test
	public void OrdersConstructorTest2() {
		Orders order = new Orders(1L, 1L, 1L,1L,1D);
	
		assertTrue(order instanceof Orders);
}
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Orders.class).withIgnoredFields("cost").verify();
	}
}
