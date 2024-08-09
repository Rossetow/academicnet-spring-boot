package br.com.rrosseto.academicnet.repository;

import br.com.rrosseto.academicnet.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>{
}
