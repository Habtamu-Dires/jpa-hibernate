package org.example.dto;

import org.example.entities.Student;

public record EnrollmentCountOfStudent(
        Student student,
        Long count
) {
}
