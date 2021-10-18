package com.spring.jwt.sample.repository;

import com.spring.jwt.sample.model.Salt;
import org.springframework.data.repository.CrudRepository;

public interface SaltRepository extends CrudRepository<Salt,Long> {

}