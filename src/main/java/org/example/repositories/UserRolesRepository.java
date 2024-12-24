package org.example.repositories;

import org.example.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    UserRoles findByUsers_userId(Long userId);

}
                    