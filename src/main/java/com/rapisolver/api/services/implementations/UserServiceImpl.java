package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CreateUserDTO;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.entities.Role;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.RoleRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO create(CreateUserDTO createUserDTO) throws RapisolverException {

        Role roleDB = roleRepository.findByName("Cliente").orElseThrow(() -> new NotFoundException("No se encontro el rol Cliente"));

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
            throw new InternalServerErrorException("Error al crear el usuario");
        }

        return MODEL_MAPPER.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() throws RapisolverException {
        List<User> users;
        try {
            users = userRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al obtener todos los usuarios");
        }

        return users.stream().map(u -> MODEL_MAPPER.map(u, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) throws RapisolverException {
        User userDB = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontro usuario con Id:" + id));
        return MODEL_MAPPER.map(userDB, UserDTO.class);
    }

}
