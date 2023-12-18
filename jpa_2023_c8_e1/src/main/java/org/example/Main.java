package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Book;
import org.example.entities.ElectronicDevice;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String puName = "pu-name";

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
//        props.put("hibernate.hbm2ddl.auto", "create"); // create, none, update


        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//            Book book = new Book();
//            book.setId(1L);
//            book.setAuthor("John Doe");
//
//            ElectronicDevice device = new ElectronicDevice();
//            device.setId(2L);
//            device.setVoltage(220);
//
//            em.persist(book);
//            em.persist(device);

            //get all Product
            var sql = "SELECT P FROM Product P";
            em.createQuery(sql, Product.class)
                            .getResultList().forEach(System.out::println);

//            //get only Book - see no DType specified , DType is used by jpa
//            var sql = "SELECT P FROM Book P";
//            em.createQuery(sql, Book.class)
//                    .getResultList().forEach(System.out::println);



            em.getTransaction().commit(); // end of transaction
        } finally {
            em.close();
        }
    }
}