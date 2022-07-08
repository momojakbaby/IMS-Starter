package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;


public final class ItemsDAOTest {
	
	private final ItemsDAO DAO = new ItemsDAO();


	@Before
	public void setup() {
		DBUtils.connect();
		System.out.println("Commencing Test");
		DBUtils.getInstance().init("src/test/resources/sql-Items.sql", "src/test/resources/sql-data-Items.sql");
	}

	@Test
	public void testCreate() {
		final Items created = new Items(10L, "Duracell", 3.50D);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Items> expected = new ArrayList<>();
		expected.add(new Items(1L, "A", 3.0D));
		expected.add(new Items(2L, "AA", 5.0D));
		expected.add(new Items(3L, "AAA", 6.0D));
		expected.add(new Items(4L, "PLUS", 7.0D));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Items(2L, "Battery", 3.50D), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 2L;
		assertEquals(new Items(ID, "Battery", 3.5D), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Items updated = new Items(1L, "Bounty", 55.5D);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(6));
	}
}