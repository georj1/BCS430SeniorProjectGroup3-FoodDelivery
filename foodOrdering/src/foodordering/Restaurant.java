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
    
    private int restaurantID; 
	private String restaurantName, restaurantCity, restaurantState, restaurantStreet, restaurantZip, restaurantType;
	private double rating;
	private ArrayList<FoodItem> menu = new ArrayList<>(); //menu for now, may change this later -Jack
	    
	public Restaurant(int restaurantID, String restaurantName, String restaurantCity, String restaurantState, String restaurantStreet, String restaurantZip, String restaurantType, double rating)
	{
		this.restaurantID=restaurantID;
		this.rating=rating;
        this.restaurantName=restaurantName;
        this.restaurantCity=restaurantCity;
        this.restaurantState=restaurantState;
        this.restaurantStreet=restaurantStreet;
        this.restaurantZip=restaurantZip;
        this.restaurantType=restaurantType;
        //Standard constructor that sets all the values except menu -Jack
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

	public ArrayList<FoodItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<FoodItem> menu) {
        this.menu = menu;
    }
    //Getter and Setter for the menu -Jack
	    
    public void addFoodItem(String name, double price, int calories)
    {
        menu.add(new FoodItem(name, price, calories));
    }
    //a method to add a food item to the menu by calling the ArrayList add function -Jack
    
}
