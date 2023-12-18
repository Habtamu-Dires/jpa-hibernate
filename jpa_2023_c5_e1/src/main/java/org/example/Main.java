package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.entities.Passport;
import org.example.entities.Person;
import org.example.entities.User;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String puName = "pu-name";

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create-drop"); // create, none, update


        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Person person = new Person();
            person.setName("Ali");

            Passport passport = new Passport();
            passport.setNumber("XYZ987");

            person.setPassport(passport);
            passport.setPerson(person);

            em.persist(person);   // does the order mater no b/c persist is not insert
//            em.persist(passport); // persist  just create an entity in context.

            User user = new User();
            user.setName("Maria");  //create user table and put a name
            user.setDescription("user description"); //create user_detail table and put description
            em.persist(user);


//            TypedQuery<Person> q = em.createQuery(
//                    "SELECT p FROM Person p WHERE p.passport.number = :number",
//                    Person.class);
//            q.setParameter("number","ABC123");
//            System.out.println(q.getResultList());

            em.getTransaction().commit(); // end of transaction -> the real insert
        } finally {
            em.close();
        }
    }

}