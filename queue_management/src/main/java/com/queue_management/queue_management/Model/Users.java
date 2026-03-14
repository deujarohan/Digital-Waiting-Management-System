package com.queue_management.queue_management.Model;


public class Users {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private int queueNumber;

    private Users() {
    }

    public Users(Long id, String name, String email, String phoneNumber, int queueNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.queueNumber = queueNumber;
    }

    public Long getId() {
        return id;
    }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", queueNumber=" + queueNumber +
                '}';
    }

}
