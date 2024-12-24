package org.example.controllers;

import org.example.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.example.services.BookService;
import org.example.services.LmsService;
import org.example.services.StudentService;
import org.example.services.UserService;
import java.util.List;

@RestController
public class LmsController {

    @Autowired
    LmsService lmsService;

    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseDto<LoginResDto> login(@RequestBody RequestDto<LoginReqDto> requestDto) {
        return lmsService.login(requestDto.getData());
    }

    @PostMapping("/getUsers")
    public ResponseDto<List<UserDto>> getUsers(@RequestBody RequestDto<UserDto> requestDto) {
        return userService.getUsers(requestDto.getPagination());
    }

    @PostMapping("/createUser")
    public ResponseDto<Object> createUser(@RequestBody RequestDto<UserDto> requestDto) {
        return userService.createUser(requestDto.getData());
    }

    @PostMapping("/updateUser")
    public ResponseDto<Object> updateUser(@RequestBody RequestDto<UserDto> requestDto) {
        return userService.updateUser(requestDto.getData());
    }

    @PostMapping("/deleteUser/{userId}")
    public ResponseDto<Object> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("/getBooks")
    public ResponseDto<List<BookDto>> getBooks(@RequestBody RequestDto<BookDto> requestDto) {
        return bookService.getAllBooks(requestDto.getPagination());
    }

    @PostMapping("/createBook")
    public ResponseDto<Object> createBook(@RequestBody RequestDto<BookDto> requestDto) {
        return bookService.createBook(requestDto.getData());
    }

    @PostMapping("/updateBook")
    public ResponseDto<Object> updateBook(@RequestBody RequestDto<BookDto> requestDto) {
        return bookService.updateBook(requestDto.getData());
    }

    @PostMapping("/deleteBook/{bookId}")
    public ResponseDto<Object> deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @PostMapping("/getStudents")
    public ResponseDto<List<StudentDto>> getStudents(@RequestBody RequestDto<StudentDto> requestDto) {
        return studentService.getAllStudents(requestDto.getPagination());
    }

    @PostMapping("/createStudent")
    public ResponseDto<Object> createStudent(@RequestBody RequestDto<StudentDto> requestDto) {
        return studentService.createStudent(requestDto.getData());
    }

    @PostMapping("/updateStudent")
    public ResponseDto<Object> updateStudent(@RequestBody RequestDto<StudentDto> requestDto) {
        return studentService.updateStudent(requestDto.getData());
    }

    @PostMapping("/deleteStudent/{studentId}")
    public ResponseDto<Object> deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @PostMapping("/issueBook")
    public ResponseDto<Object> issueBook(@RequestBody RequestDto<IssueReturnDto> requestDto) {
        return lmsService.issueBook(requestDto.getData());
    }

    @PostMapping("/returnBook")
    public ResponseDto<Object> returnBook(@RequestBody RequestDto<IssueReturnDto> requestDto) {
        return lmsService.returnBook(requestDto.getData());
    }

    @PostMapping("/viewIssuedBooks")
    public ResponseDto<List<IssuedBookDto>> viewIssuedBooks(@RequestBody RequestDto<Object> requestDto) {
        return lmsService.viewIssuedBooks(requestDto.getPagination());
    }

    @PostMapping("/viewDefaulters")
    public ResponseDto<List<IssuedBookDto>> viewDefaulters(@RequestBody RequestDto<Object> requestDto){
        return lmsService.viewDefaultersList(requestDto.getPagination());

    }
}
                    