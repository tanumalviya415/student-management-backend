package backendproject.dto;

public class StudentResponseDTO {

    private int id;
    private String name;
    private int marks;
    private String course;

    public StudentResponseDTO() {}

    public StudentResponseDTO(int id, String name, int marks, String course) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public String getCourse() {
        return course;
    }
}
