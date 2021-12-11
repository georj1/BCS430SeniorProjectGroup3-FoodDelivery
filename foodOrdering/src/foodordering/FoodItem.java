/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodordering;

/**
 *
 * @author Jack
 */
public class FoodItem {

    private int foodItemID, calories, restaurantID, categoryID, prepTime;
    private String foodName, description, categoryName;
    private float foodPrice;
    
    public FoodItem()
    {
        
        //basic constructor for food item -Jack
    }
    public FoodItem(int foodItemID, String foodName, float foodPrice, int calories, String description, int prepTime, String categoryName)
    {
    	this.foodItemID=foodItemID;
    	this.foodName=foodName;
    	this.foodPrice=foodPrice;
    	this.calories=calories;
    	this.description=description;
    	this.prepTime=prepTime;
    	this.categoryName=categoryName;
    	//this.categoryID=categoryID;
    }
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getFoodItemID() {
		return foodItemID;
	}
	public void setFoodItemID(int foodItemID) {
		this.foodItemID = foodItemID;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}
	public String getCategoryName() {
		return categoryName;
		//Note for this one some momre logic will be needed to go right to the category table -Jack
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public float getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}
    
    
    //Getters and Setters -Jack
    
    
}
