package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentByAge(int age) {
        return studentRepository.findStudentByAge(age);

    }
    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);

    }
    public  Integer getAllStudentsInSchool(){
        return studentRepository.getAllStudentsInSchool();

    }
    public Integer getAvrAgeStudents(){
        return studentRepository.getAvrAgeStudents();
    }
    public Collection<Student> getLastFiveStudents(){
        return studentRepository.getLastFiveStudents();
    }
}
