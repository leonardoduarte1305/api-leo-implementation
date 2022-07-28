package dev.leoduarte.api.leo.app.persistence.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "manager")
public class Manager extends Employee implements UserDetails {

	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Access> access = new ArrayList<>();

	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.access;
	}

	@Override public String getUsername() {
		return this.email;
	}

	@Override public boolean isAccountNonExpired() {
		return true;
	}

	@Override public boolean isAccountNonLocked() {
		return true;
	}

	@Override public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override public boolean isEnabled() {
		return true;
	}
}
