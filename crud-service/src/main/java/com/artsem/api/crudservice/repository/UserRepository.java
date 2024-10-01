package com.artsem.api.crudservice.repository;

import com.artsem.api.crudservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<UserDetails, Long>, JpaSpecificationExecutor<UserDetails> {
}