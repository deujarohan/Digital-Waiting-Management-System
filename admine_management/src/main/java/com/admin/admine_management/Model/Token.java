package com.admin.admine_management.Model;

public class Token {
    private Long id;
    private String userName;
    private String service;
    private String tokenNumber;
    private String status;

    public Token() {
    }

    public Token(Long id, String userName, String service, String tokenNumber, String status) {
        this.id = id;
        this.userName = userName;
        this.service = service;
        this.tokenNumber = tokenNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", user=" + userName +
                ", service=" + service +
                ", tokenNumber=" + tokenNumber +
                ", status='" + status + '\'' +
                '}';
    }

}

