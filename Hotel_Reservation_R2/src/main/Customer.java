package main;

public class Customer {
	private int cusId;
	private String cusName;
	
	public Customer(int cusId, String cusName) {
		this.cusId = cusId;
		this.cusName = cusName;
	}
	
	public int getCusId() {
		return cusId;
	}
	
	public String getCusName() {
		return cusName;
	}
}
