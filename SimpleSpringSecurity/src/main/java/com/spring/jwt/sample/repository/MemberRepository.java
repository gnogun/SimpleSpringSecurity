package com.spring.jwt.sample.repository;

import com.spring.jwt.sample.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

    Member findByUsername(String username);

}