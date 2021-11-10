package foodordering;

public class Driver {
	int driverID;
	String firstName, lastName, phone, driverEmail, password;
	public Driver() {}
	public Driver(int driverID, String firstName, String lastName, String phone, String driverEmail, String password) {
		this.driverID=driverID;
		this.firstName=firstName;
		this.lastName=lastName;
		this.phone=phone;
		this.driverEmail=driverEmail;
		this.password=password;
	
	}
	public String getDriverEmail() {
		return driverEmail;
	}
	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
