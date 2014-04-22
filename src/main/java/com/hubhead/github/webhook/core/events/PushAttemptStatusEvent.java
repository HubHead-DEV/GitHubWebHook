package com.hubhead.github.webhook.core.events;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 3:03 PM
 */
public class PushAttemptStatusEvent {
    private String status;

    public PushAttemptStatusEvent(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
