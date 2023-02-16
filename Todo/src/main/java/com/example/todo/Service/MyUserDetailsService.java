package com.example.todo.Service;

import com.example.todo.Repsitry.MyUserRepository;
import com.example.todo.model.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=myUserRepository.findMyUserByUsername(username);
        if (myUser==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return myUser;
    }
}
