package com.example.medicalsystem.pojo.task.showtask;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("birthday")
	private String birthday;

	@SerializedName("address")
	private String address;

	@SerializedName("gender")
	private String gender;

	@SerializedName("specialist")
	private String specialist;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("id")
	private int id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String getBirthday(){
		return birthday;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setSpecialist(String specialist){
		this.specialist = specialist;
	}

	public String getSpecialist(){
		return specialist;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"birthday = '" + birthday + '\'' + 
			",address = '" + address + '\'' + 
			",gender = '" + gender + '\'' + 
			",specialist = '" + specialist + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",id = '" + id + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}