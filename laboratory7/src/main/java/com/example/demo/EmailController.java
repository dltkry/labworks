package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;
    @GetMapping("/")
    public String showForm() {
        return "email-form";
    }
    @PostMapping("/send")
    public String sendEmail(
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("message") String message,
            Model model
    ) {
        try {
            emailService.sendEmail(email, "Hello", name, message);
            model.addAttribute("success", "Mail is sent!");
        } catch (Exception e) {

            model.addAttribute("name", name);
            model.addAttribute("error", "Error: " + e.getMessage());
            return "error";
        }
        return "email-form";
    }
}
