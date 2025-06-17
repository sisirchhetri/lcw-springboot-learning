package com.elearn.app.services;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elearn.app.dtos.VideoDto;
import com.elearn.app.entities.Video;
import com.elearn.app.repositories.VideoRepo;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	VideoRepo videoRepo;

	@Override
	public VideoDto createVideo(VideoDto video) {
		
		video.setVideoId(UUID.randomUUID().toString());
		Video savedVideo = modelMapper.map(video, Video.class);
		videoRepo.save(savedVideo);
		
		return modelMapper.map(savedVideo, VideoDto.class);
	}

	@Override
	public VideoDto updateVideo(String videoId, VideoDto videoDto) {
		
		Video video = videoRepo.findById(videoId)
							    .orElseThrow(
							    		()-> new RuntimeException("Video Not Found"));
		
		video.setDesc(videoDto.getDesc());
		video.setTitle(videoDto.getTitle());
		
		Video savedVideo = videoRepo.save(video);
		
		return modelMapper.map(savedVideo, VideoDto.class);
	}

	@Override
	public VideoDto getVideoById(String videoId) {
		Video video = videoRepo.findById(videoId)
			    .orElseThrow(
			    		()-> new RuntimeException("Video Not Found"));
		
		
		return modelMapper.map(video, VideoDto.class);
	}

	@Override
	public Page<VideoDto> getAllVideos(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVideo(String videoId) {
		Video video = videoRepo.findById(videoId)
			    .orElseThrow(
			    		()-> new RuntimeException("Video Not Found"));
		
		videoRepo.deleteById(videoId);
	}

	@Override
	public List<VideoDto> searchVideo(String search) {
		// TODO Auto-generated method stub
		return null;
	}

}
