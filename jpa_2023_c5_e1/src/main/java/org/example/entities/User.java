package org.example.entities;

import jakarta.persistence.*;
/*
* Not all tables in the database is an entities in JPA
* e.g. Here we have two tables in the database User and user_details but there combination
* create on single entity in our program.
* */

@Entity
@SecondaryTable(    // defines a secondary table name
        name = "user_details",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // here it is one entity user but in database tables it is two table user and
    // user_detail -> that is why? all tables are not entities and vice versal.
    @Column(table = "user_details") //bring this attribute from a secondary table
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
