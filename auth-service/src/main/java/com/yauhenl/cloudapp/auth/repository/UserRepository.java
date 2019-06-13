package com.yauhenl.cloudapp.auth.repository;

import com.yauhenl.cloudapp.auth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
