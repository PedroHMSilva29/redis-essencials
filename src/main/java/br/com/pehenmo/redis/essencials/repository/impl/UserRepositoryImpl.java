package br.com.pehenmo.redis.essencials.repository.impl;

import br.com.pehenmo.redis.essencials.model.User;
import br.com.pehenmo.redis.essencials.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class UserRepositoryImpl implements UserRepository {

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    private static final String CACHE_KEY = "user";

    @Autowired
    public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public User save(User user) {
        hashOperations.put(CACHE_KEY, user.getId(), user);
        return findById(user.getId());
    }

    @Override
    public Map<String, User> findAll() {
        return hashOperations.entries(CACHE_KEY);
    }

    @Override
    public User findById(String id) {
        return (User) hashOperations.get(CACHE_KEY, id);
    }

    @Override
    public User update(User user) {
        return save(user);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(CACHE_KEY, id);
    }
}
