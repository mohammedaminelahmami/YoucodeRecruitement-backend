package com.example.youcodeRecruitment.Entity;

import com.example.youcodeRecruitment.Utils.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "candidate", catalog = "youcoderecruitment")
public class Candidate extends UserParentClass implements UserDetails {

    @Column(name = "status", columnDefinition = "boolean default false")
    private Boolean status;

    @OneToMany(mappedBy = "candidate")
    private Set<Notification> notificationEntities;

    @OneToMany(mappedBy = "candidate")
    private Set<Document> documents;

    @OneToOne
    @JoinColumn(name = "skills_id")
    private Skills skills;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(RoleEnum.ROLE_CANDIDATE.toString()));
    }

    @Override
    public String getUsername() {
        return getEmail();
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