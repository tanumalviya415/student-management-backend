package backendproject.mapper;

import backendproject.dto.StudentRequestDTO;
import backendproject.dto.StudentResponseDTO;
import backendproject.model.Student;

public class StudentMapper {

    public static Student toEntity(StudentRequestDTO dto) {
        return new Student(
                dto.getId(),
                dto.getName(),
                dto.getMarks(),
                dto.getCourse()
        );
    }

    public static StudentResponseDTO toResponse(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getMarks(),
                student.getCourse()
        );
    }
}
