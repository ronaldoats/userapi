package com.tinoco.userapi.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "USRROLE")
@Data
@Accessors(chain = true)
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "role_name")
    private String roleName;

    @Override
    public String toString() {
        return "RoleEntity [description=" + description + ", id=" + id + ", roleName=" + roleName  + "]";
    }


    @Override
    public String getAuthority() {
        return roleName;
    }
}
