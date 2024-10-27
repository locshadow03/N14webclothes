package com.webclothes.webclothesservice.repository;

import com.webclothes.webclothesservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
