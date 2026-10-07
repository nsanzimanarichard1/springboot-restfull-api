package beansId;


import jakarta.persistence.*;


@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private  Integer id;
}
