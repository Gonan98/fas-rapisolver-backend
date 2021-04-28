package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query("select s from Score s where s.user.id=:userSourceId")
    List<Score> findByUserSource(@Param("userSourceId") Long userSourceId);

    @Query("select s from Score s where s.user.id=:userDestinationId")
    List<Score> findByUserDestination(@Param("userDestinationId") Long userDestinationId);
}
