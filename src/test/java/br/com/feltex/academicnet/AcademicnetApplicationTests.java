package br.com.rrosseto.academicnet;

import br.com.rrosseto.academicnet.model.Student;
import br.com.rrosseto.academicnet.repository.StudentRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcademicnetApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentRepository StudentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    final Instant registerDate = Instant.parse("2021-01-04T10:20:19.0Z");
    final Student Student = new Student(123L, "Jose da Silva", "33444-0093", "contato@rrosseto.com.br", registerDate);
    final Student StudentToAlter = new Student(123L, "Jose da Silva Santos", "33444-99999", "santos@rrosseto.com.br", registerDate);

    @Test
    void crudStudent() throws Exception {
        StudentRepository.deleteAll();
        includeStudent(Student);
        alterStudent(StudentToAlter);

        var consultedStudent = consultStudent(StudentToAlter.getRegistration());

        assertEquals(StudentToAlter, consultedStudent);
        deleteStudent(consultedStudent.getRegistration());

        var listStudents = listStudents();
        assertEquals(0, listStudents.size());
    }


    @ParameterizedTest
    @NullAndEmptySource
    void invalidData(String conteudo) throws Exception {
        StudentRepository.deleteAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpRequest = new HttpEntity<>(objectMapper.writeValueAsString(conteudo), httpHeaders);
        var responseCode = restTemplate.postForEntity(gerServiceURL(), httpRequest, Student.class).getStatusCode();
        assertEquals( HttpStatus.BAD_REQUEST , responseCode);
    }


    private Student consultStudent(long registration) {
        return restTemplate.getForObject(gerServiceURL() + registration, Student.class);
    }


    private void deleteStudent(Long registration) {
        var responseCode = restTemplate.exchange(gerServiceURL() + registration,
                HttpMethod.DELETE,
                new HttpEntity<>(new HttpHeaders()),
                String.class).getStatusCode();
        assertEquals(HttpStatus.OK, responseCode);
    }

    private void alterStudent(Student StudentToAlter) {

        var responseCode = restTemplate.exchange(gerServiceURL(), HttpMethod.PUT,
                new HttpEntity<>(StudentToAlter),
                String.class).getStatusCode();
        assertEquals(HttpStatus.OK, responseCode);
    }

    private void includeStudent(final Student Student) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpRequest = new HttpEntity<>(objectMapper.writeValueAsString(Student), httpHeaders);
        var responseCode = restTemplate.postForEntity(gerServiceURL(), httpRequest, Student.class).getStatusCode();
        assertEquals(HttpStatus.OK, responseCode);
    }

    private List<Student> listStudents() {
        return restTemplate.exchange(gerServiceURL(),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                List.class).getBody();
    }

    private String gerServiceURL() {
        return "http://localhost:" + port + "/Students/";
    }
}
