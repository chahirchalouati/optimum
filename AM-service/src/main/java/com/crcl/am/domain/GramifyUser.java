package com.crcl.am.domain;

import com.crcl.am.annotation.UniqueEmail;
import com.crcl.am.annotation.UniqueUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Document("users")
@Data
@Getter(AccessLevel.NONE)
@ToString
@EqualsAndHashCode
@FieldNameConstants()
public class GramifyUser implements UserDetails {
    @Id
    private String id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @UniqueUserName
    private String username;
    @Email
    @UniqueEmail
    @Indexed(unique = true)
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String avatar;
    @NotBlank
    private Gender gender;
    @Getter(AccessLevel.NONE)
    private Set<GramifyRole> roles = new HashSet<>();
    private boolean isAccountNonExpired = true;
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;

    public boolean isAdmin() {
        return this.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }

    public boolean isSuperAdmin() {
        return this.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream()
                .map(GramifyRole::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public GramifyUser setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public GramifyUser setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    public GramifyUser setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
        return this;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    public GramifyUser setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
        return this;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    public GramifyUser setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public GramifyUser setEnabled(boolean enabled) {
        isEnabled = enabled;
        return this;
    }

    public String getId() {
        return id;
    }

    public GramifyUser setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public GramifyUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public GramifyUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GramifyUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<GramifyRole> getRoles() {
        return roles;
    }

    public GramifyUser setRoles(Set<GramifyRole> roles) {
        this.roles = roles;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public GramifyUser setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public GramifyUser setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
