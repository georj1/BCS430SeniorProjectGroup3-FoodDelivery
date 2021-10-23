/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class Restaurant {
    
    private int restaurantID, menuID, restaurantTypeID; 
	private String restaurantName, restaurantCity, restaurantState, restaurantStreet, restaurantZip, restaurantType;
	private double rating;
	//private ArrayList<FoodItem> menu = new ArrayList<>(); //menu for now, may change this later -Jack
	    
	public Restaurant()
    {
		
    }
	public Restaurant(int restaurantID, String restaurantName, String restaurantCity, String restaurantState, String restaurantStreet, String restaurantZip, int restaurantTypeID, String restaurantType, double rating, int menuID)
	{
		this.restaurantID=restaurantID;
		this.rating=rating;
        this.restaurantName=restaurantName;
        this.restaurantCity=restaurantCity;
        this.restaurantState=restaurantState;
        this.restaurantStreet=restaurantStreet;
        this.restaurantZip=restaurantZip;
        this.restaurantTypeID=restaurantTypeID;
        this.restaurantType=restaurantType;
        this.menuID=menuID;
        //Standard constructor that sets all the values except menu -Jack
    }
	   
    public int getRestaurantTypeID() {
		return restaurantTypeID;
	}
	public void setRestaurantTypeID(int restaurantTypeID) {
		this.restaurantTypeID = restaurantTypeID;
	}
	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantCity() {
		return restaurantCity;
	}

	public void setRestaurantCity(String restaurantCity) {
		this.restaurantCity = restaurantCity;
	}

	public String getRestaurantState() {
		return restaurantState;
	}

	public void setRestaurantState(String restaurantState) {
		this.restaurantState = restaurantState;
	}

	public String getRestaurantStreet() {
		return restaurantStreet;
	}

	public void setRestaurantStreet(String restaurantStreet) {
		this.restaurantStreet = restaurantStreet;
	}

	public String getRestaurantZip() {
		return restaurantZip;
	}

	public void setRestaurantZip(String restaurantZip) {
		this.restaurantZip = restaurantZip;
	}

	public String getRestaurantType() {
		return restaurantType;
		//Note for this method some more logic will need to be applied when getting information -Jack
	}

	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	//Getters and Setters for all the values -Jack
    
}
