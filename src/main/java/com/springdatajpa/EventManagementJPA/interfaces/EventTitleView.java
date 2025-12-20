package com.springdatajpa.EventManagementJPA.interfaces;

import com.springdatajpa.EventManagementJPA.entity.Tag;

import java.util.List;

public interface EventTitleView {
    String getTitle();
    List<Tag> getTags();
}
