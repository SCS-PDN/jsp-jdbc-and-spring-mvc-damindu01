package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Fetch all courses from the database
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM courses";
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    // Fetch a course by ID (used in registration)
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{courseId}, new CourseMapper());
    }

    // RowMapper to map ResultSet to Course object
    private static class CourseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course c = new Course();
            c.setCourseId(rs.getInt("course_id"));
            c.setName(rs.getString("name"));
            c.setInstructor(rs.getString("instructor"));
            c.setCredits(rs.getInt("credits"));
            return c;
        }
    }

}
