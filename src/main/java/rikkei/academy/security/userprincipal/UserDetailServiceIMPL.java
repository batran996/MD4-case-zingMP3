package rikkei.academy.security.userprincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rikkei.academy.model.User;
import rikkei.academy.repository.IUserRepository;
import rikkei.academy.service.user.IUSerService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailServiceIMPL implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IUSerService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return UserPrinciple.build(user);

    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserPrinciple) {
            return userRepository.findByUsername(((UserPrinciple) principal).getUsername()).orElse(null);
        } else {
            return userRepository.findByUsername(String.valueOf(principal)).orElse(null);
        }
    }
    //HAM LAY RA USER HIEN TAI DE THUC HIEN THAO TAC VOI DB

}
