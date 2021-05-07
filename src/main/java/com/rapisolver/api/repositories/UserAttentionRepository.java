package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.UserAttention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAttentionRepository extends JpaRepository<UserAttention, Long> {

    @Query("SELECT ua FROM UserAttention ua JOIN ua.attention a JOIN ua.user u WHERE a.id=?1 and u.id=?2")
    Optional<UserAttention> findByAttentionIdAndUserId(Long attentionId, Long userId);

}
