package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Employee;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String puName = "pu-name";
        Map<?, ?> props = new HashMap<>();

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName),props);

        EntityManager em = emf.createEntityManager();  //the context & it's manager

        try{
            em.getTransaction().begin();

//            Employee e1 = em.find(Employee.class, 1);
              Employee e1 = new Employee();
              e1.setId(1);
              e1.setName("AB");
              e1.setAddress("Something");
            // entity not in context instead of querying it we create it and merge it
            //based on the primary key
              em.merge(e1);
//              em.detach(e1);
//              e1.setName("Merry");


//            em.persist();  -> Adding an entity in the context
//            em.find();     -> Finds by PK, GEt from DB
//            em.remove();   -> Marking entity for removal
//            em.merge();    -> Merge an entity from outside the context to the context.
//            em.refresh();  -> Mirror the context from the database
//            em.detach();   -> Taking the entity out of the context
//            em.getReference();
//            em.flush();  -> mirror now / commit it now don't wait till the end.

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}