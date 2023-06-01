package org.example.model;

import javax.persistence.*;

@Entity
@Table(name="Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="fullName")
    private String fullName;

    @Column(name="position")
    private String position;

    @Column(name="age")
    private Integer age;

    @Column(name="nationality")
    private String nationality;

    @Column(name="currentClub")
    private String currentClub;

    @Column(name="rating")
    private String rating;

    public Player() {
    }

    public Player(Integer id, String fullName, String position, Integer age, String nationality, String currentClub, String rating) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.age = age;
        this.nationality = nationality;
        this.currentClub = currentClub;
        this.rating = rating;
    }

    public Player(String fullName, String position, Integer age, String nationality, String currentClub, String rating) {
        this.fullName = fullName;
        this.position = position;
        this.age = age;
        this.nationality = nationality;
        this.currentClub = currentClub;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
