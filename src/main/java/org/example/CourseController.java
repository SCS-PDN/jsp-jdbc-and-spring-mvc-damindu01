package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

public class CourseController {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private RegistrationDAO registrationDAO;

    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseDAO.getAllCourses());
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        registrationDAO.registerStudent(courseId, student.getStudentId());
        return "redirect:/success";
    }

}
