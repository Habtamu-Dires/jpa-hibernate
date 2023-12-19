package org.example;

import jakarta.persistence.*;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

/*//            String jpql = "SELECT p FROM Product p";  //p is instance of Product
            //SELECT p FROM product p ==> Fetch all the attribute of the product entity from the current context
            //SELECT * FROM product ==> Fetch all the columns from the table product
            //Note: only SELECT, UPDATE and DELETE. no INSERT in jpql
//            String jpql = "SELECT p FROM Product p WHERE p.price > 5";
            // to use parameter use colon with no space like :price and :name. Not -> : name (wrong, no space)
            String jpql = "SELECT p FROM Product p WHERE p.price > :price AND p.name LIKE :name";

            TypedQuery<Product> q = em.createQuery(jpql, Product.class);

            q.setParameter("price", 5);
            q.setParameter("name", "%a%"); //LIKE

//            List<Product> products = q.getResultList();
//            products.forEach(System.out::println);
            // One big mistake about using stream in here is. never filter after getting it as a stream
            // rather add the filter at WHERE clauses of the query.
            // most of the thing that is done in stream will be done queries
            Stream<Product> resultStream = q.getResultStream();*/

            //Arithmetic functions. AVG, SUM, MIN, MAX ....
            /*String jpql = "SELECT AVG(p.price) FROM Product p";

            TypedQuery<Double> q = em.createQuery(jpql, Double.class);

            Double avg = q.getSingleResult();

            System.out.println(avg);*/

            /*String jpql = "SELECT COUNT(p) FROM Product p";

            TypedQuery<Long> q = em.createQuery(jpql, Long.class);

            Long avg = q.getSingleResult();

            System.out.println(avg);*/

           /* String jpql = "SELECT p.name, p.price FROM Product p";

            TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
            q.getResultList().forEach(object ->{
                System.out.println(object[0] +" " + object[1]);
            });*/

            /*String jpql = "SELECT p.name, AVG(p.price) FROM Product p GROUP BY p.name";

            TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
            q.getResultList().forEach(object ->{
                System.out.println(object[0] +" " + object[1]);
            });*/

            //select product that doesn't exist
            String jpql = "SELECT p FROM Product p WHERE p.name LIKE 'Candy'";

            TypedQuery<Product> q = em.createQuery(jpql, Product.class);
            Product p = null;
            try {
                //getSingleResult is designed not to return null but if nothing is there to throw exception.
                p = q.getSingleResult(); //
            } catch (NoResultException e){
                System.out.println("No result found");
            }


            System.out.println(p);


            em.getTransaction().commit(); // end of transaction
        } finally {
            em.close();
        }
    }
}

/**
 * JPQL -> java persistent query language
        -> a query language for objects in the context.
        -> which finally it translate to a native query language.

 */