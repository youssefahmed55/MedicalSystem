package com.example.medicalsystem.pojo.task.showtask;

import com.google.gson.annotations.SerializedName;

public class ToDoItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("task_id")
	private int taskId;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTaskId(int taskId){
		this.taskId = taskId;
	}

	public int getTaskId(){
		return taskId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"ToDoItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",task_id = '" + taskId + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}