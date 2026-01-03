package com.doconnect.doconnectservice.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/*
 * @Author : Rahul Chilampande
 * Created Date : 25-08-2022
 * Modified Date : 28-08-2022
 * Description : Service Implementation of File Service
 */
@Service
public class FileServiceIMPL implements FileService {

	/*
	 * @Author : Rahul Chilampande
	 * Created Date : 25-08-2022
	 * Modified Date : 28-08-2022
	 * Description : Uploading Image
	 * Params : path(string), file (MultipartFile)
	 */
	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// file name
		String name = file.getOriginalFilename();

		// radom-name generate
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

		// full-path
		String filepath = path + File.separator + fileName1;

		// create folder if it not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file copy

		Files.copy(file.getInputStream(), Paths.get(filepath));
		return name;
	}

}
