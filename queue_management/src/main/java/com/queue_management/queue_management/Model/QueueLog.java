package com.queue_management.queue_management.Model;

public class QueueLog {
    private Long id;
    private Token token;
    private String action; // e.g., CREATED, CALLED, COMPLETED, CANCELLED
    private java.time.LocalDateTime timestamp;

    public QueueLog() {
    }

    public QueueLog(Long id, Token token, String action, java.time.LocalDateTime timestamp) {
        this.id = id;
        this.token = token;
        this.action = action;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public java.time.LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.time.LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "QueueLog{" +
                "id=" + id +
                ", token=" + token +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
