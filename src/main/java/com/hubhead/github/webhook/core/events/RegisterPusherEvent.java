package com.hubhead.github.webhook.core.events;

import com.hubhead.github.webhook.core.domain.Person;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 4:08 PM
 */
public class RegisterPusherEvent {
    private Person person;

    public Person getPerson() {
        return person;
    }

    public RegisterPusherEvent(Person person) {
        this.person = person;
    }
}
