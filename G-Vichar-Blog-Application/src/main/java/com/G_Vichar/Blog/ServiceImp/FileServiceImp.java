package com.G_Vichar.Blog.ServiceImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.G_Vichar.Blog.Service.FileService;
@Service
public class FileServiceImp implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		//file Name
		String fileName=file.getOriginalFilename();
		//abc.png
		
		//random name generate file 
		String randomID=UUID.randomUUID().toString();
		String fileName1=randomID.concat(fileName.substring(fileName.lastIndexOf(".")));
		
		//Full Path
		String filePath=path+ File.separator+fileName1;
		
		//create folder if Not Created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//file copy 
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+ File.separator+fileName;
		InputStream is=new FileInputStream(fullPath);
		
		return is;
	}


}
