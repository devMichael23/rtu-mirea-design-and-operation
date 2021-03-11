package com.example.demo.Controller;

import  com.example.demo.DB.*;
import  com.example.demo.Model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyGroupController {
    private final StudyGroupJdbc studyGroupJdbc;

    public StudyGroupController(StudyGroupJdbc studyGroupJdbc) {
        this.studyGroupJdbc = studyGroupJdbc;
    }

    @GetMapping("/group/{id}")
    public StudyGroup getStudent(@PathVariable int id) {
        StudyGroup studyGroup = studyGroupJdbc.get(id);
        return studyGroup;
    }

    @GetMapping("/group/all")
    public List<StudyGroup> getAllStudent() {
        List<StudyGroup> studyGroupList = studyGroupJdbc.getAll();
        return studyGroupList;
    }

    @PostMapping("/group/add")
    public String addGroup(@RequestParam("name") String name) {
        return studyGroupJdbc.add(name);
    }

    @PutMapping("/group/update")
    public String updateStudent(@RequestParam("id") int id, @RequestParam("name") String name) {
        return studyGroupJdbc.update(id, name);
    }

    @DeleteMapping("/group/delete/{id}")
    public String deleteStudent ( @PathVariable int id) {
        return studyGroupJdbc.delete(id);
    }
}
