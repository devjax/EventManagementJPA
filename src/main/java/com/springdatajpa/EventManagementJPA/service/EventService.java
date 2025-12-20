package com.springdatajpa.EventManagementJPA.service;

import com.springdatajpa.EventManagementJPA.dto.EventSummaryDTO;
import com.springdatajpa.EventManagementJPA.entity.Event;
import com.springdatajpa.EventManagementJPA.entity.EventDetails;
import com.springdatajpa.EventManagementJPA.entity.Tag;
import com.springdatajpa.EventManagementJPA.entity.User;
import com.springdatajpa.EventManagementJPA.interfaces.EventCountProjection;
import com.springdatajpa.EventManagementJPA.interfaces.EventTitleView;
import com.springdatajpa.EventManagementJPA.repository.EventDetailsRepository;
import com.springdatajpa.EventManagementJPA.repository.EventRepository;
import com.springdatajpa.EventManagementJPA.repository.TagRepository;
import com.springdatajpa.EventManagementJPA.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventDetailsRepository eventDetailsRepository;
    private final TagRepository tagRepository;

    @Transactional
    public Event createNewEvent(Event event, Long userId, EventDetails eventDetails, ArrayList<Tag> tags){

        User user = userRepository.findById(userId).orElseThrow();

        if(event.getId() != null) throw new IllegalArgumentException("Event already exists");

        event.setUser(user);
        event.setEventDetails(eventDetails);
        event.setTags(tags);

        user.getEvents().add(event); //to maintain consistency

        eventRepository.save(event);

        return event;
    }

    @Transactional
    public Event createNewEvent(Event event, Long userId, Long eventDetailsId, ArrayList<Long> tagIds){

        //Event event = eventRepository.findById(eventId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        EventDetails eventDetails = eventDetailsRepository.findById(eventDetailsId).orElseThrow();

        event.setUser(user);
        event.setEventDetails(eventDetails);

        user.getEvents().add(event);
        eventDetails.setEvent(event);

        ArrayList<Tag> tags = new ArrayList<>();
        for (Long tagId : tagIds){
            Tag tag = tagRepository.findById(tagId).orElseThrow();
            tags.add(tag);
        }

        event.setTags(tags);

        eventRepository.save(event);

        return event;
    }

    public ArrayList<Event> getEventContaining(String text){

        return eventRepository.findByTitleContaining(text);
    }

    public ArrayList<Event> findTop2LatestEvents(){
        return eventRepository.findTop2ByOrderByCreatedOnDesc();
    }

    public void countEventsPerUser(){
        List<EventCountProjection> eventsPerUser = eventRepository.findEventsPerUser();

        for (EventCountProjection eventCount : eventsPerUser){
            System.out.println(eventCount.getUser().getName() + ":" + eventCount.getCount());
        }
    }

    public void getUsersWithMultiEvents(){
        List<User> fetchedUsers = eventRepository.findUsersWithMultiEvents();

        for (User user : fetchedUsers){
            System.out.println(user.getName());
        }
    }

    public void getEventSummary(){
        List<EventSummaryDTO> eventSummaryDTOList = eventRepository.findEventSummary();

        for(EventSummaryDTO dto : eventSummaryDTOList){
            System.out.println(dto.getId() + ", " + dto.getTitle() + ", " + dto.getEventDetails().getVenue()); //+ ", " + dto.getSessions().size()
        }
    }

    public void getEventTitleView(){
        List<EventTitleView> eventTitleViewList = eventRepository.findEventTitleView();

        for(EventTitleView eventTitleView : eventTitleViewList){
            System.out.println(eventTitleView.getTitle() + " -> " + eventTitleView.getTags().getFirst().getName());
        }
    }

    public void getAllEventsPaged(){
        Pageable pageable = PageRequest.of(0,2, Sort.by("title"));
        Page<Event> events = eventRepository.findAllEventsPaged(pageable);

        for (Event event : events){
            System.out.println(event.getTitle());
        }
    }

    @Transactional
    public void deleteEvent(Long id){
        Event event = eventRepository.findById(id).orElseThrow();
        eventRepository.delete(event);
    }

    public void getEventByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        List<Event> events = eventRepository.findByUser(user);

        System.out.println(events);
    }

}
