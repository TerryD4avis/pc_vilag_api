package hu.pcvilag.app.service;

import hu.pcvilag.app.models.RoleEntity;
import hu.pcvilag.app.models.UserEntity;
import hu.pcvilag.app.repositories.RoleRepository;
import hu.pcvilag.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserEntity registerNewUser(UserEntity userEntity) {
        RoleEntity userRole = roleRepository.findByName("ROLE_USER");
        userEntity.setRole(userRole);
        return userRepository.save(userEntity);
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

}