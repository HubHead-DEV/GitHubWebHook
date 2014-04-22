package com.hubhead.github.webhook.rest.controller;

import com.hubhead.github.webhook.core.domain.WebHookEvent;
import com.hubhead.github.webhook.core.events.PushAttemptReceivedEvent;
import com.hubhead.github.webhook.core.events.PushAttemptStatusEvent;
import com.hubhead.github.webhook.core.events.PushStatisticsEvent;
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
 * Time: 2:25 PM
 */
@Controller
@RequestMapping("/events")
public class WebHookController {
    @Autowired
    private GitHubService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PushAttemptStatusEvent> newEvent(@RequestBody WebHookEvent event) {
        PushAttemptStatusEvent statusEvent = service.attemptPush(new PushAttemptReceivedEvent(event));
        return new ResponseEntity<PushAttemptStatusEvent>(statusEvent, "allowed".equals(statusEvent.getStatus())?HttpStatus.CREATED:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<PushStatisticsEvent> eventStats() {
        return new ResponseEntity<PushStatisticsEvent>(service.requestPushStatistics(null),HttpStatus.OK);
    }
}
