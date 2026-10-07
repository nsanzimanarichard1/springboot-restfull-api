package beansId;


import jakarta.persistence.*;


@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "age")
    private Integer age;



    public Student() {}

    public Student(String firstname, String lastname, String email, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
    }
    public Integer getId() {
        return id;


    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}
