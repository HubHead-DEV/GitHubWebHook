package com.hubhead.github.webhook.core.events;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 3:05 PM
 */
public class PushStatisticsEvent {
    private final long started;
    private final int rejected;
    private final int allowed;
    private final long lastAttempt;

    public PushStatisticsEvent(long started, int rejected, int allowed, long lastAttempt) {
        this.started=started;
        this.rejected=rejected;
        this.allowed=allowed;
        this.lastAttempt=lastAttempt;
    }

    public long getStarted() {
        return started;
    }

    public int getRejected() {
        return rejected;
    }

    public int getAllowed() {
        return allowed;
    }

    public long getLastAttempt() {
        return lastAttempt;
    }
}
