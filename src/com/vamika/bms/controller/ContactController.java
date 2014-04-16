package com.vamika.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vamika.bms.model.Contact;

@Controller
@SessionAttributes
@RequestMapping("/contacts")
public class ContactController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showContacts(ModelMap map) {
		map.addAttribute("command", new Contact());
		return "contact";
	}
	
	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact") Contact contact, BindingResult result) {
		System.out.println("First Name:" + contact.getFirstname() + "Last Name:" + contact.getLastname());
		return "redirect:/contacts.html";
	}
}
