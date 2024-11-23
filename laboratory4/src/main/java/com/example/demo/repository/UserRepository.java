package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'name': ?0 }")
    List<User> findUsersByName(String name);

    @Query(value = "{}", fields = "{ 'email' : 1, '_id' : 0 }")
    List<User> findAllEmails();
}
