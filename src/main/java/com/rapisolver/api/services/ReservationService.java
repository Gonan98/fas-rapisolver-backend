package com.rapisolver.api.services;


import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.exceptions.RapisolverException;

public interface ReservationService {

    ReservationDTO createReservation(CreateReservationDTO createReservationDTO)  throws RapisolverException;
}
