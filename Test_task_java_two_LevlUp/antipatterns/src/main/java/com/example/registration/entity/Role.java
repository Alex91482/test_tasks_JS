package com.example.registration.entity;

/*
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Getter @Setter
    @Id
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Stuff> stuff;

    public Role(){
    }
    public Role(Long id){
        this.id = id;
    }
    public Role(Long id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority(){
        return getName();
    }
}*/
