package com.springdatajpa.EventManagementJPA;

import com.springdatajpa.EventManagementJPA.entity.Event;
import com.springdatajpa.EventManagementJPA.entity.EventDetails;
import com.springdatajpa.EventManagementJPA.entity.Tag;
import com.springdatajpa.EventManagementJPA.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class EventTests {

    @Autowired
    private EventService eventService;

    @Test
    public void newEventWithCascade(){
        Event event = Event.builder()
                .title("Spring Boot Basics")
                .build();

        EventDetails eventDetails = EventDetails.builder()
                .venue("Mumbai Hall 1")
                .capacity(100L)
                .contactPhone(9991001000L)
                .build();

        Tag tag1 = Tag.builder()
                //.id(301L)
                .name("Java")
                .build();

        Tag tag2 = Tag.builder()
                //.id(302L)
                .name("Spring")
                .build();

        ArrayList<Tag> tags = new ArrayList<>();

        tags.add(tag1);
        tags.add(tag2);

        var event1 = eventService.createNewEvent(event, 1L, eventDetails, tags);

        System.out.println(event1);

    }

    @Test
    public void newEvent(){

        Event event7 = Event.builder()
                .title("Spring Boot Basics")
                .build();

        ArrayList<Long> tags4 = new ArrayList<>();
        tags4.add(1L);
        tags4.add(2L);

        var event8 = eventService.createNewEvent(event7, 1L, 1L, tags4);
        System.out.println(event8);

        Event event1 = Event.builder()
                .title("Hibernate Deep Dive")
                .build();

        ArrayList<Long> tags1 = new ArrayList<>();
        tags1.add(3L);

        var event2 = eventService.createNewEvent(event1, 1L, 2L, tags1);
        System.out.println(event2);

        Event event3 = Event.builder()
                .title("React Workshop")
                .build();

        ArrayList<Long> tags2 = new ArrayList<>();
        tags2.add(4L);
        tags2.add(5L);

        var event4 = eventService.createNewEvent(event3, 2L, 3L, tags2);
        System.out.println(event4);

        Event event5 = Event.builder()
                .title("Cloud 101")
                .build();

        ArrayList<Long> tags3 = new ArrayList<>();
        tags3.add(6L);

        var event6 = eventService.createNewEvent(event5, 2L, 4L, tags3);
        System.out.println(6);
    }
}
