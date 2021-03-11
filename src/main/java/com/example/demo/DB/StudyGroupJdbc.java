package com.example.demo.DB;

import com.example.demo.Model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudyGroupJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudyGroupJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private StudyGroup mapStudyGroup(ResultSet rs, int i) throws SQLException {
        StudyGroup studyGroup = new StudyGroup(
                rs.getInt("ID"),
                rs.getString("NAME")
        );
        return studyGroup;
    }

    public StudyGroup get(int id) {
        return jdbcTemplate.queryForObject("select * from STUDY_GROUP where STUDY_GROUP.ID = ?", this::mapStudyGroup, id);
    }

    public List<StudyGroup> getAll() {
        return jdbcTemplate.query("select * from STUDY_GROUP",this::mapStudyGroup);
    }

    public StudyGroup search(String studyGroup) {
        return jdbcTemplate.queryForObject("select * from STUDY_GROUP where id = ?", StudyGroup.class, studyGroup);
    }

    public String add(String name) {
        String insert_request = "insert into STUDY_GROUP (name) values ('" + name  + "')";
        jdbcTemplate.execute(insert_request);
        return "В базу данных успешно добавлена новая группа " + name;
    }

    public String update(int id, String name) {
        String update_request = "update STUDY_GROUP set NAME = ? where ID = ?";
        jdbcTemplate.update(update_request, name, id);
        return "Информация о группе с id = " + id + " успешно обновлена";
    }

    public String delete(int id) {
        String delete_request = "delete from STUDY_GROUP where ID = " + id;
        jdbcTemplate.execute(delete_request);
        return "Группа с id = " + id + " удалена успешно";
    }
}
