package foodordering;

import java.util.Date;

public class CreditCard {
private String creditCardNumber,  creditCardType;
private long creditCardExpirationDate;
private int customerID, creditCardCVV;
public CreditCard() {}
public CreditCard(String creditCardNumber, long creditCardExpirationDate, String creditCardType, int customerID,
		int creditCardCVV) {
	this.creditCardNumber = creditCardNumber;
	this.creditCardExpirationDate = creditCardExpirationDate;
	this.creditCardType = creditCardType;
	this.customerID = customerID;
	this.creditCardCVV = creditCardCVV;
}
public String getCreditCardNumber() {
	return creditCardNumber;
}
public void setCreditCardNumber(String creditCardNumber) {
	this.creditCardNumber = creditCardNumber;
}
public long getCreditCardExpirationDate() {
	return creditCardExpirationDate;
}
public void setCreditCardExpirationDate(long creditCardExpirationDate) {
	this.creditCardExpirationDate = creditCardExpirationDate;
}
public String getCreditCardType() {
	return creditCardType;
}
public void setCreditCardType(String creditCardType) {
	this.creditCardType = creditCardType;
}
public int getCustomerID() {
	return customerID;
}
public void setCustomerID(int customerID) {
	this.customerID = customerID;
}
public int getCreditCardCVV() {
	return creditCardCVV;
}
public void setCreditCardCVV(int creditCardCVV) {
	this.creditCardCVV = creditCardCVV;
}
//All the code needed to create a credit card to be mimicked to the database -Ahsan
}
