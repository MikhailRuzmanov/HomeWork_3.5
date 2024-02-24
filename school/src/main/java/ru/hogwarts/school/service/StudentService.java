package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Info message - create Student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Info message - find Student");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Info message - edit Student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Info message - delete Student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Info message - get All Students");
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentByAge(int age) {
        logger.info("Info message - find Student By Age");
        return studentRepository.findStudentByAge(age);

    }
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Info message - find By Age Between");
        return studentRepository.findByAgeBetween(min, max);

    }
    public  Integer getAllStudentsInSchool(){
        logger.info("Info message - get All Student in school");
        return studentRepository.getAllStudentsInSchool();

    }
    public Integer getAvrAgeStudents(){
        logger.info("Info message - get average age Students ");
        return studentRepository.getAvrAgeStudents();
    }
    public Collection<Student> getLastFiveStudents(){
        logger.info("Info message - get last five students ");
        return studentRepository.getLastFiveStudents();
    }

    public Collection<Student> getStudentsWithLetterA(){
        return studentRepository.findAll().stream()
                .parallel()
                .filter(student -> student.getName().startsWith("A"))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }
    public Double middleAgeByStudentsByStream() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
    }

}
