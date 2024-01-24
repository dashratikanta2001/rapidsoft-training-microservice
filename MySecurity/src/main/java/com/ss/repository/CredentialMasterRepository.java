package com.ss.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.entity.CredentialMaster;



@Repository
@Transactional
public interface CredentialMasterRepository extends JpaRepository<CredentialMaster, Integer> {

}
