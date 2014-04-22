package com.hubhead.github.webhook.core.service;

import com.hubhead.github.webhook.core.events.*;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 3:06 PM
 */
public interface GitHubService {
    PushAttemptStatusEvent attemptPush(PushAttemptReceivedEvent attemptReceivedEvent);
    PushStatisticsEvent requestPushStatistics(RequestPushStatisticsEvent event);
    ListPushersEvent requestPushersList(RequestPushersEvent requestPushersEvent);
    PusherRegisteredEvent registerPusher(RegisterPusherEvent registerPusherEvent);
}
