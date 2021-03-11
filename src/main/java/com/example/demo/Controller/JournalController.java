package com.example.demo.Controller;

import com.example.demo.DB.*;
import com.example.demo.Model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JournalController {
    private final JournalJdbc journalJdbc;

    public JournalController(JournalJdbc journalJdbc) {
        this.journalJdbc = journalJdbc;
    }

    @GetMapping("/journal/get/{id}")
    public Journal getRecord(@PathVariable int id) {
        Journal journal = journalJdbc.get(id);
        return journal;
    }

    @GetMapping("/journal/all")
    public List<Journal> getAllRecords() {
        List<Journal> results = journalJdbc.getAll();
        return results;
    }

    @PostMapping("/journal/add")
    public String addRecord(
            @RequestParam("student_id") int student_id,
            @RequestParam("study_plan_id") int study_plan_id,
            @RequestParam("in_time") boolean in_time,
            @RequestParam("count") int count,
            @RequestParam("mark_id") int mark_id) {
        return journalJdbc.add(student_id, study_plan_id, in_time, count, mark_id);
    }

    @DeleteMapping("/journal/delete/{id}")
    public String deleteRecord(@PathVariable int id) {
        return journalJdbc.delete(id);
    }

    @PutMapping("/journal/update")
    public String updateRecord(
            @RequestParam("id") int id,
            @RequestParam("student_id") int student_id,
            @RequestParam("study_plan_id") int study_plan_id,
            @RequestParam("in_time") boolean in_time,
            @RequestParam("count") int count,
            @RequestParam("mark_id") int mark_id) {
        return journalJdbc.update(id, student_id, study_plan_id, in_time, count, mark_id);
    }

    @PutMapping("/journal/update/mark")
    public String updateRecordMark(@RequestParam("id") int id, @RequestParam("mark_id") int mark_id) {
        return journalJdbc.updateMark(id, mark_id);
    }

    @GetMapping("/journal/get/byStudent/{student_id}")
    public List<Journal> getByStudent(@PathVariable int student_id) {
        List<Journal> journalList = journalJdbc.getByStudent(student_id);
        return journalList;
    }

    @GetMapping("/journal/get/byGroup/{study_group_id}")
    public List<Journal> getByGroup(@PathVariable int study_group_id) {
        List<Journal> results = journalJdbc.getByGroup(study_group_id);
        return results;
    }
}
