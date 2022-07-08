package com.qa.ims.persistence.domain;

public class Items {
	
	private Long itemname;
	private String orderItem;
	private Double value;
	
	public Items(String orderItem, Double value) {
		this.setOrderItem(orderItem);
		this.setvalue(value);
	}

	public Items(Long itemname, String orderItem, Double value) {
		this.setitemname(itemname);
		this.setOrderItem(orderItem);
		this.setvalue(value);
	}

	public Long getitemname() {
		return itemname;
	}

	public void setitemname(Long itemname) {
		this.itemname = itemname;
	}

	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public Double getvalue() {
		return value;
	}

	public void setvalue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "itemname:" + itemname + " Item:" + orderItem + " value:" + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderItem == null) ? 0 : orderItem.hashCode());
		result = prime * result + ((itemname == null) ? 0 : itemname.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (getOrderItem() == null) {
			if (other.getOrderItem() != null)
				return false;
		} else if (!getOrderItem().equals(other.getOrderItem()))
			return false;
		if (itemname == null) {
			if (other.itemname != null)
				return false;
		} else if (!itemname.equals(other.itemname))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


	}