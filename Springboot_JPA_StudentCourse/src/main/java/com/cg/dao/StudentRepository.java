package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query("SELECT s FROM Student s WHERE s.name=:name")
	List<Student> findByName(@Param("name") String name);
}