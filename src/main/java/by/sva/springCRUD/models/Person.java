package by.sva.springCRUD.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
	private int id;
	
	@NotEmpty(message = "Name should not be empty")
	@Size(min=2, max=20, message = "Name should be between 2 and 20 characters")
	private String name;
	
	@Min(value = 16, message = "Age should be 16 or greater")
	@Max(value = 130, message = "Age should be 130 or less")
	private int age;
	
	@NotEmpty(message = "Name should not be empty")
	@Email(message = "Invalid email")
	private String email;
	
	public Person() {
		
	}
	
	public Person(int id, String name, int age, String email) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
