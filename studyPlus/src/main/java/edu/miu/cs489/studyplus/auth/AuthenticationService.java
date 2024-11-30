package edu.miu.cs489.studyplus.auth;

import edu.miu.cs489.studyplus.config.JwtService;
import edu.miu.cs489.studyplus.model.User;
import edu.miu.cs489.studyplus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse register(RegisterRequest registerRequest){
        // construct user object from RegisterRequest
        User user = new User(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.username(),
                registerRequest.email(),
                registerRequest.phoneNumber(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.role()
        );
        // save user
        User registeredUser = userRepository.save(user);
        // Generate token
        String token = jwtService.generateToken(registeredUser);
        return new AuthenticationResponse(token);
    }
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         authenticationRequest.username(),
                         authenticationRequest.password()
                 )
         );
         // Now authentication is succesful
        // Generate token for authenticated user

        User authenticatedUser = (User) userDetailsService.loadUserByUsername(authenticationRequest.username());
      //  User authenticatedUser = userRepository.findByUsername(authenticationRequest.username()).orElseThrow(() -> new UsernameNotFoundException("not ofund"));
//        Principal principal = (Principal) authentication.getPrincipal() ;
      //  User authenticatedUser = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(authenticatedUser);
        return new AuthenticationResponse(token);
    }
}
