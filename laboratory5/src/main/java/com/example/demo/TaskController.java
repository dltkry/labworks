package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;


    @GetMapping
    public String listTasks(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        List<Task> tasks = taskService.findByUser(user);
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/form";
    }

    @PostMapping
    public String saveTask(@ModelAttribute Task task, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        task.setUser(user);
        taskService.save(task);
        task.setDueDate(LocalDate.now());
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        if (task.getDueDate() == null) {
            task.setDueDate(LocalDate.now());
        }
        model.addAttribute("task", task);
        return "tasks/form";

    }

    @GetMapping("/filter")
    public String filterTasks(@RequestParam(required = false) Integer priority, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        List<Task> tasks;

        if (priority != null) {
            tasks = taskService.findByUserAndPriority(user, priority);
        } else {
            tasks = taskService.findByUser(user); // Показать все задачи, если фильтр не задан
        }

        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }



}
