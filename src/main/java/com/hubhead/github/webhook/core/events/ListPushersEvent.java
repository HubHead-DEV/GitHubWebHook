package com.hubhead.github.webhook.core.events;

import com.hubhead.github.webhook.core.domain.Person;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 4:09 PM
 */
public class ListPushersEvent {

    private List<Person> list;
    public ListPushersEvent(List<Person> list) {
        this.list= Collections.unmodifiableList(list);
    }

    public List<Person> getList() {
        return list;
    }
}
