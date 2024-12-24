package org.example.services;

import org.example.entities.Books;
import org.example.entities.IssuedBooks;
import org.example.entities.Students;
import jakarta.transaction.Transactional;
import org.example.mappers.Mapper;
import org.example.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.example.repositories.BooksRepository;
import org.example.repositories.IssuedBooksRepository;
import org.example.repositories.StudentsRepository;
import org.example.security.CustomUser;
import org.example.security.JwtUtils;

import java.util.Date;
import java.util.List;

@Service

public class LmsService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    StudentsRepository studentsRepository;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    IssuedBooksRepository issuedBooksRepository;

    @Autowired
    Mapper mapper;

    public ResponseDto<LoginResDto> login(LoginReqDto loginReqDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReqDto.getUserName(), loginReqDto.getPassword()));

            CustomUser customUser = (CustomUser) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateJwt(customUser.getUsername());
            LoginResDto loginResDto = new LoginResDto();
            loginResDto.setAuthToken(jwtToken);
            return commonUtils.successResponseWithData(loginResDto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public ResponseDto<Object> issueBook(IssueReturnDto issueReturnDto) {
        try {
            Books book = booksRepository.findByBookId(issueReturnDto.getBookId());
            Students student = studentsRepository.findByStudentId(issueReturnDto.getStudentId());

            if (book == null)
                throw new RuntimeException("Book not found");

            if (student == null)
                throw new RuntimeException("student not found");

            if (book.getInitialQty() - book.getIssuedQty() <= 0)
                throw new RuntimeException("book not available");

            IssuedBooks alreadyIssuedBook = issuedBooksRepository.findByBook_BookIdAndStudent_StudentId(issueReturnDto.getBookId(), issueReturnDto.getStudentId());
            if (alreadyIssuedBook != null)
                throw new RuntimeException("book is already issued to student");

            book.setIssuedQty(book.getIssuedQty() + 1);
            booksRepository.save(book);

            IssuedBooks issuedBook = new IssuedBooks();
            issuedBook.setBook(book);
            issuedBook.setStudent(student);
            issuedBook.setIssuedDate(new Date());
            issuedBook.setDuration(issueReturnDto.getDuration());
            issuedBooksRepository.save(issuedBook);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return commonUtils.successResponseWithoutData();
    }

    @Transactional
    public ResponseDto<Object> returnBook(IssueReturnDto issueReturnDto) {
        try {
            Books book = booksRepository.findByBookId(issueReturnDto.getBookId());
            IssuedBooks issuedBook = issuedBooksRepository.findByBook_BookIdAndStudent_StudentId(issueReturnDto.getBookId(), issueReturnDto.getStudentId());

            if (book == null)
                throw new RuntimeException("book not found");

            if (issuedBook == null)
                throw new RuntimeException("either book id or student id is incorrect");

            book.setIssuedQty(book.getIssuedQty() - 1);
            booksRepository.save(book);

            issuedBook.setReturnDate(new Date());
            issuedBooksRepository.save(issuedBook);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return commonUtils.successResponseWithoutData();
    }

    public ResponseDto<List<IssuedBookDto>> viewIssuedBooks(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getCurrentPage(), paginationDto.getPageSize());

        Page page = issuedBooksRepository.findAll(pageable);

        List<IssuedBookDto> issuedBookDtos = mapper.issuedBookEntitiesToIssuedBookDto(page.getContent());

        paginationDto.setTotalPages(page.getTotalPages());

        return commonUtils.successResponseWithDataAndPagination(issuedBookDtos, paginationDto);
    }

    public ResponseDto<List<IssuedBookDto>> viewDefaultersList(PaginationDto paginationDto){
        Pageable pageable = PageRequest.of(paginationDto.getCurrentPage(), paginationDto.getPageSize());

        Page page = issuedBooksRepository.findDefaulters(pageable);

        List<IssuedBookDto> issuedBookDtos = mapper.issuedBookEntitiesToIssuedBookDto(page.getContent());

        paginationDto.setTotalPages(page.getTotalPages());
        return commonUtils.successResponseWithDataAndPagination(issuedBookDtos, paginationDto);

    }
}
                    