package com.springdatajpa.EventManagementJPA.repository;

import com.springdatajpa.EventManagementJPA.dto.EventSummaryDTO;
import com.springdatajpa.EventManagementJPA.entity.Event;
import com.springdatajpa.EventManagementJPA.entity.User;
import com.springdatajpa.EventManagementJPA.interfaces.EventCountProjection;
import com.springdatajpa.EventManagementJPA.interfaces.EventTitleView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    ArrayList<Event> findByTitleContaining(String text);

    ArrayList<Event> findTop2ByOrderByCreatedOnDesc();

    @Query("select e.user as user, Count(e) as count from Event e group by e.user")
    List<EventCountProjection> findEventsPerUser();

    @Query("select e.user from Event e group by e.user  having Count(e) > 1")
    List<User> findUsersWithMultiEvents();

    @Query("select new com.springdatajpa.EventManagementJPA.dto.EventSummaryDTO(e.id, e.title, e.eventDetails) from Event e")
    List<EventSummaryDTO> findEventSummary();

    @Query("select e.title as title, e.tags as tags from Event e")
    List<EventTitleView> findEventTitleView();

    @Query("select e from Event e")
    Page<Event> findAllEventsPaged(Pageable pageable);

    List<Event> findByUser(User user);
}