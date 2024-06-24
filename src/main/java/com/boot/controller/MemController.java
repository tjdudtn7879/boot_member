package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.MemDTO;
import com.boot.service.MemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemController {
	@Autowired
	private MemService service;
		
	//	url 치고 들어오는 애
	@RequestMapping("/login")
	public String login() {
		log.info("@# login");
		
		return "login";
	}
	
	@RequestMapping("/login_yn")
	public String write(@RequestParam HashMap<String, String> param) {
		log.info("@# login_yn");
		
		ArrayList<MemDTO> dtos = service.loginYn(param);
		
		if (dtos.isEmpty()) {
			return "redirect:login";
		} else {
//			if (request.getParameter("mem_pwd").equals(dtos.get(0).getMem_PWD())) {
			if (param.get("mem_pwd").equals(dtos.get(0).getMem_pwd())) {
				return "redirect:login_ok";	
			} else {
				return "redirect:login";
			}
		}
	}
	
	@RequestMapping("/login_ok")
	public String login_ok() {
		log.info("@# login_ok");
		
		return "login_ok";
	}
	
	@RequestMapping("/register")
	public String register() {
		log.info("@# register");
		
		return "register";
	}
	
	@RequestMapping("/registerok")
	public String registerOk(@RequestParam HashMap<String, String> param) {
		log.info("@# write");
		
		service.write(param);
		
		return "redirect:login";
	}
	
}
