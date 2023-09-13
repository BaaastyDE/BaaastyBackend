package de.baaasty.baaastybackend.user.service;

import de.baaasty.baaastybackend.user.entity.User;
import de.baaasty.baaastybackend.user.exception.UserCreateException;
import de.baaasty.baaastybackend.user.exception.UserNotFoundException;
import de.baaasty.baaastybackend.user.repository.UserRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Cacheable(key = "#uuid")
    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @CachePut(key = "#user.uuid")
    public User createUser(User user) {
        if (user.getUuid() == null) throw new UserCreateException();
        return userRepository.save(user);
    }

    @CachePut(key = "#user.uuid")
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getUuid())) throw new UserNotFoundException(user.getUuid());

        return userRepository.save(user);
    }

    @CacheEvict(key = "#uuid")
    public void deleteUser(UUID uuid) {
        if (!userRepository.existsById(uuid)) throw new UserNotFoundException(uuid);

        userRepository.deleteById(uuid);
    }

}
