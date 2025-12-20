package com.springdatajpa.EventManagementJPA.service;

import com.springdatajpa.EventManagementJPA.entity.Status;
import com.springdatajpa.EventManagementJPA.entity.User;
import com.springdatajpa.EventManagementJPA.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ArrayList<User> getActiveUsers(){

        return userRepository.findByStatus(Status.ACTIVE);
    }
}
