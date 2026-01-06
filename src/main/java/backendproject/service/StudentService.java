package backendproject.service;

import backendproject.dto.StudentRequestDTO;
import backendproject.dto.StudentResponseDTO;
import backendproject.exception.StudentNotFoundException;
import backendproject.mapper.StudentMapper;
import backendproject.model.Student;
import backendproject.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        Student student = StudentMapper.toEntity(dto);
        return StudentMapper.toResponse(repository.save(student));
    }

    public Page<StudentResponseDTO> getStudents(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable)
                .map(StudentMapper::toResponse);
    }

    public StudentResponseDTO getStudentById(int id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return StudentMapper.toResponse(student);
    }

    public StudentResponseDTO updateStudent(int id, StudentRequestDTO dto) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        student.setName(dto.getName());
        student.setMarks(dto.getMarks());
        student.setCourse(dto.getCourse());

        return StudentMapper.toResponse(repository.save(student));
    }

    public void deleteStudent(int id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        repository.delete(student);
    }

    public List<StudentResponseDTO> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<StudentResponseDTO> searchByCourse(String course) {
        return repository.findByCourseContainingIgnoreCase(course)
                .stream()
                .map(StudentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
