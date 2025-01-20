package com.grocery.booking.service;

import com.grocery.booking.model.CustomUserDetails;
import com.grocery.booking.model.Users;
import com.grocery.booking.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> dbUserData = usersRepository.findByName(username);
        //select * from users join roles where user.name=username;
        //Optional<Users> ==> our database data
        //Need to map database data to UserDetails object  //4 approaches

        dbUserData
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        //Map is going to Cloning
        //CustomUserDetails -> Our object and UserDetails -> Spring security Object
        // CustomerUserDetails springData =new CustomerDetails();
        // springData.setUserName("UserObject of Username");
        //springData.setPassword("UserObject of password");
        //springData.isActive("true")
        //


        return dbUserData
                .map(CustomUserDetails::new).get();


    }
}
