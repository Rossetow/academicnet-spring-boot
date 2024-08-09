package br.com.rrosseto.academicnet.controller;

import br.com.rrosseto.academicnet.model.Aluno;
import br.com.rrosseto.academicnet.repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController
{

    @Autowired
    private AlunoRepository AlunoRepository;

    @GetMapping
    public List<Aluno> listar() {
        return AlunoRepository.findAll();
    }

    @PostMapping
    public void incluir(@RequestBody Aluno aluno) {
        AlunoRepository.save(aluno);
    }

    @PutMapping
    public void alterar(@RequestBody Aluno aluno){
        AlunoRepository.save(aluno);
    }

    @DeleteMapping("/{matricula}")
    public void deletar(@PathVariable Long matricula){
        AlunoRepository.deleteById(matricula);
    }

    @GetMapping("/{matricula}")
    public Optional<Aluno> ler(@PathVariable Long matricula){
        return AlunoRepository.findById(matricula);
    }



}
