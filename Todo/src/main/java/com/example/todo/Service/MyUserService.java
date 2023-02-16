package com.example.todo.Service;

import com.example.todo.ApiException.ApiException;
import com.example.todo.Repsitry.MyUserRepository;
import com.example.todo.model.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepository myUserRepository;


    public List<MyUser> getAllUsers(){
        return myUserRepository.findAll();
    }

    public MyUser getUser(Integer id){
        MyUser myUser=myUserRepository.findMyUserById(id);
        if (myUser==null){
            throw new ApiException("User Not Found!");
        }
        return myUser;
    }


    public void addUser(MyUser newUser){
        newUser.setRole("USER");
        String hashedPassword=new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        myUserRepository.save(newUser);
    }

    public void deleteUser(Integer id){
        MyUser myUser=myUserRepository.findMyUserById(id);
        if(myUser==null){
            throw new ApiException("User Not Found");
        }
        myUserRepository.delete(myUser);
    }


    public void updateUser(MyUser newUser, Integer id){
        MyUser oldUser=myUserRepository.findMyUserById(id);

        newUser.setId(id);
        newUser.setRole(oldUser.getRole());
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));

        myUserRepository.save(newUser);
    }

}
