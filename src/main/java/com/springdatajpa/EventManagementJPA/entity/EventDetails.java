package com.springdatajpa.EventManagementJPA.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class EventDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String venue;

    private Long capacity;

    private Long contactPhone;

    @OneToOne(mappedBy = "eventDetails")
    @ToString.Exclude
    private Event event;
}
