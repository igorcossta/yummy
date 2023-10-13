package io.igorcossta.user;

import io.igorcossta.registration.DuplicateUsernameException;
import io.igorcossta.registration.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public static User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public User save(UserRegistrationDTO newUser) {
        if (existsUsername(newUser.getUsername())) {
            throw new DuplicateUsernameException("email %s already taken", newUser.getUsername());
        }
        return userRepository.save(userMapper.toEntity(newUser));
    }

    @Transactional
    public void activateUserById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user %s not found".formatted(userId)));
        user.setIsEnabled(true);
        userRepository.save(user);
    }
}
