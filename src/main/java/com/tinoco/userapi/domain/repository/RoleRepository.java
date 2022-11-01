package com.tinoco.userapi.domain.repository;

import java.util.Optional;

import com.tinoco.userapi.domain.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(String name);
}