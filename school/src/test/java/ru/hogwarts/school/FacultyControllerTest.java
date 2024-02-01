package ru.hogwarts.school;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
//import ru.hogwarts.school.repositiries.FacultyRepository;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FacultyRepository facultyRepository;

	@SpyBean
	private FacultyService facultyService;

	@InjectMocks
	private FacultyController facultyController;

	@Test
	public void postFacultyTest() throws Exception {
		Long id = 1L;
		String name = "Richard";
		String color = "Black";

		JSONObject facultyObject = new JSONObject();
		facultyObject.put("name", name);
		facultyObject.put("color", color);
		facultyObject.put("id", 1L);

		Faculty faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(name);
		faculty.setColor(color);

		when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
		when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

		mockMvc.perform(MockMvcRequestBuilders
						.post("/faculty") //send
						.content(facultyObject.toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) //receive
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value(name))
				.andExpect(jsonPath("$.color").value(color));
	}
	@Test
	public void getFacultyByIdTest() throws Exception {
		Long id = 1L;
		String name = "Richard";
		String color = "Black";

		Faculty faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(name);
		faculty.setColor(color);

		when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(faculty));

		mockMvc.perform(MockMvcRequestBuilders
						.get("/faculty/"+id) //send
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) //receive
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value(name))
				.andExpect(jsonPath("$.color").value(color));
	}
	@Test
	public void getFacultyByColor() throws Exception {
		Long id = 1L;
		String name = "Richard";
		String color = "Black";

		Faculty faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(name);
		faculty.setColor(color);

		when(facultyRepository.findFacultyByColor(any(String.class))).thenReturn(faculty);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/faculty/color?color="+color) //send
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) //receive
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value(name))
				.andExpect(jsonPath("$.color").value(color));
	}
	@Test
	public void putFacultyTest() throws Exception {
		Long id = 1L;
		String name = "Richard";
		String color = "Black";

		JSONObject facultyObject = new JSONObject();
		facultyObject.put("name", name);
		facultyObject.put("color", color);
		facultyObject.put("id", 1L);

		Faculty faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(name);
		faculty.setColor(color);

		when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
		when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

		mockMvc.perform(MockMvcRequestBuilders
						.put("/faculty") //send
						.content(facultyObject.toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) //receive
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value(name))
				.andExpect(jsonPath("$.color").value(color));
	}

	@Test
	public void deleteFacultyTest() throws Exception {
		Long id = 1L;
		String name = "Richard";
		String color = "Black";

		Faculty faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(name);
		faculty.setColor(color);


		mockMvc.perform(MockMvcRequestBuilders
						.delete("/faculty/57") //send
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()); //receive

	}
	@Test
	public void getAllFacultyTest() throws Exception {
		Long id = 1L;
		String name = "Richard";
		String color = "Black";

		Faculty faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(name);
		faculty.setColor(color);
		Faculty faculty2 = new Faculty();
		faculty2.setId(2L);
		faculty2.setName("Kolya");
		faculty2.setColor("Black");
		Faculty faculty3 = new Faculty();
		faculty3.setId(3L);
		faculty3.setName("Franc");
		faculty3.setColor("Red");
		List <Faculty> list1 = new ArrayList<>(List.of(faculty, faculty2, faculty3));

		when(facultyRepository.findAll()).thenReturn(list1);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/faculty") //send
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).toString().contains(faculty3.getName()) ;//receive

	}
}
