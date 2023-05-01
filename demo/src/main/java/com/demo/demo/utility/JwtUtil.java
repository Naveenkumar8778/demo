package com.demo.demo.utility;

import java.util.Date;


import org.springframework.stereotype.Component;

import com.demo.demo.modal.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	Date date=new Date();
	private static String secret="this_is_secret";
	private static long expairation=600000;
	Date expire=new Date(date.getTime()+expairation);
	public String generateJwt(User user)
	{
		
		//climes
		
		Claims climes=Jwts.claims()
				.setIssuer(user.getId())
				.setExpiration(expire)
				.setIssuedAt(date);
		climes.put("email", user.getEmail());
		climes.put("password", user.getPassword());
		//jwttokencreation
		return Jwts.builder()
				.setClaims(climes)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
				
		
	}
	
	public void varifyJwt(String auth)throws Exception{
		try
		{
		Jwts.parser().setSigningKey(secret).parseClaimsJws(auth);
		}catch(Exception e) { 
			throw new Exception();
		}
	}


}
