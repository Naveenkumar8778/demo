package com.demo.demo.serviceimpl;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.demo.dto.UserDto;
import com.demo.demo.modal.User;
import com.demo.demo.repository.UserRepository;
import com.demo.demo.service.UserService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
 

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public String saveUser(UserDto userDto,MultipartFile image) throws IOException {

		User user=new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());		
		 //userRepository.save(user);
		 mongoTemplate.save(user);
	
		 File file=new File("E:/myprivateproject/demo/images/"+user.getId());
		 if(!file.exists())
		 {
			file.mkdir();
		 }
		 byte[] bytes =image.getBytes();
		 
		 Path path=Paths.get(file+"/"+user.getId()+".jpg");		
		 Files.write(path, bytes);		
		 return "inserted successfully";
	}
	@Override
	public String updateUser(UserDto userDto, MultipartFile image) throws IOException{
		Query query =new Query(Criteria.where("id").is(userDto.getId()));
		Update update=new Update();
		update.set("username", userDto.getUsername());
		update.set("email", userDto.getEmail());
		update.set("password", userDto.getPassword());
		mongoTemplate.findAndModify(query, update, User.class);	
	
		File file=new File("E:/myprivateproject/demo/images/"+userDto.getId());
		byte[] bytes =image.getBytes();
		Path path=Paths.get(file+"/"+userDto.getId()+".jpg");	
		if (Files.exists(path)) {
			Files.delete(path);
		}
		if (!file.exists()) {
			file.mkdirs();
		}
		Files.write(path, bytes);
		 
		
		return "updated successfully";
	}


	@Override
	public String updateUserOriginalFile(UserDto userDto, MultipartFile image) throws IOException{
		Query query =new Query(Criteria.where("id").is(userDto.getId()));
		Update update=new Update();
		update.set("username", userDto.getUsername());
		update.set("email", userDto.getEmail());
		update.set("password", userDto.getPassword());
		mongoTemplate.findAndModify(query, update, User.class);	
		/*
		 * String extension=""; int i = image.getName().lastIndexOf('.'); if (i > 0) {
		 * extension = image.getName().substring(i); }
		 */
		File file=new File("E:/myprivateproject/demo/images/"+userDto.getId());
		byte[] bytes =image.getBytes();
		
		Path path=Paths.get(file+"/"+userDto.getId()+".jpg");	
		if (Files.exists(path)) {
			Files.delete(path);
		}
		if (!file.exists()) {
			file.mkdirs();
		}
		Files.write(path, bytes);
		 
		
		return "updated successfully";
	}


	@Override
	public boolean checkEmailAndPassword(String email, String p0assword) {
		java.util.Optional<User> users=userRepository.findByEmailAndPassword(email,p0assword);
		if(users.isPresent())
		{
			return true;			
		}
		else
		{
			return false;
		}
	}

}
