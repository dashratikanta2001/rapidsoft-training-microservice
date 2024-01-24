package com.ss.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ss.dao.CrediantialDao;
import com.ss.entity.CredentialMaster;

@Service
public class CustumUserDetalisService implements UserDetailsService {

	@Autowired
	private CrediantialDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CredentialMaster credentialMaster = this.dao.getByUsername(username);

		if (credentialMaster != null && credentialMaster.getEmployee().getCompany().isActive() == true) {
			String designationName = credentialMaster.getEmployee().getDesignation().getName();

			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(designationName));
			return (UserDetails) new User(credentialMaster.getUsername(), credentialMaster.getPassword(), authorities);

		} else {
			    throw new UsernameNotFoundException("User not found or company not active");
			}
		}
	}

