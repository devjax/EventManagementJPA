package com.springdatajpa.EventManagementJPA;

import com.springdatajpa.EventManagementJPA.service.EventService;
import com.springdatajpa.EventManagementJPA.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventManagementJpaApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

	@Test
	void contextLoads() {
	}

    @Test
    void findActiveUsers(){
        System.out.println(userService.getActiveUsers());
    }

    @Test
    void findEventContaining(){
        System.out.println(eventService.getEventContaining("Spring"));
    }

    @Test
    void findTop2LatestEvents(){
        System.out.println(eventService.findTop2LatestEvents());
    }

    @Test
    void countEventsPerUser(){
        eventService.countEventsPerUser();
    }

    @Test
    void getUsersWithMultiEvents(){
        eventService.getUsersWithMultiEvents();
    }

    @Test
    void getEventSummary(){
        eventService.getEventSummary();
    }

    @Test
    void getEventTitleView(){
        eventService.getEventTitleView();

    }

    @Test
    void getPagedEvents(){
        eventService.getAllEventsPaged();
    }

    @Test
    void deleteEventByID(){
        eventService.deleteEvent(9L);
    }

    @Test
    void getEventByUser(){
        eventService.getEventByUser(2L);
    }


}
