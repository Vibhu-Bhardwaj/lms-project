package org.example.services;

import org.example.dtos.PaginationDto;
import org.example.dtos.ResponseDto;
import org.example.dtos.UserDto;
import org.example.entities.Roles;
import org.example.entities.UserRoles;
import org.example.entities.Users;
import jakarta.transaction.Transactional;
import org.example.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.example.repositories.RolesRepository;
import org.example.repositories.UserRolesRepository;
import org.example.repositories.UsersRepository;

import java.util.List;

@Service

public class UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    Mapper mapper;

    @Autowired
    CommonUtils commonUtils;

    public ResponseDto<List<UserDto>> getUsers(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getCurrentPage(), paginationDto.getPageSize(), Sort.by("userId"));

        Page page = usersRepository.findAll(pageable);

        List<UserDto> userDtos = mapper.userEntitiesToUserDtos(page.getContent());

        paginationDto.setTotalPages(page.getTotalPages());

        return commonUtils.successResponseWithDataAndPagination(userDtos, paginationDto);
    }

    @Transactional
    public ResponseDto<Object> createUser(UserDto userDto) {
        try {
            Users user = new Users();
            user = mapper.userDtoToUserEntity(userDto);
            user.setPassword("librarianPassword");
            usersRepository.save(user);

            //create user roles
            UserRoles userRoles = new UserRoles();
            Roles role = rolesRepository.findByRoleName("LIBRARIAN");
            userRoles.setUsers(user);
            userRoles.setRoles(role);
            userRolesRepository.save(userRoles);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return commonUtils.successResponseWithoutData();
    }

    @Transactional
    public ResponseDto<Object> updateUser(UserDto userDto) {
        try {
            Users user = usersRepository.findByUserId(userDto.getUserId());
            user.setUserName(userDto.getUserName());
            user.setContact(userDto.getContact());
            user.setEmail(userDto.getEmail());
            usersRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return commonUtils.successResponseWithoutData();
    }

    @Transactional
    public ResponseDto<Object> deleteUser(Long userId){
        try {
            Users user = usersRepository.findByUserId(userId);
            UserRoles userRoles = userRolesRepository.findByUsers_userId(userId);
            userRolesRepository.delete(userRoles);
            usersRepository.delete(user);

        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return commonUtils.successResponseWithoutData();

    }
}
                    