package foodordering;

public class Driver {
	int driverID;
	String firstName, lastName, phone;
	public Driver() {}
	public Driver(int driverID, String firstName, String lastName, String phone) {
		this.driverID=driverID;
		this.firstName=firstName;
		this.lastName=lastName;
		this.phone=phone;
	
	}
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
