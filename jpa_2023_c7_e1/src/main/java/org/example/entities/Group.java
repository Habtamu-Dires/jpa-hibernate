package org.example.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name= "user_group",
            joinColumns = @JoinColumn(name="group_id"),  //the current table attribute
            inverseJoinColumns = @JoinColumn(name="user_id") // opposite side of the relationship
    )
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
