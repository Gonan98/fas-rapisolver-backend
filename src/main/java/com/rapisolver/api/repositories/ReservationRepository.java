package com.rapisolver.api.repositories;

import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.entities.Reservation;
import com.rapisolver.api.exceptions.RapisolverException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation,Long> {

}
