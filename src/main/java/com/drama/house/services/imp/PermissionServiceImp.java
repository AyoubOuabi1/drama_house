package com.drama.house.services.imp;

import com.drama.house.entities.PermissionEntity;
import com.drama.house.repositories.PermissionRepository;
import com.drama.house.services.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImp implements PermissionService {


    private final PermissionRepository permissionRepository;
    public PermissionServiceImp(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    @Override
    public PermissionEntity getPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public PermissionEntity savePermission(PermissionEntity permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(String name) {
        permissionRepository.deleteByName(name);
    }

}
