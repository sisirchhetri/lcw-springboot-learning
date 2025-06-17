package com.elearn.app.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String userId;
	
	private String name;
	
	private String email;
	
	private String phoneNumber;
	
	private String password;
	
	private String about;
	
	private boolean active;
	
	private boolean emailVerified;
	
	private boolean smsVerified;
	
	private Date createdAt;
	
	private String profilePath;
	
	private String recentOTP;
}
