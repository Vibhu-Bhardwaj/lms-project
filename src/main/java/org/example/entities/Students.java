package org.example.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_department")
    private String department;

    @Column(name = "student_contact_no")
    private String contact;

    @OneToMany(mappedBy = "student")
    private List<IssuedBooks> issuedBooks;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<IssuedBooks> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<IssuedBooks> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }
}
                    