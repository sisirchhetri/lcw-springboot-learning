package com.elearn.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	
	@Id
	private String id;
	
	private String title;
	
	private String shortDesc;
	
	@Column(length = 2000)
	private String longDesc;
	
	private double price;
	
	//@Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean live = false;
	
	private double discount ;
	
	private Date createdDate;
	
	private String bannerName;
	
	private String bannerContentType;
	
	
	//Videos
	@OneToMany(mappedBy = "course")
	private List<Video> videos = new ArrayList<>();
	
	//Category
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Category> categoryList = new ArrayList<>();
	
	public void addCategory(Category category) {
		
		categoryList.add(category);
		category.getCourses().add(this);
	}
	
	public void removeCategory(Category category) {
		categoryList.remove(category);
		category.getCourses().remove(this);
	}
	

}
