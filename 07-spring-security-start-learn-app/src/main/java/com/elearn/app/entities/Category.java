package com.elearn.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	
	@Id
	private String id;
	
	private String title;
	
	@Column(name = "description")
	private String desc;
	
	private Date addedDate;
	
	@ManyToMany(mappedBy = "categoryList", 
				cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<>();
	
	
	public void addCourse(Course course) {
		courses.add(course);
		course.getCategoryList().add(this);
	}
	
	
	public void removeCourse(Course course) {
		courses.remove(course);
		course.getCategoryList().remove(this);
	}
}
