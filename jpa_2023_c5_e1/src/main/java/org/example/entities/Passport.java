package org.example.entities;


import jakarta.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;
    // the mappedBy doesn't create a person column in the table of database but
    // used programmatically to get the person that the passport related with.
    //The mappedBy show the opposite side of the owner of the relationship in the bidirectional relationship.
    // if you want to add column person_id(default name) in the table just use @OneToOne only
    // that is make this also owner of the relationship , dual owner or the only owner.
    @OneToOne(mappedBy = "passport")   // passport var name in the person table.
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
