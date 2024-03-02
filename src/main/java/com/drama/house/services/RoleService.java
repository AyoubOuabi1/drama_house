package com.drama.house.services;

import com.drama.house.entities.RoleEntity;

public interface RoleService {
    RoleEntity getRoleByName(String name);
    RoleEntity saveRole(RoleEntity role);
    void deleteRole(String name);
}
