package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
public class User implements Entity, UserDetails
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, length = 16, nullable = false)
    private String name;

    @Column(length = 80, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<Role>();

    protected User()
    {
        /* Reflection instantiation */
    }

    public User(String name, String passwordHash)
    {
        this.name = name;
        this.password = passwordHash;
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Role> getRoles()
    {
        return this.roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    public void addRole(Role role)
    {
        this.roles.add(role);
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.getRoles();
    }

    @Override
    public String getUsername()
    {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
