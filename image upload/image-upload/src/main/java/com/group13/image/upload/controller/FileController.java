package com.group13.image.upload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group13.image.upload.payload.FileResponse;
import com.group13.image.upload.services.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
	
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	public String path;
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(
			@RequestParam("image") MultipartFile image
			){
		String fileName = null;
		try {
		 fileName = this.fileService.uploadImage(path,image);
		}catch(IOException e){
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null,"Image is Not Successfully uploaded !!"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new FileResponse(fileName,"Image is Successfully uploaded !!"),HttpStatus.OK);
	}

}
