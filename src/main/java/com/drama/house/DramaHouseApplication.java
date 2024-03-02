package com.drama.house;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.drama.house.entities.PermissionEntity;
import com.drama.house.entities.RoleEntity;
import com.drama.house.services.PermissionService;
import com.drama.house.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

@SpringBootApplication
public class DramaHouseApplication {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;
    public static void main(String[] args) {
        SpringApplication.run(DramaHouseApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }


   /* @Bean
    CommandLineRunner commandLineRunner (){
        return args -> {
            seedRole();

        };
    }*/
    private void seedRole() {
        RoleEntity roleAdmin=roleService.saveRole(RoleEntity.builder().name("ADMIN").build());
        RoleEntity roleUser=roleService.saveRole(RoleEntity.builder().name("USER").build());
        permissionService.savePermission(PermissionEntity.builder().name("crud movies").role(roleAdmin).build());

    }
}
