package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Student validateStudent(String email, String password) {
        String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, new StudentMapper());
        } catch (Exception e) {
            return null; // invalid login
        }
    }

    private static class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student s = new Student();
            s.setStudentId(rs.getInt("student_id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            s.setPassword(rs.getString("password"));
            return s;
        }

        @Override
        public int[] getRowsForPaths(TreePath[] path) {
            return new int[0];
        }
    }


}
