package classesInheritance;

public abstract class Person {
	
	private String name;
	private String address;
	private short age;
	
	
	public Person(String name, short age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public abstract String getAddress() ;
	public abstract void setAddress(String address);
	
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	
	

}
