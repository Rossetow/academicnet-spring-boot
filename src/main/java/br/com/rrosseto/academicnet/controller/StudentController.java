package br.com.rrosseto.academicnet.controller;

import br.com.rrosseto.academicnet.model.Student;
import br.com.rrosseto.academicnet.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController
{

    @Autowired
    private StudentRepository StudentRepository;

    @GetMapping
    public List<Student> listar() {
        return StudentRepository.findAll();
    }

    @PostMapping
    public void incluir(@RequestBody Student Student) {
        StudentRepository.save(Student);
    }

    @PutMapping
    public void alterar(@RequestBody Student Student){
        StudentRepository.save(Student);
    }

    @DeleteMapping("/{registration}")
    public void deletar(@PathVariable Long registration){
        StudentRepository.deleteById(registration);
    }

    @GetMapping("/{registration}")
    public Optional<Student> ler(@PathVariable Long registration){
        return StudentRepository.findById(registration);
    }



}
