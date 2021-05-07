package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

}
