package com.example.medicalsystem.pojo.task.showtask;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShowTaskData {

	@SerializedName("task_name")
	private String taskName;

	@SerializedName("note")
	private Object note;

	@SerializedName("description")
	private String description;

	@SerializedName("to_do")
	private List<ToDoItem> toDo;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private String status;

	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

	public String getTaskName(){
		return taskName;
	}

	public void setNote(Object note){
		this.note = note;
	}

	public Object getNote(){
		return note;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setToDo(List<ToDoItem> toDo){
		this.toDo = toDo;
	}

	public List<ToDoItem> getToDo(){
		return toDo;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
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
			"task_name = '" + taskName + '\'' + 
			",note = '" + note + '\'' + 
			",description = '" + description + '\'' + 
			",to_do = '" + toDo + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}