package com.pro.ukart.Entities;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id",unique = true)
    private Long id;

    
    @Column(name="email", unique=true,nullable = false)
    @NotEmpty(message="Email is required")
    @Email(message="Valid email is required")
    private String email;

    @Column(name="password", unique=true, nullable = false)
    @NotEmpty(message = "Passwors is required")
    @Size(min= 8, message="Password must be at least 8 character long")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns =  {@JoinColumn(name="role_id")}
    )

    private Set<UserRole> authorities;

    
    public User() {
        super();
        authorities = new HashSet<>();
    }

    public User(String email, String password, Set<UserRole> authorities) {
        super();
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public void setAuthorities(Set<UserRole> authorities)
    {
        this.authorities = authorities;

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
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
}
