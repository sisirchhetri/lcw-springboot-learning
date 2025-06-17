package com.elearn.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.dtos.VideoDto;
import com.elearn.app.services.VideoService;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {
	
	//Assignment
	
	@Autowired
	private VideoService videoService;
	
	@PostMapping
	public ResponseEntity<VideoDto> createVideo(@RequestBody VideoDto videoDto){
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(this.videoService.createVideo(videoDto));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<VideoDto> updateVideo(@RequestBody VideoDto videoDto, @PathVariable String id){
		
		return ResponseEntity.ok(this.videoService.updateVideo(id, videoDto));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<VideoDto> getVideo(@PathVariable String id){
		
		return ResponseEntity.ok(this.videoService.getVideoById(id));
	}
	
	//1. Create API  - Get All Videos Page able Start

	//Create API  - Get All Videos Page able End
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteVideo(@PathVariable String id){
		
		this.videoService.deleteVideo(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<List<VideoDto>> searchVideos(@RequestParam String search){
		
		return ResponseEntity.ok(this.videoService.searchVideo(search));
	}
	
	//2. Create API - Get all videos from courses
	
	//3. Create API - To upload a video

}
