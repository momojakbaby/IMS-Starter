package com.qa.ims.persistence.domain;

public class Orders {

	private Long orderID;
	private Long customerID;
	private Long itemname;
	private Long quantity;
	private Double cost;
	

	public Orders(Long customerID, Long itemname, Long quantity) {
		this.setCustomerID(customerID);
		this.setitemname(itemname);
		this.setQuantity(quantity);
	}

	
	public Orders(Long orderID, Long customerID, Long itemname, Long quantity) {
		this.setOrderID(orderID);
		this.setCustomerID(customerID);
		this.setitemname(itemname);
		this.setQuantity(quantity);
	}
	
	public Orders(Long orderID, Long customerID, Long itemname, Long quantity, Double cost ) {
		this.setOrderID(orderID);
		this.setCustomerID(customerID);
		this.setitemname(itemname);
		this.setQuantity(quantity);
		this.setCost(cost);
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public Long getitemname() {
		return itemname;
	}

	public void setitemname(Long itemname) {
		this.itemname = itemname;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		
		return "orderID: " + orderID + " customerID: " + customerID +"\n" + "itemname: " + itemname + " quantity: " + quantity + "\n" + "Cost: " + cost;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((itemname == null) ? 0 : itemname.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		Orders other = (Orders) obj;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (itemname == null) {
			if (other.itemname != null)
				return false;
		} else if (!itemname.equals(other.itemname))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}


	}