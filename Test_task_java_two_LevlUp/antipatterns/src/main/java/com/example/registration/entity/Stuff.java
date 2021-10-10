package com.example.registration.entity;
/*
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="staff")
public class Stuff implements UserDetails {

    @Getter @Setter
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    //@Size(min=2, message = "Не меньше 5 знаков")
    private String userName;

    //@Size(min=2, message = "Не меньше 5 знаков")
    private String password;

    @Getter @Setter
    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Stuff(){
    }

    @Override
    public String getUsername(){
        return userName;
    }
    public void setUsername(String userName){
        this.userName = userName;
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
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public Set<Role> getRoles(){
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}*/
