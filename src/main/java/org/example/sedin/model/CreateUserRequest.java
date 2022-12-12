package org.example.sedin.model;

public class CreateUserRequest{
	private String name;
	private String job;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJob(){
		return job;
	}

	@Override
 	public String toString(){
		return
			"CreateUserRequest{" +
			"\"name\" : \"" + name + '\"' +
			", \"job\" : \"" + job + '\"' +
			"}";
		}
}
