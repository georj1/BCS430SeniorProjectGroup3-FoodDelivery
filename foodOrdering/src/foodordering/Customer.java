package foodordering;

public class Customer {
	private int customerID;
	
	//TODO: Fix Customer Class so it accurately reflects the Customer Table
	private String customerFName, customerLName, customerEmail, customerPhone, customerCity, customerState, customerStreet, customerZip;
     public Customer() 
     {
    	 //default Constructor -Jack
	 }
     public Customer(int customerID, String customerFName, String customerLName, String customerEmail,
 			String customerPhone, String customerCity, String customerState, String customerStreet,
 			String customerZip) {
 		this.customerID = customerID;
 		this.customerFName = customerFName;
 		this.customerLName = customerLName;
 		this.customerEmail = customerEmail;
 		this.customerPhone = customerPhone;
 		this.customerCity = customerCity;
 		this.customerState = customerState;
 		this.customerStreet = customerStreet;
 		this.customerZip = customerZip;
 		//Constructor with all the variables -Jack
 	}
     //all getters and setters -Jack
     public int getCustomerID() {
 		return customerID;
 	}
 	public void setCustomerID(int customerID) {
 		this.customerID = customerID;
 	}
 	public String getCustomerFName() {
 		return customerFName;
 	}
 	public void setCustomerFName(String customerFName) {
 		this.customerFName = customerFName;
 	}
 	public String getCustomerLName() {
 		return customerLName;
 	}
 	public void setCustomerLName(String customerLName) {
 		this.customerLName = customerLName;
 	}
 	public String getCustomerEmail() {
 		return customerEmail;
 	}
 	public void setCustomerEmail(String customerEmail) {
 		this.customerEmail = customerEmail;
 	}
 	public String getCustomerPhone() {
 		return customerPhone;
 	}
 	public void setCustomerPhone(String customerPhone) {
 		this.customerPhone = customerPhone;
 	}
 	public String getCustomerCity() {
 		return customerCity;
 	}
 	public void setCustomerCity(String customerCity) {
 		this.customerCity = customerCity;
 	}
 	public String getCustomerState() {
 		return customerState;
 	}
 	public void setCustomerState(String customerState) {
 		this.customerState = customerState;
 	}
 	public String getCustomerStreet() {
 		return customerStreet;
 	}
 	public void setCustomerStreet(String customerStreet) {
 		this.customerStreet = customerStreet;
 	}
 	public String getCustomerZip() {
 		return customerZip;
 	}
 	public void setCustomerZip(String customerZip) {
 		this.customerZip = customerZip;
 	}
}
