package com.echo.taask.model;

import java.util.Collection;
import java.util.Date;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor@Builder
@Document @Getter@Setter
public class User implements UserDetails {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private byte[] image;
    private String userRole;
    private Date creationDate = new Date();
    private Date updateDate = new Date();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
