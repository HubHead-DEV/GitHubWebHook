package com.hubhead.github.webhook.core.repository;

import com.hubhead.github.webhook.core.domain.Person;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 4:11 PM
 */
public class PusherMemoryRepository implements PusherRepository {

    private Map<String,Person> pushers=new Hashtable<String, Person>();

    @Override
    public List<Person> list() {
        return new ArrayList<Person>(pushers.values());
    }

    @Override
    public void add(Person person) {
        pushers.put(person.getEmail(),person);
    }

    @Override
    public void remove(Person person) {
        pushers.remove(person.getEmail());
    }

    @Override
    public boolean contains(Person pusher) {
        return pushers.containsKey(pusher.getEmail());
    }
}
