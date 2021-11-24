package foodordering;

public class Rating {
	private int ratingID, restaurantID, ratingScore, customerID;
	private String ratingReview;
	public Rating()
	{
		
	}
	public Rating(int ratingID, int restaurantID, int ratingScore, String ratingReview, int customerID)
	{
		this.ratingID=ratingID;
		this.restaurantID=restaurantID;
		this.ratingScore=ratingScore;
		this.ratingReview=ratingReview;
		this.customerID=customerID;
	}
	public int getRatingID() {
		return ratingID;
	}
	public void setRatingID(int ratingID) {
		this.ratingID = ratingID;
	}
	public int getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	public int getRatingScore() {
		return ratingScore;
	}
	public void setRatingScore(int ratingScore) {
		this.ratingScore = ratingScore;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getRatingReview() {
		return ratingReview;
	}
	public void setRatingReview(String ratingReview) {
		this.ratingReview = ratingReview;
	}
}
