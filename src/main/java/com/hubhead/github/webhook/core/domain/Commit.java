package com.hubhead.github.webhook.core.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ysokolovski
 * Date: 21/04/2014
 * Time: 3:19 PM
 */
public class Commit implements Serializable {
    private String[] added;
    private Person author;
    private Person committer;

    private boolean distinct;
    private String id;
    private String message;
    private String[] modified;
    private String[] removed;
    private String timestamp;
    private String url;

    public String[] getAdded() {
        return added;
    }

    public void setAdded(String[] added) {
        this.added = added;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Person getCommitter() {
        return committer;
    }

    public void setCommitter(Person committer) {
        this.committer = committer;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getModified() {
        return modified;
    }

    public void setModified(String[] modified) {
        this.modified = modified;
    }

    public String[] getRemoved() {
        return removed;
    }

    public void setRemoved(String[] removed) {
        this.removed = removed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
