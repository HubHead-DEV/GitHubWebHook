package com.hubhead.github.webhook.rest.controller;

import com.hubhead.github.webhook.core.domain.Person;
import com.hubhead.github.webhook.core.events.ListPushersEvent;
import com.hubhead.github.webhook.core.events.PusherRegisteredEvent;
import com.hubhead.github.webhook.core.events.RegisterPusherEvent;
import com.hubhead.github.webhook.core.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 4:37 PM
 */
@Controller
@RequestMapping("/pushers")
public class PushersController {
    @Autowired
    GitHubService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> addPusher(@RequestBody Person person) {
        PusherRegisteredEvent registerPusherEvent=service.registerPusher(new RegisterPusherEvent(person));
        return new ResponseEntity<Person>(registerPusherEvent.getPerson(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Person[]> getPushers() {
        ListPushersEvent listPushersEvent=service.requestPushersList(null);
        return new ResponseEntity<Person[]>(listPushersEvent.getList().toArray(new Person[] {}), HttpStatus.OK);
    }
}
