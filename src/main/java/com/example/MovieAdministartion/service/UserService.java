package com.example.MovieAdministartion.service;

import com.example.MovieAdministartion.dto.UserDto;
import com.example.MovieAdministartion.model.User;
import com.example.MovieAdministartion.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User,Long> {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private PasswordEncoder PasswordEncoder;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        // TODO Auto-generated method stub
        return userRepository;
    }

    public User registerUser(UserDto request) {

        User user = new User();

        user.setUsername(request.getUsername());


        user.setEmail(request.getEmail());

        String encodedPassword = getEncodedPassword(request.getPassword());
        user.setPassword(encodedPassword);


        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public String getEncodedPassword(String password) {
        return PasswordEncoder.encode(password);
    }

    public long getNumberOfUsers() {
        return userRepository.count();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }




}