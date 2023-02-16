package com.example.todo;

import com.example.todo.Repsitry.AuthRepoitory;
import com.example.todo.Repsitry.MyUserRepository;
import com.example.todo.Service.MyUserService;
import com.example.todo.model.MyUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyUserServiceTest {
    @InjectMocks
    MyUserService myUserService;
    @Mock
    MyUserRepository myUserRepository;
    @Mock
    AuthRepoitory authRepoitory;

    MyUser myUser, myUser1,myUser2, myUser3;
    List<MyUser> myusers;

    @BeforeEach
    void setUp() {
        myUser = new MyUser(null, "shaima", "1234", "CUSTOMER", null);
        myUser1 = new MyUser(null, "maha", "12345", "ADMIN", null);
        myUser2 = new MyUser(null, "reema", "12345", "ADMIN", null);
        myUser3 = new MyUser(null, "reem", "12345", "ADMIN", null);

        myusers = new ArrayList<>();
        myusers.add(myUser1);
        myusers.add(myUser2);
        myusers.add(myUser3);
    }

    @Test
    public void getAllUsers(){
        when(myUserRepository.findAll()).thenReturn(myusers);
        List<MyUser> userList = myUserService.getAllUsers();
        Assertions.assertEquals(3,userList.size());
        verify(myUserRepository,times(1)).findAll();
    }

    @Test
    public void UserById(){
        when(myUserRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        MyUser user = myUserService.getUser(myUser.getId());
        Assertions.assertEquals(user,myUser);
        verify(myUserRepository,times(1)).findMyUserById(myUser.getId());
    }

    @Test
    public void updateUser(){
        when(myUserRepository.findMyUserById(myUser1.getId())).thenReturn(myUser1);
        myUserService.updateUser(myUser2,myUser1.getId());

        verify(myUserRepository,times(1)).findMyUserById(myUser1.getId());
        verify(myUserRepository,times(1)).save(myUser2);
    }

    @Test
    public void delete(){
        // user
        when(myUserRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        //TO do
        myUserService.deleteUser(myUser.getId());

        verify(myUserRepository,times(1)).findMyUserById(myUser.getId());
        verify(myUserRepository,times(1)).delete(myUser);

    }

    @Test
    public void addUser(){
        myUserService.addUser(myUser1);
        verify(myUserRepository,times(1)).save(myUser1);
    }
}
