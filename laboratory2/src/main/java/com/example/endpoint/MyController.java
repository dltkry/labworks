package com.example.endpoint;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class MyController {

    private final Random random = new Random();
    private int secretNumber = random.nextInt(500) + 1;

    @GetMapping("/currentTime")
    public String currentTime() {
        return LocalDateTime.now().toString();
    }

    @GetMapping("/api")
    public List<Integer> getNumbers(@RequestParam int q) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= q; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    @GetMapping("/random_number")
    public int randomNumber() {
        return random.nextInt(500) + 1;
    }

    @GetMapping("/fib")
    public int fibonacci(@RequestParam int number) {
        if (number <= 1) return number;
        return fibonacci(number - 1) + fibonacci(number - 2);
    }

    @GetMapping("/{string}")
    public String reverseString(@PathVariable String string) {
        return new StringBuilder(string).reverse().toString();
    }

    @GetMapping("/game")
    public String guessNumber(@RequestParam int n) {
        if (n < secretNumber) {
            return "Your number is lower than the secret number.";
        } else if (n > secretNumber) {
            return "Your number is higher than the secret number.";
        } else {
            secretNumber = random.nextInt(500) + 1;
            return "You guessed the number!";
        }
    }
}