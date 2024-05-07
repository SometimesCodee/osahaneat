package com.example.osahaneatMysql.Security;

import com.example.osahaneatMysql.Entity.Users;
import com.example.osahaneatMysql.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
//dung de authenticate nhung api khac ma chung khong duoc permit
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUserName(username);
        if(users == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(username, users.getPassword(), new ArrayList<>());
    }
}
