package edu.miu.cs489.studyplus.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    COORDINATOR(
            Set.of(Permission.COORDINATOR_WRITE,
                    Permission.COORDINATOR_READ)
    ),
    PARTICIPANT(
            Set.of(
                    Permission.PARTICIPANT_WRITE,
                    Permission.PARTICIPANT_READ
            )
    );
    private final Set<Permission> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities =
                new ArrayList<>();
        authorities.add(
                new SimpleGrantedAuthority("ROLE_" + name())
        );
        authorities.addAll(
                permissions.stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toList())
        );
        return authorities;
    }


}
