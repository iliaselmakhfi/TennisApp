package com.ielmakhfi.tennisapp.dto;

public class PlayerDto {

	private String name;
	// I can add other information about the user
	
	public PlayerDto(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
