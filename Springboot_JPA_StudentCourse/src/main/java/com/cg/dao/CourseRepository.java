package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	@Query("SELECT c FROM Course c WHERE c.cName=:cName")
	List<Course> findByName(@Param("cName") String cName);
}