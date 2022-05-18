package com.example.medicalsystem.pojo.report.showreport;

import com.google.gson.annotations.SerializedName;

public class Manger{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("specialist")
	private String specialist;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("id")
	private String id;

	@SerializedName("first_name")
	private String firstName;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
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
			"Manger{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",specialist = '" + specialist + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",id = '" + id + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}