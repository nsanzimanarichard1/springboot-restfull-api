package beansId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "studentProfile")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bio;
    private  String image;

    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student studentProfile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Student getStudentProfile() {
        return studentProfile;
    }
    public void setStudentProfile(Student studentProfile) {
        this.studentProfile = studentProfile;
    }
}
