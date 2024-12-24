package org.example.repositories;

import org.example.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

    Books findByBookId(Long bookId);
}
                    