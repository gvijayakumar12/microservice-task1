package com.vijay.authentication.Auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.authentication.Auth.model.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
	Optional<UserDetail> findByEmail(String email);
}
