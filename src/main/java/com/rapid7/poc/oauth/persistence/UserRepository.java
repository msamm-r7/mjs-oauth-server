package com.rapid7.poc.oauth.persistence;

import org.springframework.data.repository.CrudRepository;
import com.rapid7.poc.oauth.data.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

}
