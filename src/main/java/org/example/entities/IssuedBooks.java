package org.example.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "issued_books")
public class IssuedBooks {

    @Id
    @Column(name = "issued_book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issuedBookId;

    @ManyToOne()
    @JoinColumn(name = "book_id")
    private Books book;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Students student;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "issued_date")
    private Date issuedDate;

    @Column(name = "return_date")
    private Date returnDate;

    public Long getIssuedBookId() {
        return issuedBookId;
    }

    public void setIssuedBookId(Long issuedBookId) {
        this.issuedBookId = issuedBookId;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
                    