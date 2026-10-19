package beansId;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;


    public StudentService(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }


    public StudentResponseDto createStudentServiceMethod(StudentDto studentDto) {

        // Find school using schoolId
        School school = schoolRepository.findById(studentDto.schoolId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "There is no school in database with that id: "
                                        + studentDto.schoolId()
                        )
                );

        // Create Student entity
        Student student1 = new Student();

        student1.setFirstname(studentDto.firstname());
        student1.setLastname(studentDto.lastname());
        student1.setEmail(studentDto.email());
        student1.setSchool(school);

        // Save student
        Student student = studentRepository.save(student1);

        System.out.println("student lastname is: " + student.getLastname());

        // Return only what we want the user to see
        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }

    // get student by ID
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student with that id does not exist: " + id));

    }

    // get all student
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    // update by ID
    public Student updateStudent(Integer id, Student student){
        Student doesExist =  studentRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("student with that id does not exist: " + id)
                );

        if (student.getFirstname()!=null){
            doesExist.setFirstname(student.getFirstname());
        }

        if (student.getLastname()!=null){
            doesExist.setLastname(student.getLastname());
        }
        if (student.getEmail()!=null){
            doesExist.setEmail(student.getEmail());
        }
        if (student.getAge()!=null){
            doesExist.setAge(student.getAge());
        }

        return  studentRepository.save(doesExist);
    }

    // delete student by Id
    public String deleteStudent(@PathVariable Integer id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("student with that id does not exist: " + id)
        );
        studentRepository.delete(student);
        return "Student with ID " + id + " deleted successfully";
    }
}




