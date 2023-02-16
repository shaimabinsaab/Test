package com.example.todo.Repsitry;

import com.example.todo.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepoitory extends JpaRepository<MyUser, Integer> {
    MyUser findMyUserByUsername(String username);
}
