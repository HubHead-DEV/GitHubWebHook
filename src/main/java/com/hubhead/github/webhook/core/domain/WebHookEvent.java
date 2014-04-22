package com.hubhead.github.webhook.core.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 3:15 PM
 */
public class WebHookEvent implements Serializable {

    private String after;
    private String before;


    private Commit[] commits;
    private String compare;
    private boolean created;
    private boolean deleted;
    private boolean forced;

    private Commit head_commit;

    private Person pusher;
    private String ref;

    private Repository repository;

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public Commit[] getCommits() {
        return commits;
    }

    public void setCommits(Commit[] commits) {
        this.commits = commits;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isForced() {
        return forced;
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }

    public Commit getHead_commit() {
        return head_commit;
    }

    public void setHead_commit(Commit head_commit) {
        this.head_commit = head_commit;
    }

    public Person getPusher() {
        return pusher;
    }

    public void setPusher(Person pusher) {
        this.pusher = pusher;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }


}
