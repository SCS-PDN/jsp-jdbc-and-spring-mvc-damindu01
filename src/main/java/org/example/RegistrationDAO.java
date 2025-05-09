package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.Date;

public class RegistrationDAO {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Enroll a student in a course (if not already registered)
    public boolean registerStudent(int courseId, int studentId) {
        if (isAlreadyRegistered(courseId, studentId)) {
            return false; // prevent duplicate registration
        }

        String sql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, studentId, courseId, new Timestamp(new Date().getTime()));
        return true;
    }

    // Check if a student is already registered for a course
    public boolean isAlreadyRegistered(int courseId, int studentId) {
        String sql = "SELECT COUNT(*) FROM registrations WHERE course_id = ? AND student_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{courseId, studentId}, Integer.class);
        return count != null && count > 0;
    }
}
