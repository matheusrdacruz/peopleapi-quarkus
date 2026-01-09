package br.com.mrcruz.service;


import java.util.List;
import java.util.UUID;

import br.com.mrcruz.domain.User;
import br.com.mrcruz.exceptions.UserNotFoundException;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class UserService {

    public User create(User user) {
        User.persist(user);
        return user;
    }

    public List<User> findAll(Integer page, Integer size) {
        return User.findAll()
                .page(page, size)
                .list();
    }

    public User findById(UUID id) {
        return (User) User.findByIdOptional(id).orElseThrow(UserNotFoundException::new);
    }

    public User update(UUID id, User userUpdate) {
        var user = this.findById(id);
        user.setEmail(userUpdate.getEmail());
        user.setUsername(userUpdate.getUsername());
        User.persist(user);
        return user;
    }

    public void deleteById(UUID id) {
        var user = this.findById(id);
        User.deleteById(user.getId());
    }
}
