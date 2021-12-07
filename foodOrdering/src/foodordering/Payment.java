package foodordering;

public class Payment{
	private float amount;
	public Payment (float amount) {
		this.amount=amount;

	}
	public float getAmount() {
		return this.amount;
	}	

	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void paymentDetails() {
			System.out.println("The payment amount is "+ this.amount);

	}
} 
