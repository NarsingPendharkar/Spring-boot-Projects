package org.FileUploadAndDownload.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.FileUploadAndDownload.Entity.Filedetails;
import org.FileUploadAndDownload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.io.exceptions.IOException;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;

	// Upload File Endpoint
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			String msg = fileService.uploadFile(file);
			redirectAttributes.addFlashAttribute("msg", msg); // Add flash attribute for success message
			return "redirect:/home"; // Redirect to index page
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Error: " + e.getMessage());
			return "redirect:/home"; // Redirect back to index even in case of error
		}
	}

	// Download File Endpoint
	@GetMapping("/download/{fileName}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
		try {
			byte[] fileData = fileService.downloadFile(fileName);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDisposition(ContentDisposition.attachment().filename(fileName).build());
			return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// list
	@GetMapping("/home")
	public String allFiles(Model model) {
		List<Filedetails> availFiledetails = fileService.listoffiles();
		model.addAttribute("files", availFiledetails);
		return "index";
	}

	@GetMapping("/delete/{id}")
	public String deleteFile(@PathVariable long id, RedirectAttributes redirectAttributes) {
		if (fileService.deleteFile(id)) {
			redirectAttributes.addFlashAttribute("msg", "File deleted with ID: " + id);
		} else {
			redirectAttributes.addFlashAttribute("msg", "Failed to delete file with ID: " + id);
		}
		return "redirect:/home";
	}
	
	
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Resource> viewFile(@PathVariable long id){
		
		 Resource resource = fileService.viewFile(id);
		 Filedetails filedetails=fileService.getFileDetails(id);
		 if (resource == null || filedetails == null) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
	        }
		 HttpHeaders header=new HttpHeaders();
		 header.setContentType(MediaType.parseMediaType(filedetails.getFiletype()));
		 header.setContentDisposition(ContentDisposition.inline()
	                .filename(filedetails.getFilename(), StandardCharsets.UTF_8)
	                .build());
		 header.setCacheControl(CacheControl.noCache().getHeaderValue());
		return ResponseEntity.ok().headers(header).body(resource);
		
	}
	
	// ✅ Download CSV
    @GetMapping("/csv")
    public ResponseEntity<ByteArrayResource> downloadCsv() throws IOException, java.io.IOException {
        byte[] data = fileService.generateCsvFile();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=files.csv")
                .body(new ByteArrayResource(data));
    }

    // ✅ Download Excel
    @GetMapping("/excel")
    public ResponseEntity<ByteArrayResource> downloadExcel() throws IOException, java.io.IOException {
        byte[] data = fileService.generateExcelFile();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=files.xlsx")
                .body(new ByteArrayResource(data));
    }

    // ✅ Download PDF
    @GetMapping("/pdf")
    public ResponseEntity<ByteArrayResource> downloadPdf() throws IOException, java.io.IOException {
        byte[] data = fileService.generatePdfFile();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=files.pdf")
                .body(new ByteArrayResource(data));
    }
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @GetMapping("/view/{id}") public ResponseEntity<Resource>
	 * viewFile(@PathVariable Long id) { Filedetails fileDetails =
	 * fileService.getFileById(id); if (fileDetails == null) { return
	 * ResponseEntity.notFound().build(); }
	 * 
	 * try { Path path = Paths.get(fileDetails.getFilepath()); Resource resource =
	 * new UrlResource(path.toUri());
	 * 
	 * if (resource.exists() && resource.isReadable()) { return ResponseEntity.ok()
	 * .contentType(MediaType.parseMediaType(fileDetails.getFiletype()))
	 * .body(resource); } else { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } } catch
	 * (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
	 */

	

