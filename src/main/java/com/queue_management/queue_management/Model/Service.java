package com.queue_management.queue_management.Model;

public class Service {
    private Long id;
    private String serviceName;
    private String description;

    public Service() {
    }

    public Service(Long id, String serviceName, String description) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
