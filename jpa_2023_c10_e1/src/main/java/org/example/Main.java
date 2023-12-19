package org.example;

import jakarta.persistence.*;
import org.example.dto.EnrolledStudent;
import org.example.dto.EnrollmentCountOfStudent;
import org.example.entities.Student;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToUrl;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String puName = "pu-name";

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "none"); // create, none, update


        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            //INNER JOIN or JOIN
//            String jpql = """
//                    SELECT s, e FROM Student s INNER JOIN s.enrollments e
//                    """;
//            String jpql = """
//                    SELECT s, e FROM Student s JOIN s.enrollments e
//                    """;
//            String jpql = """
//                    SELECT s, e FROM Student s, Enrollment e WHERE s.id = e.student.id
//                    """;
//            String jpql = """
//                    SELECT s, e FROM Student s, Enrollment e WHERE s = e.student
//                    """;
            //the above four jpql have similar output

            //left join
//            String jpql = """
//                    SELECT s, e FROM Student s LEFT JOIN s.enrollments e
//                    """;
            //right join
//            String jpql = """
//                    SELECT s, e FROM Student s RIGHT JOIN s.enrollments e
//                    """;
//
//            TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
//
//            /**
//             * [s1,e1],
//             * [s2,e2
//             */
            //a better way for projection than using Object[] -> using dto

//            String jpql = """
//                    SELECT NEW org.example.dto.EnrolledStudent(s,e)
//                    FROM Student s JOIN s.enrollments e
//                    """;
//
//            TypedQuery<EnrolledStudent> q = em.createQuery(jpql, EnrolledStudent.class);
//
//            q.getResultList().forEach(o ->{
//                System.out.println(o.student() + " " + o.enrollment());  // records getter don't have get verb
//            });

            // inner quires - sub quires
//            String jpql = """
//                    SELECT s FROM Student s WHERE
//                    (SELECT COUNT(e) FROM Enrollment e WHERE e.student.id = s.id) > 1
//                    """;

            //
            String jpql = """
                    SELECT New org.example.dto.EnrollmentCountOfStudent(s, 
                        (SELECT count(e) FROM Enrollment e WHERE e.student = s)) 
                    FROM Student s      
                    """;

            TypedQuery<EnrollmentCountOfStudent> q =
                        em.createQuery(jpql, EnrollmentCountOfStudent.class);

            q.getResultList().forEach(o ->
                    System.out.println(o.student().getName() + " " + o.count()));

            em.getTransaction().commit(); // end of transaction
        } finally {
            em.close();
        }
    }
}



/**
 * Always remember that JPQL work with Objects.
 * StackOverFlowError: happened on bidirectional relationship inside a toString method.
   calling toString method of e.g Student make call to toString method of Enrollment,
   which then call toString method of Student and goes on -> make the stack to flow
 * -> can be solved by adjusting the toString methods to stop the cycle.
 * so careful with lombok (@Data) - sometimes you have to write you own toString methods
 * Or also, don't use bidirectional relationship, unless it must.
 */



/**
 CREATE TABLE Student (
 id BIGINT PRIMARY KEY,
 name VARCHAR(255) NOT NULL
 );

 CREATE TABLE Course (
 id BIGINT PRIMARY KEY,
 title VARCHAR(255) NOT NULL
 );

 CREATE TABLE Enrollment (
 id BIGINT PRIMARY KEY,
 enrollmentDate DATE NOT NULL,
 student_id BIGINT REFERENCES Student(id),
 course_id BIGINT REFERENCES Course(id)
 );

 INSERT INTO Student (id, name) VALUES (1, 'Alice');
 INSERT INTO Student (id, name) VALUES (2, 'Bob');
 INSERT INTO Student (id, name) VALUES (3, 'Charlie');

 INSERT INTO Course (id, title) VALUES (1, 'Mathematics');
 INSERT INTO Course (id, title) VALUES (2, 'Physics');
 INSERT INTO Course (id, title) VALUES (3, 'Chemistry');

 -- Alice enrolls in Mathematics and Physics
 INSERT INTO Enrollment (id, enrollmentDate, student_id, course_id) VALUES (1, '2023-10-10', 1, 1);
 INSERT INTO Enrollment (id, enrollmentDate, student_id, course_id) VALUES (2, '2023-10-09', 1, 2);

 -- Bob enrolls in Physics
 INSERT INTO Enrollment (id, enrollmentDate, student_id, course_id) VALUES (3, '2023-09-15', 2, 2);

 -- Charlie enrolls in Chemistry
 INSERT INTO Enrollment (id, enrollmentDate, student_id, course_id) VALUES (4, '2023-08-20', 3, 3);
 */