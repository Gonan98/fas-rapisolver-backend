package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.UserAttention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttentionRepository extends JpaRepository<UserAttention, Long> {

}
