package org.example.entities;

import jakarta.persistence.*;

import java.net.Inet4Address;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    //Cascade.PERSIST -> when you persist person also persist passport.
    // fetch -> FetchType.EAGER (default) -> when you fetch person also fetch the associated passport with it
    // i.e use join query. but FetchType.LAZY -> when you fetch person it only fetch person data's no join query.
    //however, if you passport there will be another query to fetch a passport.
    //Optional -> true (default) allow to be null , optional-> false can't be null
    //orphanRemoval -> false (default) , orphanRemoval -> true if you delete this also delete it's child
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false, orphanRemoval = false)
    //@JoinColumn is always in the owner side of the relationship b/c it is this side that will have column in its table.
    @JoinColumn(name = "pass_port") // naming the column on person table to pass_port than default passport_id
    private Passport passport;

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

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport=" + passport +
                '}';
    }
}
