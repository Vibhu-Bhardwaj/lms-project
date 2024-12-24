package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @Column(name = "user_roles_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRolesId;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Roles roles;

    public Long getUserRolesId() {
        return userRolesId;
    }

    public void setUserRolesId(Long userRolesId) {
        this.userRolesId = userRolesId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
                    