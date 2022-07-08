package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest { 

		private final OrderDAO DAO = new OrderDAO();

		@Before
		public void setup() {
			DBUtils.connect();
			DBUtils.getInstance().init("src/test/resources/sql-Orders.sql", "src/test/resources/sql-data-Orders.sql");
		}

		@Test
		public void testCreate() {
			final Orders created = new Orders(2L,1L, 1L, 1L);
			assertEquals(created, DAO.create(created));
		}

		@Test
		public void testReadAll() {
			List<Orders> expected = new ArrayList<>();
			expected.add(new Orders(1L, 1L, 1L,1L));
			assertEquals(expected, DAO.readAll());
		}

		@Test
		public void testReadLatest() {
			assertEquals(new Orders(1L, 1L, 1L, 1L), DAO.readLatest());
		}

		@Test
		public void testRead() {
			final long orderID = 1L;
			assertEquals(new Orders(orderID, 1L, 1L, 1L), DAO.read(orderID));
		}

		@Test
		public void testUpdate() {
			final Orders updated = new Orders(1L, 1L, 1L, 1L);
			assertEquals(updated, DAO.update(updated));

		}

		@Test
		public void testDelete() {
			assertEquals(1, DAO.delete(1));
		}
	}

