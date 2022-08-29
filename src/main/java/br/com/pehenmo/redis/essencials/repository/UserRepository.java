package br.com.pehenmo.redis.essencials.repository;

import br.com.pehenmo.redis.essencials.model.User;

import java.util.Map;

public interface UserRepository {

    User save(User user);
    Map<String, User> findAll();
    User findById(String id);
    User update(User user);
    void delete(String id);

}
