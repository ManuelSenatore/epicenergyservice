package com.buildweek.epicenergyservice.repositories;

import java.util.Optional;

import com.buildweek.epicenergyservice.entities.Role;
import com.buildweek.epicenergyservice.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleType(RoleType roleType);

}
