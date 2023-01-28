package com.social.socialnetwork.repository;

import com.social.socialnetwork.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {

    ConfirmationToken findVerificationTokenByTokenAndUser_Email(String token, String email);
}
