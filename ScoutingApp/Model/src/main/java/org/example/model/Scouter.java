package org.example.model;

import javax.persistence.*;

@Entity
@Table(name="Scouter")
public class Scouter {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public Scouter(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Scouter() {
    }

    public Scouter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
