package br.com.rrosseto.academicnet.data;

import br.com.rrosseto.academicnet.model.Student;
import br.com.rrosseto.academicnet.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Component
public class LoadData {

    @Value("${habilitate.load.data}")
    private boolean canLoad;

    @Autowired
    private StudentRepository studentRepository;

    @PostConstruct
    public void loadData() {

        if (canLoad) {
            for (int x = 0; x < 50; x++) {
                studentRepository.save(new Student((long) x, "John Doe" + x, "99662-554" + x, "john@doe.com.br", Instant.now()));
            }

        }
    }
}
