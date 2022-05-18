package com.example.medicalsystem.pojo.calls.callsreceptionist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CallsResponse{

	@SerializedName("data")
	private List<CallsData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(List<CallsData> data){
		this.data = data;
	}

	public List<CallsData> getData(){
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
			"CallsResponse{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}