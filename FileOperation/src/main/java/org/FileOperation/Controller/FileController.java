package org.FileOperation.Controller;



import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.management.RuntimeErrorException;

import org.FileOperation.Entity.Filedata;
import org.FileOperation.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class FileController {
    
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
       try {
		return ResponseEntity.ok(fileService.uploadFile(file));
	} catch (Exception e) {
	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occured ");
	}
       
       
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        Filedata fileData = fileService.getFileByName(fileName);
        if (fileData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileData.getType())); // Set correct content type
        headers.setContentDisposition(ContentDisposition.attachment()
            .filename(fileData.getFileName(), StandardCharsets.UTF_8)
            .build());

        return new ResponseEntity<>(fileData.getFileData(), headers, HttpStatus.OK);
    }
    
    
}
