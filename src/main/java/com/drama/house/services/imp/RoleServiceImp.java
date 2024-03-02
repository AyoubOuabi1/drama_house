package com.drama.house.services.imp;

import com.drama.house.entities.RoleEntity;
import com.drama.house.repositories.RoleRepository;
import com.drama.house.services.RoleService;
import org.springframework.stereotype.Service;
@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public RoleEntity getRoleByName(String name) {
        return roleRepository.findByName(name);
    }


    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(String name) {
        roleRepository.deleteByName(name);
    }


}
