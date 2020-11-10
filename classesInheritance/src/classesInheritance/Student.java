package classesInheritance;

public class Student extends Person {// makes student a child of person

	
	// ToDo 3: Add a field for GPA 
	private double GPA;
	
	// getter
	public void setGPA(double GPA){
		this.GPA = GPA;
	}
	//getter
	public double getGPA(){
		return GPA;
	}


}
