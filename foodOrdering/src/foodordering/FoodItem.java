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

    private String name;
    private double price;
    private int calories;
    
    public FoodItem(String name, double price, int calories)
    {
        this.name=name;
        this.price=price;
        this.calories=calories;
        //basic constructor for food item, not updated with all the attributes yet -Jack
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    //Getters and Setters, again not updated with all attributes -Jack
    
    
}
