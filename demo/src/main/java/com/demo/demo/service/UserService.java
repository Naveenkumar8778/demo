package com.demo.demo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.demo.demo.dto.UserDto;

public interface UserService {
	public String saveUser(UserDto userDto, MultipartFile image) throws IOException;

	public String updateUser(UserDto userDto, MultipartFile image) throws IOException;
	
	public String updateUserOriginalFile(UserDto userDto, MultipartFile image) throws IOException;

	boolean checkEmailAndPassword(String email, String p0assword);

}
