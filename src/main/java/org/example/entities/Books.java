package org.example.entities;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "books")
public class Books {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_author")
    private String authorName;

    @Column(name = "initial_qty")
    private Long initialQty;

    @Column(name = "issued_qty")
    private Long issuedQty;

    @OneToMany(mappedBy = "book")
    private List<IssuedBooks> issuedBooks;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getInitialQty() {
        return initialQty;
    }

    public void setInitialQty(Long initialQty) {
        this.initialQty = initialQty;
    }

    public Long getIssuedQty() {
        return issuedQty;
    }

    public void setIssuedQty(Long issuedQty) {
        this.issuedQty = issuedQty;
    }

    public List<IssuedBooks> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<IssuedBooks> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }
}
                    