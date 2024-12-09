package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@Controller
public class PhotoController {
    private final PhotoService photoService;
    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("photos", photoService.getAllPhotos());
        return "index";
    }
    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, Model model) {
        try {
            Photo photo = photoService.uploadPhoto(file);
            model.addAttribute("message", "Photo uploaded successfully: " + photo.getFileName());
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload photo: " + e.getMessage());
        }
        return "redirect:/";
    }
}
