package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.StudentRepository;
import com.cg.entities.Student;
import com.cg.exception.NoStudentException;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository sRepo;

	@Override
	@Transactional
	public Student addStudent(Student s) {
		return sRepo.save(s);
	}

	@Override
	public List<Student> findAllStudent() {
		return sRepo.findAll();
	}

	@Override
	public Student findById(int id) throws NoStudentException {
		Optional<Student> stu = sRepo.findById(id);
		if (stu.isPresent())
			return stu.get();

		throw new NoStudentException("student not found");
	}

	@Override
	@Transactional
	public Student modifyStudent(int id, Student s) throws NoStudentException {
		Optional<Student> find = sRepo.findById(id);
		if (find.isPresent()) {
			deleteStudent(id);
			return sRepo.save(s);
		} else {
			throw new NoStudentException("student not found. cannot update.");
		}
	}

	@Override
	@Transactional
	public boolean deleteStudent(int id) throws NoStudentException {
		sRepo.deleteById(id);
		Optional<Student> stu = sRepo.findById(id);
		return !(stu.isEmpty());
	}

	@Override
	public List<Student> findStudentByName(String name) {
		return sRepo.findByName(name);
	}

}