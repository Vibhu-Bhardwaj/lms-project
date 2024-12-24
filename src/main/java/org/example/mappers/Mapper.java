package org.example.mappers;

import org.example.dtos.IssuedBookDto;
import org.example.dtos.UserDto;
import org.example.dtos.BookDto;
import org.example.dtos.StudentDto;
import org.example.entities.Books;
import org.example.entities.IssuedBooks;
import org.example.entities.Students;
import org.example.entities.Users;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    @Named("userEntityToUserDto")
    UserDto userEntityToUserDto(Users user);

    @IterableMapping(qualifiedByName = "userEntityToUserDto")
    List<UserDto> userEntitiesToUserDtos(List<Users> users);

    @Named("userDtoToUserEntity")
    Users userDtoToUserEntity(UserDto userDto);

    @Named("studentEntityToStudentDto")
    StudentDto studentEntityToStudentDto(Students student);

    @IterableMapping(qualifiedByName = "studentEntityToStudentDto")
    List<StudentDto> studentEntitiesToStudentDtos(List<Students> students);

    Students studentDtoToStudentEntity(StudentDto studentDto);

    @Named("bookEntityToBookDto")
    BookDto bookEntityToBookDto(Books book);

    @IterableMapping(qualifiedByName = "bookEntityToBookDto")
    List<BookDto> bookEntitiesToBookDtos(List<Books> books);

    Books bookDtoToBookEntity(BookDto bookDto);

    @Named("issuedBookEntityToIssuedBooksDto")
    @Mappings(value = {
            @Mapping(target = "bookTitle", source = "book.title"),
            @Mapping(target = "studentId", source = "student.studentId"),
            @Mapping(target = "studentName", source = "student.name"),
            @Mapping(target = "issuedDate", source = "issuedDate"),
            @Mapping(target = "returnDate", source = "returnDate"),
            @Mapping(target = "duration", source = "duration"),
    })
    IssuedBookDto issuedBookEntityToIssuedBooksDto(IssuedBooks issuedBook);

    @IterableMapping(qualifiedByName = "issuedBookEntityToIssuedBooksDto")
    List<IssuedBookDto> issuedBookEntitiesToIssuedBookDto(List<IssuedBooks> issuedBook);

}
                    