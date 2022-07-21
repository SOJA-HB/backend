package fr.shewbii.sojahbbackend.service;

import fr.shewbii.sojahbbackend.security.model.UserEntity;
import fr.shewbii.sojahbbackend.security.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("INVALID USERNAME"));
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
