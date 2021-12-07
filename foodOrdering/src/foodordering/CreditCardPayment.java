package foodordering;

public class CreditCardPayment extends Payment{
	private String CardName, ExpirationDate, CreditCardNumber;
	CreditCardPayment(float amount, String CardName, String ExpirationDate, String ExpirationNumber){
		super(amount);
		this.CardName=CardName;
		this.ExpirationDate=ExpirationDate;
		this.CreditCardNumber=CreditCardNumber;
	}

	public String getCardName() {
		return CardName;
	}

	public void setCardName(String cardName) {
		CardName = cardName;
	}

	public String getExpirationDate() {
		return ExpirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}

	public String getCreditCardNumber() {
		return CreditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}

	public void PaymentDetails() {
		System.out.println("The payment of Amount " + super.getAmount() + "by the card " + this.CreditCardNumber + "with the expiry date "
				+this.ExpirationDate + "by the card holder " +this.CardName );
	}
}