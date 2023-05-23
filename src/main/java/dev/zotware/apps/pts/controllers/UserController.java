package dev.zotware.apps.pts.controllers.users;

import dev.zotware.apps.pts.entities.users.User;
import dev.zotware.apps.pts.exceptions.UserNotFoundException;
import dev.zotware.apps.pts.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    public static UserController INSTANCE;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        INSTANCE = this;
        this.userRepository = userRepository;
    }

    @PostMapping("/new")
    User newUser(@RequestBody User newUser) {

        final AtomicInteger id = new AtomicInteger();
        do {
            id.set(ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE));
        } while (UserController.INSTANCE.getAllUsers().parallelStream().anyMatch(user -> (user.getId() == id.get())));
        newUser.setId(id.get());

        return userRepository.save(newUser);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Integer id) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Integer id) {

        if (!userRepository.existsById(id)) throw new UserNotFoundException(id);

        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted success.";
    }

}
