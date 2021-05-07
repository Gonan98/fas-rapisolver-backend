package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface ReservationService {

    ReservationDTO create(CreateReservationDTO createReservationDTO) throws RapisolverException;
    List<ReservationDTO> getAllByUser(Long userId) throws RapisolverException;

}
