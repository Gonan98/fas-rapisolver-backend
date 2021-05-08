package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class RerservationController {

    @Autowired
    private ReservationService reservationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RapisolverResponse<ReservationDTO> create(@Valid @RequestBody CreateReservationDTO createReservationDTO) throws RapisolverException {
        return new RapisolverResponse<>(201,"CREATED","Reserva creada correctamente", reservationService.create(createReservationDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customer/{userId}")
    public RapisolverResponse<List<ReservationDTO>> getAllByUserId(@PathVariable Long userId) throws RapisolverException {
        return new RapisolverResponse<>(200,"OK","Reservas hechas por un usuario", reservationService.getAllByUser(userId));
    }
}
