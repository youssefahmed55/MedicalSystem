package com.example.medicalsystem.pojo.usersbytype;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserItem implements Serializable {

	@SerializedName("id")
	private int id;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("type")
	private String type;

	@SerializedName("first_name")
	private String firstName;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
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
			"DataItem{" + 
			"id = '" + id + '\'' + 
			",avatar = '" + avatar + '\'' + 
			",type = '" + type + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}