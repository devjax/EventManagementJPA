package com.springdatajpa.EventManagementJPA.repository;

import com.springdatajpa.EventManagementJPA.entity.Status;
import com.springdatajpa.EventManagementJPA.entity.User;
import com.springdatajpa.EventManagementJPA.interfaces.EventCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    ArrayList<User> findByStatus(Status status);


}