package br.com.rrosseto.academicnet.data;

import br.com.rrosseto.academicnet.model.Aluno;
import br.com.rrosseto.academicnet.repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Component
public class CarregaData {

    @Value("${habilitar.carregar.massa.dados}")
    private boolean podeCarregarDados;

    @Autowired
    private AlunoRepository AlunoRepository;

    @PostConstruct
    public void loadData() {

        if (podeCarregarDados) {
            for (int x = 0; x < 50; x++) {
                AlunoRepository.save(new Aluno((long) x, "Jose da Silva" + x, "99662-554" + x, "jose@rrosseto.com.br", Instant.now()));
            }

        }
    }
}
