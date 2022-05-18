package com.example.medicalsystem.pojo.task;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TasksResponseModel{

	@SerializedName("data")
	private List<TaskResponseData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(List<TaskResponseData> data){
		this.data = data;
	}

	public List<TaskResponseData> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"TasksResponseModel{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}