package org.example.services;

import org.example.dtos.BookDto;
import org.example.dtos.PaginationDto;
import org.example.dtos.ResponseDto;
import org.example.entities.Books;
import jakarta.transaction.Transactional;
import org.example.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.example.repositories.BooksRepository;

import java.util.List;

@Service

public class BookService {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    Mapper mapper;

    @Autowired
    CommonUtils commonUtils;

    public ResponseDto<List<BookDto>> getAllBooks(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getCurrentPage(), paginationDto.getPageSize(), Sort.by("bookId"));

        Page<Books> books = booksRepository.findAll(pageable);

        List<BookDto> bookDtos = mapper.bookEntitiesToBookDtos(books.getContent());
        paginationDto.setTotalPages(books.getTotalPages());

        return commonUtils.successResponseWithDataAndPagination(bookDtos, paginationDto);
    }

    @Transactional
    public ResponseDto<Object> createBook(BookDto bookDto) {
        try {
            Books book = new Books();
            book = mapper.bookDtoToBookEntity(bookDto);
            book.setIssuedQty(0L);
            booksRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return commonUtils.successResponseWithoutData();
    }

    @Transactional
    public ResponseDto<Object> updateBook(BookDto bookDto){
        try{
            Books book = booksRepository.findByBookId(bookDto.getBookId());
            book.setTitle(bookDto.getTitle());
            book.setAuthorName(bookDto.getAuthorName());
            if(bookDto.getInitialQty() < book.getInitialQty()) {
                if (book.getInitialQty() - book.getIssuedQty() < bookDto.getInitialQty())
                    throw new RuntimeException("Initial qty can't be set less than the available qty");
            }
            book.setInitialQty(bookDto.getInitialQty());
            }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return commonUtils.successResponseWithoutData();
        }

        @Transactional
    public ResponseDto<Object> deleteBook(Long bookId){
        try {
            Books book = booksRepository.findByBookId(bookId);
            booksRepository.delete(book);
        }catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return commonUtils.successResponseWithoutData();
    }
} 
                    