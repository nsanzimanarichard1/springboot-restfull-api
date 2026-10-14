package beansId;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentProfileController {
    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileController(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    // create profile
    @PostMapping("/create/profile")
    public StudentProfile create(@RequestBody StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    @GetMapping("/profile")
    public List<StudentProfile> findAll() {
        return studentProfileRepository.findAll();
    }


}
