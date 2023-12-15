package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Employee;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.sql.SQLOutput;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String puName = "pu-name";

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto","create");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            var e1 = new Employee();
            e1.setName("John");
            e1.setAddress("Address");
            em.persist(e1 );

//            //find vs getReference
//            var e1 = em.getReference(Employee.class, 1);
//            System.out.println(e1);
//
//            System.out.println("before refresh" + e1);
//            e1.setName("Daniel");     // by end the change will be mirrored to database
//            System.out.println("before refresh" + e1);
//            em.refresh(e1);    // take the data from the database (as it was), undo anychage done on contex
//
//            System.out.println("After refresh" + e1);

//            em.persist();   -> Adding an entity in the context
//            em.find()       -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
//            em.remove();    -> Marking entity for removal
//            em.merge();     -> Merges an entity from outside the context to the context.
//            em.refresh();   -> Mirror the context from the database
//            em.detach();    -> Taking the entity out of the context
//            em.getReference()

            em.getTransaction().commit(); // end of transaction
        } finally {
            em.close();
        }
    }

}