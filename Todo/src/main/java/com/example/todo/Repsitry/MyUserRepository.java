package com.example.todo.Repsitry;

import com.example.todo.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUserById(Integer id);
    MyUser findMyUserByUsername(String username);
}
