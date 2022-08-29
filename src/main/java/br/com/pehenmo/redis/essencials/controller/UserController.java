package br.com.pehenmo.redis.essencials.controller;

import br.com.pehenmo.redis.essencials.model.User;
import br.com.pehenmo.redis.essencials.repository.UserRepository;
import br.com.pehenmo.redis.essencials.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping
    public Map<String, User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User findAll(@PathVariable("id") String id){
        return userRepository.findById(id);
    }

    @PatchMapping()
    public User update(@RequestBody User user){
        return userRepository.update(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id){
        userRepository.delete(id);
    }



}
