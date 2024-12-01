package edu.miu.cs489.studyplus.auth;

import edu.miu.cs489.studyplus.model.Role;

public record RegisterRequest (
        String firstName,
        String lastName,
        String username,
        String password,
        String email,
        String phoneNumber,
        Role role
){
}
