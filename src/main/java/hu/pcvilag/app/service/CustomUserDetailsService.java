package hu.pcvilag.app.service;

import hu.pcvilag.app.models.UserEntity;
import hu.pcvilag.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);

        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
        return CustomUserDetails.build(userEntity);
    }
}