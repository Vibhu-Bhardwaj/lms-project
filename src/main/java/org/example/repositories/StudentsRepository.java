package org.example.repositories;

import org.example.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

    Students findByStudentId(Long studentId);

}
                    