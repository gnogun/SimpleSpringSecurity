package com.gnogun.sample.repository;

import com.gnogun.sample.model.Salt;
import org.springframework.data.repository.CrudRepository;

public interface SaltRepository extends CrudRepository<Salt,Long> {

}