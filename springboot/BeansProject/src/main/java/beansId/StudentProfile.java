package beansId;

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
    @JoinColumn(name = "StudentProfile")
    private Student studentProfile;
}
