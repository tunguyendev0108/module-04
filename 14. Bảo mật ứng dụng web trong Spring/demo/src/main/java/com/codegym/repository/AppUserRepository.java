package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser,Long> {
    AppUser findByName(String name);
}
