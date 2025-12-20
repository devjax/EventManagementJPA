package com.springdatajpa.EventManagementJPA.repository;

import com.springdatajpa.EventManagementJPA.entity.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> {
}