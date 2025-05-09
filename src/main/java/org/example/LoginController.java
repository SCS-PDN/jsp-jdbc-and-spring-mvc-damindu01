package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

public class LoginController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Student student = studentDAO.validateStudent(email, password);
        if (student != null) {
            session.setAttribute("student", student);
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

}
