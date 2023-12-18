package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

//    // in case of oneToMany relationship the owner of the relationship(the one with the column in the database. as foreign key)
//    // should be the many side. in OneToOne you can decide which one is the owner. or both can be owners.
    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
