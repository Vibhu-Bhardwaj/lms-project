package org.example.repositories;

import org.example.entities.IssuedBooks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface IssuedBooksRepository extends JpaRepository<IssuedBooks, Long> {

    IssuedBooks findByBook_BookIdAndStudent_StudentId(Long bookId, Long studentId);

    @Query(nativeQuery = true, value = "select * from issued_books where return_date is null and date_add(issued_date, interval duration day) < curdate()")
    Page<List<IssuedBooks>> findDefaulters(Pageable pageable);
}
                    