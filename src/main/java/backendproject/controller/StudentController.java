package backendproject.controller;

import backendproject.dto.ApiResponse;
import backendproject.dto.StudentRequestDTO;
import backendproject.dto.StudentResponseDTO;
import backendproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<StudentResponseDTO> addStudent(
            @Valid @RequestBody StudentRequestDTO dto) {
        return new ApiResponse<>(true, "Student added", service.addStudent(dto));
    }

    @GetMapping
    public ApiResponse<Page<StudentResponseDTO>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return new ApiResponse<>(true, "Students fetched",
                service.getStudents(page, size, sortBy, direction));
    }

    @GetMapping("/{id}")
    public ApiResponse<StudentResponseDTO> getStudent(@PathVariable int id) {
        return new ApiResponse<>(true, "Student found", service.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<StudentResponseDTO> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody StudentRequestDTO dto) {

        return new ApiResponse<>(true, "Student updated",
                service.updateStudent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return new ApiResponse<>(true, "Student deleted", null);
    }

    @GetMapping("/search/name")
    public ApiResponse<List<StudentResponseDTO>> searchByName(@RequestParam String name) {
        return new ApiResponse<>(true, "Search by name", service.searchByName(name));
    }

    @GetMapping("/search/course")
    public ApiResponse<List<StudentResponseDTO>> searchByCourse(@RequestParam String course) {
        return new ApiResponse<>(true, "Search by course", service.searchByCourse(course));
    }
}
