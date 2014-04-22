package com.hubhead.github.webhook.core.repository;

import com.hubhead.github.webhook.core.domain.Person;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 4:11 PM
 */
public interface PusherRepository {
    List<Person> list();
    void add(Person person);
    void remove(Person person);
    boolean contains(Person pusher);
}
