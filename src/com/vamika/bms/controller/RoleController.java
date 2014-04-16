package com.vamika.bms.controller;

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

import com.vamika.bms.model.enums.EnableDisableStatus;
import com.vamika.bms.model.enums.EnumInterface;
import com.vamika.bms.service.UserService;
import com.vamika.bms.validator.Create;
import com.vamika.bms.validator.Update;
import com.vamika.bms.view.FullRole;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private UserService userService;
	
	@InitBinder("roleCreateForm")
	public void initRoleCreateFormBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "name", "status" });
	}

	@InitBinder("roleUpdateForm")
	public void initRoleUpdateFormBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "name", "status" });
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listRoles(ModelMap map) {
		map.addAttribute("roles", userService.getAllRoles());
		return "roles/list";
	}

	@RequestMapping(value = "/{roleName}", method = RequestMethod.GET)
	public String showRole(@PathVariable String roleName, ModelMap map) {
		FullRole role = userService.loadRole(roleName);
		map.addAttribute("role", role);
		return "roles/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newRole(ModelMap map) {
		map.addAttribute("roleCreateForm", new FullRole());
		return "roles/new";
	}

	@RequestMapping(value = "/edit/{roleName}", method = RequestMethod.GET)
	public String editRole(@PathVariable String roleName, ModelMap map) {
		FullRole role = userService.loadRole(roleName);
		map.addAttribute("roleUpdateForm", role);
		return "roles/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String createRole(@Validated({ Create.class }) @ModelAttribute("roleCreateForm") FullRole role, BindingResult bindingResult, ModelMap map) {
		if (bindingResult.hasErrors()) {
			return "roles/new";
        }
		userService.saveRole(role);
        return "redirect:/roles";
    }
	
	@RequestMapping(value = "/{roleName}", method = { RequestMethod.POST })
    public String updateRole(@PathVariable String roleName, @Validated({ Update.class }) @ModelAttribute("roleUpdateForm") FullRole role, BindingResult bindingResult, ModelMap map) {
		if (bindingResult.hasErrors()) {
        	return "roles/edit";
        }
		userService.updateRole(role);
        role = null;
        return "redirect:/roles";
    }

	@RequestMapping(value = "/delete/{roleName}", method = { RequestMethod.GET, RequestMethod.DELETE })
	public String destroyRole(@PathVariable String roleName) {
		userService.deleteRole(roleName);
		return "redirect:/roles";
	}

	public void setRoleService(UserService userService) {
		this.userService = userService;
	}
	
	@ModelAttribute("rolesstatus")
	public EnumInterface[] getRolesstatus() {
		return EnableDisableStatus.values();
	}
}