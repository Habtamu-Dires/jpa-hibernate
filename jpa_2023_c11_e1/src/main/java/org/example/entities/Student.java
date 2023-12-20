package org.example.entities;

import jakarta.persistence.*;
import jdk.jfr.Name;

import java.sql.Array;
import java.util.List;

/**
 * Use named query for most repetitive quires.
 * The named query can be placed in any entities, for example the below can be placed on
   Course entity. But better to put somewhere where it fits the logic.
 * The name should be unique in the application level.
 */

@Entity
@NamedQueries(
        @NamedQuery(name = "getEnrolledStudents",
        query = """
                SELECT s FROM Student s, Enrollment e WHERE s.id = e.student.id
                """)
)
public class Student {

    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
