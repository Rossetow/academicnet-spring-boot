package br.com.rrosseto.academicnet.repository;

import br.com.rrosseto.academicnet.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
}
