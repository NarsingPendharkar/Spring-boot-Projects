package org.FileOperation.Repository;

import java.util.Optional;

import org.FileOperation.Entity.Filedata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<Filedata, Long>{

  Optional<Filedata>	findByFileName(String fileName);
  
}
