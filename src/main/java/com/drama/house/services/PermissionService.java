package com.drama.house.services;

import com.drama.house.entities.PermissionEntity;

public interface PermissionService {
    PermissionEntity getPermissionByName(String name);
    PermissionEntity savePermission(PermissionEntity permission);
    void deletePermission(String name);
}
