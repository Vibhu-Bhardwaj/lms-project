package org.example.services;

import org.example.dtos.PaginationDto;
import org.example.dtos.ResponseDto;
import org.example.dtos.StudentDto;
import org.example.entities.Students;
import jakarta.transaction.Transactional;
import org.example.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.example.repositories.StudentsRepository;

import java.util.List;

@Service

public class StudentService {

    @Autowired
    StudentsRepository studentsRepository;

    @Autowired
    Mapper mapper;

    @Autowired
    CommonUtils commonUtils;

    public ResponseDto<List<StudentDto>> getAllStudents(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getCurrentPage(), paginationDto.getPageSize(), Sort.by("studentId"));

        Page page = studentsRepository.findAll(pageable);

        List<StudentDto> studentDtos = mapper.studentEntitiesToStudentDtos(page.getContent());

        paginationDto.setTotalPages(page.getTotalPages());

        return commonUtils.successResponseWithDataAndPagination(studentDtos, paginationDto);
    }

    @Transactional
    public ResponseDto<Object> createStudent(StudentDto studentDto) {
        try {
            Students student = new Students();
            student = mapper.studentDtoToStudentEntity(studentDto);
            studentsRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return commonUtils.successResponseWithoutData();
    }

    @Transactional
    public ResponseDto<Object> updateStudent(StudentDto studentDto) {
        try {
            Students student = studentsRepository.findByStudentId(studentDto.getStudentId());
            student.setContact(studentDto.getContact());
            student.setDepartment(studentDto.getDepartment());
            student.setName(studentDto.getName());
            studentsRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return commonUtils.successResponseWithoutData();
    }

    @Transactional
    public ResponseDto<Object> deleteStudent(Long studentId){
        try {
            Students student = studentsRepository.findByStudentId(studentId);
            studentsRepository.delete(student);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return commonUtils.successResponseWithoutData();

    }
}
                    