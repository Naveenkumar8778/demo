package com.demo.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.demo.dto.UserDto;
import com.demo.demo.service.UserService;
import com.demo.demo.utility.JwtUtil;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/signup")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	JwtUtil jwtUtil;
	
	@RequestMapping(path="/savesignup",method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("User") @Valid UserDto userDto,BindingResult bindingResult,@RequestParam MultipartFile image) throws IOException {
		if(bindingResult.hasFieldErrors("email"))
		{
			String error=bindingResult.getFieldError().getDefaultMessage();
			return error;
		}
		else if(bindingResult.hasFieldErrors("password"))
		{
			String error=bindingResult.getFieldError().getDefaultMessage();
			return error;
		}
		else if(bindingResult.hasFieldErrors("username"))
		{
			String error = bindingResult.getFieldError().getDefaultMessage();
			return error;
		}
		
		else
		{
		String user=userService.saveUser(userDto,image);
		return user;
		}
	}
	
	@RequestMapping(path="/updateuser",method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("User") UserDto userDto,@RequestParam ("userimage") MultipartFile image) throws IOException{	
		return userService.updateUser(userDto, image);
	}
	
	@RequestMapping(path="/updateuserfile",method = RequestMethod.POST)
	public String updateUserOriginalFile(@ModelAttribute("User") UserDto userDto,@RequestParam ("userimage") MultipartFile image) throws IOException{	
		return userService.updateUser(userDto, image);
	}
	
	@RequestMapping(path = "/loginuser", method = RequestMethod.POST)
	public String checkEmailAndPassword(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult) {

		String result = "";
		if (bindingResult.hasFieldErrors("email")) {
			result = bindingResult.getFieldError().getDefaultMessage();

		} else if (bindingResult.hasFieldErrors("password")) {
			result = bindingResult.getFieldError().getDefaultMessage();

		} else {
			String userlogin = userService.checkEmailAndPassword(userDto.getEmail(), userDto.getPassword());
			result = userlogin;
		}
		return result;
	}
	@RequestMapping(path = "/privatemethod", method = RequestMethod.POST)
	public String privateMethod(@RequestHeader(value = "authorization", defaultValue = "no fecord found") String auth) throws Exception {
		System.out.println(auth);
		jwtUtil.varifyJwt(auth);		
		return "its working";
	}
}
