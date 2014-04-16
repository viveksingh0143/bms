package com.vamika.bms.view;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import com.vamika.bms.model.enums.UsersStatus;


@Component
public class ShortUser {
	
	@Min(0) 
	private Integer id;
	
	@Size(min=4, max=250)
	private String username;
	
	@NotNull @Email
	private String email;
	
	@NotNull @Size(min=4, max=250)
	private String name;
	
	@NotNull
	private UsersStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UsersStatus getStatus() {
		return status;
	}

	public void setStatus(UsersStatus status) {
		this.status = status;
	}
}
