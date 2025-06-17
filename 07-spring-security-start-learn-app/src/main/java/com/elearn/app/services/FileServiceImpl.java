package com.elearn.app.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String save(MultipartFile file, String outputDirectory, String fileName) throws IOException {
		
		//Create Output Folder
		
		//1. Create Path
		Path outputDir = Paths.get(outputDirectory);
		
		//2. Create Directory/Folder if not exists 
		Files.createDirectories(outputDir);
		
		//e.g:outDir/filename.png : creating path with the fileName 
		//output/web-dev-banner.jpeg
		Path finalFilePath =Paths.get(outputDirectory.toString(), file.getOriginalFilename());
		
		//3. Write The File
		Files.copy(file.getInputStream(), finalFilePath, StandardCopyOption.REPLACE_EXISTING);
		
		//System.out.println(finalFilePath.toString());
		return finalFilePath.toString();
	}

}
