package foodordering;

public class Menu {
     private int menuID, foodItemID;
     public Menu()
     {
	
     }
     public Menu(int menuID, int foodItemID)
     {
	     this.menuID=menuID;
	     this.foodItemID=foodItemID;
     }
	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
	public int getFoodItemID() {
		return foodItemID;
	}
	public void setFoodItemID(int foodItemID) {
		this.foodItemID = foodItemID;
	}
}
