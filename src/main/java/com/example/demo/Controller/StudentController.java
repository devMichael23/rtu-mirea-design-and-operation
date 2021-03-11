package com.example.demo.Controller;

import com.example.demo.DB.*;
import com.example.demo.Model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc) {
        this.studentJdbc = studentJdbc;
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id) {
        Student student = studentJdbc.get(id);
        return student;
    }

    @GetMapping("/student/all")
    public List<Student> getAllStudent() {
        List<Student> studentList = studentJdbc.getAll();
        return studentList;
    }

    @PostMapping("/student/add")
    public String addStudent(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name,
            @RequestParam("second_name") String second_name,
            @RequestParam("study_group_id") Integer study_group_id) {
        return studentJdbc.add(surname, name, second_name, study_group_id);
    }

    @GetMapping("/student/getByGroup/{study_group_id}")
    public List<Student> getStudentByGroup(@PathVariable int study_group_id) {
        List<Student> studentList = studentJdbc.getByGroup(study_group_id);
        return studentList;
    }

    @PutMapping("/student/update")
    public String updateStudent(
            @RequestParam("id") int id,
            @RequestParam("surname") String surname,
            @RequestParam("name") String name,
            @RequestParam("second_name") String second_name,
            @RequestParam("study_group_id") int study_group_id) {
        return studentJdbc.update(id, surname, name, second_name, study_group_id);
    }

    @DeleteMapping("/student/delete/{id}")
    public String deleteStudent ( @PathVariable int id) {
        return studentJdbc.delete(id);
    }
}
