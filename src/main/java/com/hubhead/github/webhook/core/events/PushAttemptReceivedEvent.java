package com.hubhead.github.webhook.core.events;

import com.hubhead.github.webhook.core.domain.WebHookEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 3:02 PM
 */
public class PushAttemptReceivedEvent {

    WebHookEvent webHookEventDetails;

    public PushAttemptReceivedEvent(WebHookEvent webHookEventDetails) {
        this.webHookEventDetails = webHookEventDetails;
    }

    public WebHookEvent getDetails() {
        return webHookEventDetails;
    }
}
