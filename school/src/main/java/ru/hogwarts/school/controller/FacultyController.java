package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFacultys() {
        return ResponseEntity.ok(facultyService.getAllFacultys());
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color")
    public ResponseEntity<?> findFacultyByColor(@RequestParam String color) {
        return ResponseEntity.ok(facultyService.findFacultyByColor(color));
    }

    @GetMapping("/getLongestNameFaculty")
    public ResponseEntity<String> getLongestNameFaculty() {
        return ResponseEntity.ok(facultyService.getLongestNameFaculty());
    }

    @GetMapping("/summa")
    public ResponseEntity<Integer> getSummaTime() {
        return ResponseEntity.ok(facultyService.summa());

    }
    @GetMapping("/summaParallel")
    public ResponseEntity<Integer> getSummaTimeParallel(){
        return ResponseEntity.ok(facultyService.summaParallel());
    }
}
