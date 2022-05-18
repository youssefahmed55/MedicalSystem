package com.example.medicalsystem.pojo.report.showreport;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("specialist")
	private String specialist;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("id")
	private int id;

	@SerializedName("first_name")
	private String firstName;

	public void setSpecialist(String specialist){
		this.specialist = specialist;
	}

	public String getSpecialist(){
		return specialist;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"specialist = '" + specialist + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",id = '" + id + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}