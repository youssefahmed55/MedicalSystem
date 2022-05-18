package com.example.medicalsystem.pojo.showprofile;

import com.google.gson.annotations.SerializedName;

public class ShowProfileData {

	@SerializedName("birthday")
	private String birthday;

	@SerializedName("address")
	private String address;

	@SerializedName("gender")
	private String gender;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("verified")
	private boolean verified;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("type")
	private String type;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("specialist")
	private String specialist;

	@SerializedName("id")
	private int id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

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

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setVerified(boolean verified){
		this.verified = verified;
	}

	public boolean isVerified(){
		return verified;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setSpecialist(String specialist){
		this.specialist = specialist;
	}

	public String getSpecialist(){
		return specialist;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"birthday = '" + birthday + '\'' + 
			",address = '" + address + '\'' + 
			",gender = '" + gender + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",verified = '" + verified + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",type = '" + type + '\'' + 
			",token_type = '" + tokenType + '\'' + 
			",access_token = '" + accessToken + '\'' + 
			",specialist = '" + specialist + '\'' + 
			",id = '" + id + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",email = '" + email + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}