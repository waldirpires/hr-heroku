package com.newton.aaw.hr.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.newton.aaw.hr.domain.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	Optional<User> findOneByName(String name);
}
