package com.vamika.bms.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.vamika.bms.annotation.validation.FieldMatch;
import com.vamika.bms.annotation.validation.Unique;
import com.vamika.bms.annotation.validation.UniqueList;
import com.vamika.bms.model.User;
import com.vamika.bms.model.enums.UsersStatus;
import com.vamika.bms.model.enums.UsersType;
import com.vamika.bms.validator.Create;
import com.vamika.bms.validator.Signup;
import com.vamika.bms.validator.Update;

@Component
@FieldMatch(first = "password", second = "confirmPassword", message="FieldMatch.user.confirmPassword", groups = { Update.class, Create.class, Signup.class })
@UniqueList({
		@Unique(entity=User.class, uniqueFields={"username"}, message = "{Unique.user.username}", groups = { Create.class, Signup.class }),
		@Unique(entity=User.class, uniqueFields={"email"}, message = "{Unique.user.email}", groups = { Create.class, Signup.class }),
		@Unique(entity=User.class, uniqueFields={"email"}, message = "{Unique.user.email}", ignoreFields={"username"}, groups = { Update.class})
})
public class FullUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(groups = { Update.class })
	private Integer id;

	@Size(min = 5, max = 250, groups = { Update.class, Create.class, Signup.class })
	private String username;

	@NotBlank(groups = { Update.class, Create.class, Signup.class })
	@Email(groups = { Update.class, Create.class, Signup.class })
	private String email;

	@Size(min = 4, max = 250, groups = { Update.class, Create.class, Signup.class })
	/*@Phone(groups = { Update.class, Create.class, Signup.class })*/
	private String name;

	@Size(min = 5, max = 250, groups = { Create.class, Signup.class })
	private String password;

	@Size(min = 5, max = 250, groups = { Create.class, Signup.class })
	private String confirmPassword;

	@NotNull(groups = { Update.class, Create.class })
	private UsersType admin;

	@NotNull(groups = { Update.class, Create.class })
	private UsersStatus status;
	
	private List<Integer> roles_id;
	
	private List<FullRole> roles;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UsersType getAdmin() {
		return admin;
	}

	public void setAdmin(UsersType admin) {
		this.admin = admin;
	}

	public UsersStatus getStatus() {
		return status;
	}

	public void setStatus(UsersStatus status) {
		this.status = status;
	}

	public List<FullRole> getRoles() {
		return roles;
	}

	public void setRoles(List<FullRole> roles) {
		this.roles = roles;
	}

	public List<Integer> getRoles_id() {
		return roles_id;
	}

	public void setRoles_id(List<Integer> roles_id) {
		this.roles_id = roles_id;
	}
	
	
}
