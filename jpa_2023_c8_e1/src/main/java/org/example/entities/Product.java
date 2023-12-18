package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    @Id
    protected Long id;

    protected String name;

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}

/**
 * Inheritance strategies
 * 1.SINGLE TABLE - it will create a single table and
      in that table both child will be added and discriminated by
     discriminated column(DType) - which takes a class name by default.
   - it create null columns

 *2. JOINED - this creates 3(child + parent) tables, all the common column in one table
     that is all common column in parent table.
    - you have to write joints to get values. i.e Hibernate uses join by hind the seen.
      even if you don't write it.
    - no null columns
 *3.TABLE_PER_CLASS - having different table for each child.
   - no parent table created
    - however, one can say using JPQL in the context: SELECT p FROM Product p;
     then it will use union to select both. this because jpql is polymorphic and knows
    you're calling it child.
 */
