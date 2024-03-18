package com.codegym.repository;

import com.codegym.model.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends CrudRepository<AppRole,Long> {
}
