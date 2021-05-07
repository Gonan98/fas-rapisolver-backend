package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.entities.Location;
import com.rapisolver.api.entities.Reservation;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.entities.UserAttention;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.LocationRepository;
import com.rapisolver.api.repositories.ReservationRepository;
import com.rapisolver.api.repositories.UserAttentionRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserAttentionRepository userAttentionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReservationDTO create(CreateReservationDTO createReservationDTO) throws RapisolverException {
        Location locationDB = locationRepository.findById(createReservationDTO.getLocationId()).orElseThrow(() -> new NotFoundException("LOCATION_NOT_FOUND"));
        User userDB = userRepository.findById(createReservationDTO.getUserId()).orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));
        UserAttention userAttentionDB = userAttentionRepository.findById(createReservationDTO.getUserAttentionId()).orElseThrow(() -> new NotFoundException("USER_ATTETION_NOT_FOUND"));

        try {
            Reservation reservation = new Reservation();
            reservation.setDatetime(createReservationDTO.getDatetime());
            reservation.setAddress(createReservationDTO.getAddress());
            reservation.setStatus(1);
            reservation.setCreatedAt(new Date());
            reservation.setLocation(locationDB);
            reservation.setUser(userDB);
            reservation.setUserAttention(userAttentionDB);
            reservation = reservationRepository.save(reservation);
            return MODEL_MAPPER.map(reservation, ReservationDTO.class);
        } catch (Exception e) {
            throw new InternalServerErrorException("CREATE_RESERVATION_ERROR");
        }
    }

    @Override
    public List<ReservationDTO> getAllByUser(Long userId) throws RapisolverException {
        try {
            List<Reservation> reservations = reservationRepository.findByUserId(userId);
            return reservations.stream().map(r -> MODEL_MAPPER.map(r, ReservationDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("GET_AlL_BY_USER_ERROR");
        }
    }
}
