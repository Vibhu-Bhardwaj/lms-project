package org.example.dtos;

import java.io.Serializable;

public class BookDto implements Serializable {
    
    private Long bookId;
    private String title;
    private String authorName;
    private Long initialQty;
    private Long issuedQty;

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
}
                    