package org.FileOperation.Service;

import java.io.IOException;

import org.FileOperation.Entity.Filedata;
import org.FileOperation.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;


	    public String uploadFile(MultipartFile file) throws IOException {
	        Filedata filedata = new Filedata();
	        filedata.setFileName(file.getOriginalFilename());
	        filedata.setType(file.getContentType());
	        filedata.setFileData(file.getBytes());

	        fileRepository.save(filedata);
	        return filedata.getFileName();
	    }

	    public Filedata getFileByName(String fileName) {
	        return fileRepository.findByFileName(fileName).orElse(null);
	    }
	

}
