package foodordering;

public class CreditCardPayment extends Payment{
	private String CardName, ExpirationDate, CreditCardNumber;
	CreditCardPayment(double amount, String CardName, String ExpirationDate, String ExpirationNumber){
		super(amount);
		this.CardName=CardName;
		this.ExpirationDate=ExpirationDate;
		this.CreditCardNumber=CreditCardNumber;
	}

	public void PaymentDetails() {
		System.out.println("The payment of Amount " + super.getAmount() + "by the card " + this.CreditCardNumber + "with the expiry date "
				+this.ExpirationDate + "by the card holder " +this.CardName );
	}
}