package com.tinoco.userapi.domain.models;

import com.tinoco.userapi.domain.dto.UserCreatedDto;
import com.tinoco.userapi.domain.dto.UserDto;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "USERAPI")
@Data
@Accessors(chain = true)
//@Builder
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Column(name = "token")
    private String token;
    @Column(name = "is_active")
    private Boolean active;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    private Set<PhoneEntity> phones = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "usrrole_id",
                    referencedColumnName = "id"))
    private List<RoleEntity> roles;

    @CreatedDate
    @Column(name = "created")
    private LocalDateTime created;
    @LastModifiedDate
    @Column(name = "modified")
    private LocalDateTime modified;

    @Override
    public String toString() {
        return ", email=" + email + ", id=" + id + ", lastLogin="
                + ", name=" + name + ", password=" + password + ", phones="
                + phones + ", token=" + token + "]";
    }

//    @Override
//    public int hashCode() {
//        return toString().hashCode() + 10;
//    }

    public UserDto toUserDto() {
        return new UserDto()
                .setEmail(email)
                .setName(name)
                .setPassword(password)
                .setPhones(phones.stream().map(PhoneEntity::phoneDto).collect(Collectors.toList()));
    }

    public UserCreatedDto toUserCreatedDto() {
        return new UserCreatedDto()
                .setId(id)
                .setCreated(created)
                .setToken(token)
                .setModified(modified)
                .setLastLogin(lastLogin)
                .setActive(active);
    }
}
