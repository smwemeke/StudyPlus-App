package edu.miu.cs489.studyplus.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}