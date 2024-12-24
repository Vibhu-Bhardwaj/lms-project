package org.example.repositories;

import org.example.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUserName(String userName);

    Users findByUserId(Long userId);

}
                    