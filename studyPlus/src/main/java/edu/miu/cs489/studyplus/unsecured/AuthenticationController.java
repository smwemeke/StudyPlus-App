package edu.miu.cs489.studyplus.unsecured;

import edu.miu.cs489.studyplus.auth.AuthenticationRequest;
import edu.miu.cs489.studyplus.auth.AuthenticationResponse;
import edu.miu.cs489.studyplus.auth.AuthenticationService;
import edu.miu.cs489.studyplus.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

   @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
       AuthenticationResponse authenticationResponse = authenticationService.login(request);
       return  ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
    }
}