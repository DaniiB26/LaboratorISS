package org.example.model;

import javax.persistence.*;

@Entity
@Table(name="Request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="description")
    private String description;

    @Column(name="status")
    private String status;

    public Request() {
    }

    public Request(Integer id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Request(String description, String status) {
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
