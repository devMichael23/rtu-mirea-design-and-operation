package com.example.demo.DB;

import com.example.demo.Model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JournalJdbc {
    private final JdbcTemplate jdbcTemplate;

    public JournalJdbc(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private Journal mapJournal(ResultSet rs, int i) throws SQLException {
        Journal journal = new Journal(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
        return journal;
    }

    public Journal get(int id) {
        return jdbcTemplate.queryForObject("select * from JOURNAL where id = ?", this::mapJournal, id);
    }

    public List<Journal> getAll() {
        List<Journal> journalList = jdbcTemplate.query("select * from JOURNAL", this::mapJournal);
        return journalList;
    }

    public String add(int student_id, int study_plan_id, boolean in_time, int count, int mark_id) {
        String add_request = "insert into journal (student_id, study_plan_id, in_time, count, mark_id) " +
                "values ('" + student_id + "','" + study_plan_id + "','" + in_time + "','" + count + "','" + mark_id + "')";
        jdbcTemplate.execute(add_request);
        return "Запись в журнал успешно добавлена";
    }

    public String delete(Integer id) {
        String delete_request = "delete from JOURNAL where id = " + id;
        jdbcTemplate.execute(delete_request);
        return "Запись журнала с id = " + id + " успешно удалена";
    }

    public String update(int id, int student_id, int study_plan_id, boolean in_time, int count, int mark_id) {
        String update_request = "update journal set student_id = ?, study_plan_id = ?, in_time = ?, count = ?, mark_id = ? where id = ?";
        jdbcTemplate.update(update_request, student_id, study_plan_id, in_time, count, mark_id, id);
        return "Запись в журнале с id = " + id + " успешно обновлена";
    }

    public String updateMark(int id, int mark_id) {
        String update_request = "update journal set mark_id = ? where id = ?";
        jdbcTemplate.update(update_request, mark_id, id);
        return "Оценка записи в журнале с id = " + id + " успешно обновлена";
    }

    public List<Journal> getByStudent(int student_id) {
        List<Journal> journalList = jdbcTemplate.query("select j.id, student_id, study_plan_id, in_time, count, mark_id " +
                "from journal j inner join student s on j.student_id=s.id where student_id = ?", this::mapJournal, student_id);
        return journalList;
    }

    public List<Journal> getByGroup(int study_group_id) {
        List<Journal> journalList = jdbcTemplate.query("select j.id, student_id, study_plan_id, in_time, count, mark_id " +
                "from journal j inner join student s on j.student_id=s.id " +
                "where s.study_group_id = ?", this::mapJournal, study_group_id);
        return journalList;
    }
}
