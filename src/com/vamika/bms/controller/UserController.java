package com.vamika.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vamika.bms.model.enums.EnumInterface;
import com.vamika.bms.model.enums.UsersStatus;
import com.vamika.bms.model.enums.UsersType;
import com.vamika.bms.service.UserService;
import com.vamika.bms.validator.Create;
import com.vamika.bms.validator.Update;
import com.vamika.bms.view.FullRole;
import com.vamika.bms.view.FullUser;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@InitBinder("userCreateForm")
	public void initUserCreateFormBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "username", "password", "confirmPassword", "name", "email", "admin", "status", "roles_id" });
	}

	@InitBinder("userUpdateForm")
	public void initUserUpdateFormBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "username", "password", "confirmPassword", "name", "email", "admin", "status", "roles_id" });
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listUsers(ModelMap map) {
		map.addAttribute("users", userService.getAllUsers());
		return "users/list";
	}

	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	public String showUser(@PathVariable String userName, ModelMap map) {
		FullUser user = userService.loadUser(userName);
		map.addAttribute("user", user);
		return "users/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newUser(ModelMap map) {
		map.addAttribute("userCreateForm", new FullUser());
		return "users/new";
	}

	@RequestMapping(value = "/edit/{userName}", method = RequestMethod.GET)
	public String editUser(@PathVariable String userName, ModelMap map) {
		FullUser user = userService.loadUser(userName);
		map.addAttribute("userUpdateForm", user);
		return "users/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String createUser(@Validated({ Create.class }) @ModelAttribute("userCreateForm") FullUser user, BindingResult bindingResult, ModelMap map) {
		if (bindingResult.hasErrors()) {
			return "users/new";
        }
        userService.saveUser(user);
        map.clear();
        return "redirect:/users";
    }
	
	@RequestMapping(value = "/{userName}", method = { RequestMethod.POST })
    public String updateUser(@PathVariable String userName, @Validated({ Update.class }) @ModelAttribute("userUpdateForm") FullUser user, BindingResult bindingResult, ModelMap map) {
		if (bindingResult.hasErrors()) {
        	return "users/edit";
        }
		if(user.getPassword() == null || user.getPassword().trim().equals("")) {
			user.setPassword(null);
		}
        userService.updateUser(user);
        map.clear();
        return "redirect:/users";
    }

	@RequestMapping(value = "/delete/{userName}", method = { RequestMethod.GET, RequestMethod.DELETE })
	public String destroyUser(@PathVariable String userName, ModelMap map) {
		userService.deleteUser(userName);
		map.clear();
		return "redirect:/users";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@ModelAttribute("roles")
	public List<FullRole> getRoles() {
		return userService.getAllRoles();
	}
	
	@ModelAttribute("userstype")
	public EnumInterface[] getUserstype() {
		return UsersType.values();
	}
	
	@ModelAttribute("usersstatus")
	public EnumInterface[] getUsersstatus() {
		return UsersStatus.values();
	}
}