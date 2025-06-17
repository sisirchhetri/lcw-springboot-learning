package com.elearn.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elearn.app.dtos.VideoDto;

public interface VideoService {

	VideoDto createVideo(VideoDto video);
	
	VideoDto updateVideo(String videoId, VideoDto video);
	
	VideoDto getVideoById(String videoId);
	
	Page<VideoDto> getAllVideos(Pageable pageable);
	
	void deleteVideo(String videoId);
	
	List<VideoDto> searchVideo(String search);
}
