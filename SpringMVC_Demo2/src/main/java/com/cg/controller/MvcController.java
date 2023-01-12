package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/frontcontroller")
public class MvcController {

	//@ResponseBody
	@RequestMapping("/index")
	public String index() {
		System.out.println("Index Handler called");
		return "index";
	}
	@ResponseBody
	@RequestMapping("/hello/{id}")
	public String helloUser(@PathVariable("id") String id) {
		return "Welcome " + id;
	}
	@RequestMapping("/studform")
	public ModelAndView getStudForm() {
		ModelAndView mv = new ModelAndView("studentform");
		return mv;
	}
	
	@RequestMapping(value="/submitform")
	public ModelAndView getsubmitForm(@RequestParam("stuName") String studName, @RequestParam("subject")String subject) {
		ModelAndView mv = new ModelAndView("submitform");
		mv.addObject("studData",studName+" "+subject);
		return mv;
	}
}
