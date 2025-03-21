package org.school.repository;

import java.util.List;
import java.util.Optional;

import org.school.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Long> {

	Optional<ClassEntity> findByClassName(String className);
}
