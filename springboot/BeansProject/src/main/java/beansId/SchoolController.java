package beansId;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private  final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/create")
    public School create(@RequestBody School school) {
        return schoolRepository.save(school);


    }

    // getall school
    @GetMapping("/")
    public List<School> getSchools() {
        return schoolRepository.findAll();
    }
}
