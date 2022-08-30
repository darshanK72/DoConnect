package com.doconnect.doconnectservice.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


/*
 * @Author : Rahul Chilampande
 * Created Date : 25-08-2022
 * Modified Date : 28-08-2022
 * Description : File Service Interface
 */

public interface FileService {
	
	String uploadImage(String path, MultipartFile file) throws IOException;

}
