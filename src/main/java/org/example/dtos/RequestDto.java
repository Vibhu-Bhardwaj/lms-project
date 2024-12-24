package org.example.dtos;
import java.io.Serializable;

public class RequestDto<T> implements Serializable {

    private T data;
    private PaginationDto pagination;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PaginationDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDto pagination) {
        this.pagination = pagination;
    }
}
                    