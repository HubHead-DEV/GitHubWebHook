package com.hubhead.github.webhook.core.service;

import com.hubhead.github.webhook.core.domain.Person;
import com.hubhead.github.webhook.core.events.*;
import com.hubhead.github.webhook.core.repository.PusherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 3:06 PM
 */

public class GitHubServiceEventHandler implements GitHubService {

    private static final String MASTER = "refs/heads/master";
    private static Logger LOGGER=Logger.getLogger(GitHubServiceEventHandler.class.getName());

    @Autowired
    private PusherRepository repository;

    public GitHubServiceEventHandler(PusherRepository repository) {
        this.repository = repository;
    }

    private final long started=System.currentTimeMillis();
    private int rejected;
    private int allowed;
    private long lastAttempt;

    @Override
    public PushAttemptStatusEvent attemptPush(PushAttemptReceivedEvent attemptReceivedEvent) {
        Person pusher = attemptReceivedEvent.getDetails().getPusher();
        String ref=attemptReceivedEvent.getDetails().getRef();
        LOGGER.log(Level.INFO,"Push attempt to "+ref+" received... Pusher="+ pusher);

        PushAttemptStatusEvent pushAttemptStatusEvent;

        if(MASTER.equals(ref) && !repository.contains(pusher)) {
            LOGGER.log(Level.SEVERE,"Push attempt rejected... Pusher="+ pusher);
            pushAttemptStatusEvent=new PushAttemptStatusEvent("rejected");
            rejected++;
        } else {
            LOGGER.log(Level.SEVERE,"Push attempt to "+ref+" allowed... Pusher="+ pusher);
            pushAttemptStatusEvent=new PushAttemptStatusEvent("allowed");
            allowed++;
        }

        lastAttempt=System.currentTimeMillis();

        return pushAttemptStatusEvent;
    }

    @Override
    public PushStatisticsEvent requestPushStatistics(RequestPushStatisticsEvent event) {

        return new PushStatisticsEvent(
                started,
                rejected,
                allowed,
                lastAttempt
        );
    }

    @Override
    public ListPushersEvent requestPushersList(RequestPushersEvent requestPushersEvent) {
        return new ListPushersEvent(repository.list());
    }

    @Override
    public PusherRegisteredEvent registerPusher(RegisterPusherEvent registerPusherEvent) {
        repository.add(registerPusherEvent.getPerson());
        return new PusherRegisteredEvent(registerPusherEvent.getPerson());
    }
}
