package com.scott.assignment1;

/**
 * Created by Tech on 18/11/2015.
 */
public class Presenter {
    private long id;
    private String name;
    private String email;
    private String bio;
    private String affiliation;

    public Presenter(long id, String name, String email, String bio, String affiliation){
        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.affiliation = affiliation;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }
}
