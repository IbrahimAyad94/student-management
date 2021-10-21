package com.boubyan.studentmanagement.security.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.boubyan.studentmanagement.student.model.Student;


public class AppUserDetails implements UserDetails {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student student;
     
    public AppUserDetails(Student student) {
        this.student = student;
    }
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			return Collections.emptyList();
		 
	}

	@Override
	public String getPassword() {
		return student.getPassword();
	}

	@Override
	public String getUsername() {
		return student.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Long getStudentId() {
		return student.getId();
	}
	
	public Student getStudent() {
		return student;
	}
	
	public boolean isTokenExpired() {
		return false;
	}

}
