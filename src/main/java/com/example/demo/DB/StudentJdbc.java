package com.example.demo.DB;

import com.example.demo.Model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Student mapStudent(ResultSet rs, int i) throws SQLException {
        Student student = new Student(
                rs.getInt("ID"),
                rs.getString("SURNAME"),
                rs.getString("NAME"),
                rs.getString("SECOND_NAME"),
                rs.getInt("STUDY_GROUP_ID")
        );
        return student;
    }

    public Student get(int id) {
        return jdbcTemplate.queryForObject("select * from STUDENT where STUDENT.ID = ?", this::mapStudent, id);
    }

    public List<Student> getAll() {
        return jdbcTemplate.query("select * from STUDENT",this::mapStudent);
    }

    public List<Student> getByGroup(int study_group_id) {
        return jdbcTemplate.query("select * from STUDENT where STUDY_GROUP_ID = ?",this::mapStudent, study_group_id);
    }

    public Student search(String student) {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE id = ?", Student.class, student);
    }

    public String add(String surname, String name, String second_name, int study_group_id) {
        String insert_request = "insert into student (surname, name, second_name, study_group_id) " +
                "values ('" + surname + "','" + name + "','" + second_name + "','" + study_group_id + "')";
        jdbcTemplate.execute(insert_request);
        return "В базу данных успешно добавлен новый студент " + surname + " " + name + " " + second_name;
    }

    public String update(int id, String surname, String name, String second_name, int study_group_id) {
        String update_request = "update STUDENT set SURNAME = ?, NAME = ?, SECOND_NAME = ?, STUDY_GROUP_ID = ? where ID = ?";
        jdbcTemplate.update(update_request, surname, name, second_name, study_group_id, id);
        return "Информация о студенте с id = " + id + " успешно обновлена";
    }

    public String delete(int id) {
        String delete_request = "delete from STUDENT where ID = " + id;
        jdbcTemplate.execute(delete_request);
        return "Студент с id = " + id + " удален успешно";
    }
}

/*
Запрос позволяющий получить вместо id группы сразу её название при выводе студента
"select STUDENT.ID, SURNAME, STUDENT.NAME, SECOND_NAME, STUDY_GROUP.NAME from STUDENT join STUDY_GROUP on STUDENT.STUDY_GROUP_ID = STUDY_GROUP.ID WHERE STUDENT.ID = ?;"

Этот же запрос для вывода всех студентов
"select STUDENT.ID, SURNAME, STUDENT.NAME, SECOND_NAME, STUDY_GROUP.NAME from STUDENT join STUDY_GROUP on STUDENT.STUDY_GROUP_ID = STUDY_GROUP.ID;"
 */
