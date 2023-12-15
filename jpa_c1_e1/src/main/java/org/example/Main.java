package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(
                        new CustomPersistenceUnitInfo(), new HashMap<>());

        EntityManager em = emf.createEntityManager();   // represents the context - manager of the context
        try{
            em.getTransaction().begin();

            Product p = new Product();
            p.setId(2L);
            p.setName("Chocolate");

            em.persist(p);  //add to the context -> Not INSERT query  . we are not sure yet.

            em.getTransaction().commit();  // we know at end of the transaction
                                            // when we commit the context mirror to the database
        } finally {
            em.close();
        }

    }
}