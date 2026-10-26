package beansId;


import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface StudentRepository extends JpaRepository< Student, Integer> {
}
