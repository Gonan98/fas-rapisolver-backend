package com.rapisolver.api.services.implementations;

import com.rapisolver.api.dtos.CreateRoleDTO;
import com.rapisolver.api.dtos.RoleDTO;
import com.rapisolver.api.entities.Role;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.RoleRepository;
import com.rapisolver.api.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public RoleDTO create(CreateRoleDTO createRoleDTO) throws RapisolverException {
        Role role = new Role();
        role.setName(createRoleDTO.getName());
        role.setCanPublish(createRoleDTO.isCanPublish());

        try {
            role = roleRepository.save(role);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al crear un rol");
        }
        return MODEL_MAPPER.map(role, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findAll() throws RapisolverException {
        List<Role> roles;
        try {
            roles = roleRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al obtener todos los roles");
        }
        return roles.stream().map(role -> MODEL_MAPPER.map(role, RoleDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public RoleDTO update(Long id, CreateRoleDTO createRoleDTO) throws RapisolverException {

        Role roleDB = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Rol a actualizar no encontrado"));

        roleDB.setName(createRoleDTO.getName());
        roleDB.setCanPublish(createRoleDTO.isCanPublish());

        try {
            roleDB = roleRepository.save(roleDB);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error de base de datos al actualizar el rol");
        }

        return MODEL_MAPPER.map(roleDB, RoleDTO.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws RapisolverException {
        roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Rol a eliminar no encontrado"));

        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error de base de datos al eliminar el rol");
        }
    }
}
