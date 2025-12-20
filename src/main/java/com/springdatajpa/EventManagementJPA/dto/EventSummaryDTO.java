package com.springdatajpa.EventManagementJPA.dto;

import com.springdatajpa.EventManagementJPA.entity.EventDetails;
import com.springdatajpa.EventManagementJPA.entity.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSummaryDTO {
    Long id;
    String title;
    EventDetails eventDetails;
    //List<Session> sessions;
}
