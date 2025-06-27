package com.elearn.app.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private String userId;
	
	private String name;
	
	@Column(unique = true)//user-name for login
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
