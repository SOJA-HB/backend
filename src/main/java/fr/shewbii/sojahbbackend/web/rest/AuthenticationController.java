package fr.shewbii.sojahbbackend.web.rest;

import fr.shewbii.sojahbbackend.security.helper.TokenHelper;
import fr.shewbii.sojahbbackend.security.model.TokenResponse;
import fr.shewbii.sojahbbackend.security.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class AuthenticationController {

    private final TokenHelper tokenHelper;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(TokenHelper tokenHelper, AuthenticationManager authenticationManager) {
        this.tokenHelper = tokenHelper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity validateLogin(@RequestBody UserDto userDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        if (authenticate.isAuthenticated()) {
            User user = (User) authenticate.getPrincipal();
            return new ResponseEntity<>(tokenHelper.generateToken(user.getUsername(), (Collection<GrantedAuthority>) authenticate.getAuthorities()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("INVALID USERNAME/PASSWORD", HttpStatus.BAD_REQUEST);
        }
    }

}
