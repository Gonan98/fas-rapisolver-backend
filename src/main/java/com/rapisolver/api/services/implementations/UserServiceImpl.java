package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CreateUserDTO;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.entities.Role;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.exceptions.BadRequestException;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.RoleRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public UserDTO create(CreateUserDTO createUserDTO) throws RapisolverException {

        Role roleDB = roleRepository.findByName("ROLE_CUSTOMER").orElseThrow(() -> new NotFoundException("ROLE_CUSTOMER_NOT_FOUND"));

        if(userRepository.findByEmail(createUserDTO.getEmail()).isPresent())
            throw new BadRequestException("EMAIL_ALREADY_REGISTERED");

        User user = new User();
        user.setFirstname(createUserDTO.getFirstname());
        user.setLastname(createUserDTO.getLastname());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        user.setBirthdate(createUserDTO.getBirthdate());
        user.setPhone(createUserDTO.getPhone());
        user.setRole(roleDB);

        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            throw new InternalServerErrorException("CREATE_USER_ERROR");
        }

        return MODEL_MAPPER.map(user, UserDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> findAll() throws RapisolverException {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(u -> MODEL_MAPPER.map(u, UserDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("FIND_ALL_USERS_ERROR");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO findById(Long id) throws RapisolverException {
        User userDB = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontro usuario con Id:" + id));
        return MODEL_MAPPER.map(userDB, UserDTO.class);
    }

    @Transactional
    @Override
    public String buySubscription(Long id) throws RapisolverException {
        User userDB = userRepository.findById(id).orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));

        if (userDB.getRole().isCanPublish())
            throw new BadRequestException("USER_ALREADY_HAS_SUBSCRIPTION");

        Role roleDB = roleRepository.findByName("ROLE_SUPPLIER").orElseThrow(() -> new NotFoundException("ROLE_NOT_FOUND"));

        userDB.setRole(roleDB);
        userRepository.save(userDB);

        return "Ahora puede publicar sus servicios";
    }

}
