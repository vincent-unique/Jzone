package org.trump.vincent.event;

public class LogEvent implements Event<String>{
    private String eventId;

    public String getEventId() {
        return eventId;
    }

    public LogEvent setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public LogEvent setMessage(String message) {
        this.message = message;
        return this;
    }

    private String message;
}