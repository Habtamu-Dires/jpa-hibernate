package org.example.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;
    // Cascade operation can be done on either side form oneToMany or ManyToOne or both.
    // In case of Bidirectional mappedBy is always on opposite side of owner of the relationship
    @OneToMany(mappedBy="post", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> comments;   //can be Collection, List, Set

//    @OneToMany //this without @JoinColumn force sql to create a new join table.
//    @JoinColumn(name="post_id")   // In case of Unidirectional r/ship @JoinColumn is always on the owner except on @OneToMany,
//    // where we can use @JoinColumn that tells the owner is the many side and creates columns
//    on owner (in this case on comment table)
//    private List<Comment> comments;


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
