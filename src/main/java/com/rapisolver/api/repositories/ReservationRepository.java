package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r JOIN r.user u WHERE u.id=?1")
    List<Reservation> findByUserId(Long userId);

}
