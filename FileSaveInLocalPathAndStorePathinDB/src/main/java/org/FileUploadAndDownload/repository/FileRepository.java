package org.FileUploadAndDownload.repository;

import java.util.Optional;

import org.FileUploadAndDownload.Entity.Filedetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<Filedetails, Long> {
	
	Optional<Filedetails>findByFilename(String filename);

}
