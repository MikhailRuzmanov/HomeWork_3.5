package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    @Autowired
    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Info message - create faculty ");
        return facultyRepository.save(faculty);

    }

    public Faculty findFaculty(long id) {
        logger.info("Info message - find faculty ");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Info message - edit faculty ");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Info message - delete faculty ");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFacultys() {
        logger.info("Info message - get all faculties ");
        return facultyRepository.findAll();
    }

    public Faculty findFacultyByColor(String color) {
        logger.info("Info message - find faculty by color ");
        return facultyRepository.findFacultyByColor(color);
    }
}
