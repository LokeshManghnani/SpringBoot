package com.cg.services;

import java.util.List;
import com.cg.entities.Student;
import com.cg.exception.NoStudentException;

public interface StudentService {

	Student addStudent(Student s);

	List<Student> findAllStudent();

	Student findById(int id) throws NoStudentException;

	Student modifyStudent(int id, Student s) throws NoStudentException;

	boolean deleteStudent(int id) throws NoStudentException;

	List<Student> findStudentByName(String name);
}