package org.example.services;
import org.example.dtos.PaginationDto;
import org.example.dtos.ResponseDto;
import org.springframework.stereotype.Component;

@Component

public class CommonUtils {

    public <T> ResponseDto<T> successResponseWithoutData() {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setStatus("success");
        return responseDto;
    }

    public <T> ResponseDto<T> successResponseWithDataAndPagination(T data, PaginationDto paginationDto) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setStatus("success");
        responseDto.setData(data);
        responseDto.setPagination(paginationDto);
        return responseDto;
    }

    public <T> ResponseDto<T> successResponseWithData(T data){
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setStatus("success");
        responseDto.setData(data);
        return responseDto;

    }
}
                    