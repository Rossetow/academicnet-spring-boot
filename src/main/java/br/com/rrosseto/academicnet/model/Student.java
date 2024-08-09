package br.com.rrosseto.academicnet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Student{

    @Id
    private Long registration;
    private String name;
    private String cellphone;
    private String email;
    private Instant registerDate;

}
