package com.group13.image.upload.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String uploadImage(String path, MultipartFile file) throws IOException;

}
