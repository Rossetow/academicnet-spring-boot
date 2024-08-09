package br.com.rrosseto.academicnet.beans;

import br.com.rrosseto.academicnet.model.Student;
import br.com.rrosseto.academicnet.repository.StudentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "StudentMB")
@ViewScoped
public class StudentMB {

    @Getter
    @Setter
    private List<Student> students = new ArrayList<>();

    @Getter
    @Setter
    private Student student;

    @Autowired
    private StudentRepository studentRepository;

    @PostConstruct
    public List<Student> listAll() {
        students = studentRepository.findAll();
        return students;
    }

    public Integer getListSize() {
        return students.size();
    }

    public void setListSize(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }
}
