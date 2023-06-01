package de.baaasty.baaastybackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.baaasty.baaastybackend.entity.User;
import de.baaasty.baaastybackend.repository.UserRepository;
import de.baaasty.baaastybackend.exception.user.UserAlreadyExistsException;
import de.baaasty.baaastybackend.exception.user.UserNotFoundException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Cacheable(key = "#uuid")
    public Optional<User> getUserByUuid(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @CachePut(key = "#user.uuid")
    public User saveUser(User user) {
        if (userRepository.existsById(user.getUuid()))
            throw new UserAlreadyExistsException(user.getUuid());

        return userRepository.save(user);
    }

    @CachePut(key = "#uuid")
    public User updateUser(UUID uuid, User user) {
        if (!userRepository.existsById(uuid))
            throw new UserNotFoundException(uuid);

        return userRepository.save(user);
    }

    @CacheEvict(key = "#uuid")
    public void deleteUser(UUID uuid) {
        if (!userRepository.existsById(uuid))
            throw new UserNotFoundException(uuid);

        userRepository.deleteById(uuid);
    }
}
