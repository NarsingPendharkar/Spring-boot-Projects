package org.FileUploadAndDownload.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.FileUploadAndDownload.Entity.Filedetails;
import org.FileUploadAndDownload.repository.FileRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.opencsv.CSVWriter;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;
	private static final String UPLOAD_DIR = "D:\\Test\\uploads";
	static {
		if (!new File(UPLOAD_DIR).exists()) {
			new File(UPLOAD_DIR).mkdir();
		}
	}

	// Upload File and Store Path in Database
	public String uploadFile(MultipartFile multipartFile) {
		try {
			System.out.println(multipartFile.getContentType());
			if (multipartFile.isEmpty()) {
				return "File is empty. Please select a valid file.";
			}
			if (!multipartFile.getContentType().equals("application/pdf")
					&& !multipartFile.getContentType().equals("image/jpeg")) {
				return "Only PDF or JPG format allowed.";
			}

			String filePath = Paths.get(UPLOAD_DIR, multipartFile.getOriginalFilename()).toString();
			// automatically detect path
			Files.copy(multipartFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
			// Save file details in the database
			Filedetails details = new Filedetails();
			details.setFilename(multipartFile.getOriginalFilename());
			details.setFilepath(filePath);
			details.setFiletype(multipartFile.getContentType());
			details.setFileSize((multipartFile.getSize()) % 1024);
			// Assuming you have a JPA repository
			fileRepository.save(details);
			return "File uploaded successfully !";
		} catch (IOException e) {
			return "Error storing file: " + e.getMessage();
		}
	}

	// Retrieve File Path from Database
	public byte[] downloadFile(String filename) {
		Optional<Filedetails> foundFile = fileRepository.findByFilename(filename);
		if (foundFile.isPresent()) {
			String filePath = foundFile.get().getFilepath();
			try {
				return Files.readAllBytes(Paths.get(filePath));
			} catch (Exception e) {
				throw new RuntimeException("Error reading file: " + e.getMessage());
			}
		} else {
			throw new RuntimeException("File not found!");
		}
	}

	// list of files
	public List<Filedetails> listoffiles() {
		return fileRepository.findAll();
	}

	// delete file from database and direcotry
	public boolean deleteFile(Long id) {
		Filedetails filetodelete = fileRepository.findById(id).orElse(null);
		if (filetodelete == null) {
			return false;
		}
		Path path = Paths.get(filetodelete.getFilepath());
		try {
			Files.deleteIfExists(path);
			fileRepository.delete(filetodelete);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// show file

	public Resource viewFile(long id) {
		Filedetails viewFile = fileRepository.findById(id).orElse(null);
		if (viewFile == null) {
			return null;
		}
		try {
			Path filepath = Paths.get(viewFile.getFilepath());
			Resource resource = new UrlResource(filepath.toUri());
			if (resource.exists() && resource.isReadable()) {
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public Filedetails getFileDetails(long id) {
		return fileRepository.findById(id).orElse(null);
	}
	
	 // ✅ Generate CSV
    public byte[] generateCsvFile() throws IOException {
        List<Filedetails> files = fileRepository.findAll();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));

        // Write headers
        writer.writeNext(new String[]{"ID", "Filename", "File Path", "File Type", "Size"});

        // Write file details
        for (Filedetails file : files) {
            writer.writeNext(new String[]{
                String.valueOf(file.getId()),
                file.getFilename(),
                file.getFilepath(),
                file.getFiletype(),
                String.valueOf(file.getFileSize())
            });
        }
        writer.close();
        return byteArrayOutputStream.toByteArray();
    }

    // ✅ Generate Excel
    public byte[] generateExcelFile() throws IOException {
        List<Filedetails> files = fileRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Files");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Filename", "File Path", "File Type", "Size"};
        for (int i = 0; i < columns.length; i++) {
            headerRow.createCell(i).setCellValue(columns[i]);
        }

        // Fill data rows
        int rowNum = 1;
        for (Filedetails file : files) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(file.getId());
            row.createCell(1).setCellValue(file.getFilename());
            row.createCell(2).setCellValue(file.getFilepath());
            row.createCell(3).setCellValue(file.getFiletype());
            row.createCell(4).setCellValue(file.getFileSize());
        }

        // Write data to byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }

    // ✅ Generate PDF
    public byte[] generatePdfFile() throws IOException {
        List<Filedetails> files = fileRepository.findAll();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("File Details Report\n\n"));
        for (Filedetails file : files) {
            document.add(new Paragraph("ID: " + file.getId()));
            document.add(new Paragraph("Filename: " + file.getFilename()));
            document.add(new Paragraph("File Path: " + file.getFilepath()));
            document.add(new Paragraph("File Type: " + file.getFiletype()));
            document.add(new Paragraph("Size: " + file.getFileSize() + "\n\n"));
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}
