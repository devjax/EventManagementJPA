package com.springdatajpa.EventManagementJPA.interfaces;

import com.springdatajpa.EventManagementJPA.entity.User;

public interface EventCountProjection {

    User getUser();

    Integer getCount();

}
