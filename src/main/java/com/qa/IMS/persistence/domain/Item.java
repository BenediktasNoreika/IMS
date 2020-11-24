package com.qa.IMS.persistence.domain;

public class Item {
	
	
	private Long product_id;
	private String name;
	private String model;
	private Long stock;
	
	public Item(Long product_id, String name, String model, Long stock) {
		this.product_id = product_id;
		this.name = name;
		this.model = model;
		this.stock = stock; 
	}
	
	public Item(String name, String model, Long stock) {
		this.name = name;
		this.model = model;
		this.stock = stock; 
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}




	@Override
	public String toString() {
		return "Item [product_id=" + product_id + ", name=" + name + ", model=" + model + ", stock=" + stock + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}


	}
	
	
	


