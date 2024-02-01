package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AvatarRepository avatarRepository;

    @AfterEach
    void destroid() {
        avatarRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull().contains("rrrrrr");

    }

    @Test
    public void testGetStudentById() throws Exception {
        //подготовка данных
        Student studentKarl = new Student();
        studentKarl.setName("Karl");
        studentRepository.save(studentKarl);
        //если запрос такой
        Student student = this.restTemplate.getForObject("http://localhost:" + port + "/student/" + studentKarl.getId(), Student.class);
        //то даные должны быть такие
        Assertions
                .assertThat(student)
                .isNotNull();
        Assertions
                .assertThat(student.getName()).isEqualTo(studentKarl.getName());

    }

    @Test
    public void testPostStudents() throws Exception {
        Student student = new Student();
        student.setName("Lululi");
        student.setAge(15);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull().contains("Lululi");

    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student();
        student.setName("Lululi");
        student.setAge(15);

        Long id = restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class).getId();
        restTemplate.delete("http://localhost:" + port + "/student/" + id);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, String.class))
                .contains("500");

    }

    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student();
        student.setName("Lululi");
        student.setAge(15);

        Long id = studentRepository.save(student).getId();

       Student student2 = new Student();
       student2.setName("Ivan");
       student2.setId(id);
       restTemplate.put("http://localhost:" + port + "/student", student2, Student.class);

        Assertions
                .assertThat(studentRepository.findById(id).equals(student2));


    }
    @Test
    public void testAgeBetween() throws Exception{
        Student student1 = new Student(1L, "Vasya", 45 );
        Student student2 = new Student(2L, "Valya", 35 );
        Student student3 = new Student(3L, "Tolya", 23 );
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        Collection <Student> listStudents1 = new ArrayList<>() ;
        listStudents1.add(student1);
        listStudents1.add(student2);

        //Collection <Student> listStudents = this.restTemplate.getForObject("http://localhost:" + port + "/student/betweenAge?min=30&max=47", Collection.class );
      Assertions
              .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/betweenAge?min=30&max=47", Collection.class ))
              .isNotNull();




    }


}
