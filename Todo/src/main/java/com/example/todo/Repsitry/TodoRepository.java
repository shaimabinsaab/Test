package com.example.todo.Repsitry;

import com.example.todo.model.MyUser;
import com.example.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    Todo findTodoById(Integer id);
    List<Todo> findAllByMyUser(MyUser myUser);
}
