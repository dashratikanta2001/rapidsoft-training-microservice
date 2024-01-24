package com.ss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.entity.CredentialMaster;

@Repository
public interface CrediantialDao {


	Integer saveCrediantial(CredentialMaster credentialMaster);

	CredentialMaster getByUsername(String username);

}
