package org.example.entities.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable   //means you can plug this type into entities.
public class StudentKey implements Serializable {

    private Long number;
    private String code;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "StudentKey{" +
                "number=" + number +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentKey that = (StudentKey) o;
        return Objects.equals(number, that.number) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, code);
    }
}
