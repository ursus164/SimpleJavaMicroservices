package com.ursus.students.controller;

import com.ursus.students.model.Student;
import com.ursus.students.repository.StudentsRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentsRepository repository;

    public StudentsController(StudentsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody @Valid Student student) {
        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        return repository.findById(id).map(student -> {
            repository.delete(student);
            return ResponseEntity.ok(student);  // przekazanie statusu odpowiedzi HTTP
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody @Valid Student student) {
        // @RequestBody - wyciąga obiekt z ciała requestu (czyli tego co wpisujemy w body dla metody POST) i przypisuje go do zmiennej.
        return repository.findById(id).map(studentUpdate -> {
            studentUpdate.setName(student.getName());
            studentUpdate.setSurname(student.getSurname());
            studentUpdate.setEmail(student.getEmail());
            return ResponseEntity.ok(repository.save(studentUpdate));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody Student student) {
        return repository.findById(id).map(studentFromDb -> {
            // map działa w ten sposób że dane z request body są przypisywane do obiektu student1
            if(!StringUtils.isEmpty(student.getName())) {
                studentFromDb.setName(student.getName());
            }
            if(!StringUtils.isEmpty(student.getSurname())) {
                studentFromDb.setSurname(student.getSurname());
            }
            // z reguły nie pozwalamy na modyfikację wszystkich danych, tylko tych które są potrzebne.

            return ResponseEntity.ok(repository.save(studentFromDb));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
