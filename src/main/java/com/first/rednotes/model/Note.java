package com.first.rednotes.model;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private String heading;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User author;

    public Note(){ }
    public Note(String text, String heading, User user) {
        this.text = text;
        this.heading = heading;
        this.author = user;
    }
    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String tag) {
        this.heading = heading;
    }
}
