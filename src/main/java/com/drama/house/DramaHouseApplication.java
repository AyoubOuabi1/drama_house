package com.drama.house;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.drama.house.dtos.GenreDTO;
import com.drama.house.entities.PermissionEntity;
import com.drama.house.entities.RoleEntity;
import com.drama.house.services.GenreService;
import com.drama.house.services.PermissionService;
import com.drama.house.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class DramaHouseApplication {

    @Autowired
    RoleService roleService;

    @Autowired
    GenreService genreService;
    @Autowired
    PermissionService permissionService;
    public static void main(String[] args) {
        SpringApplication.run(DramaHouseApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
    @Bean
    CommandLineRunner commandLineRunner (){
        return args -> {
           // seedRole();
            //seedgnere();

        };
    }
    private void seedRole() {
        RoleEntity roleAdmin=roleService.saveRole(RoleEntity.builder().name("ADMIN").build());
        RoleEntity roleUser=roleService.saveRole(RoleEntity.builder().name("USER").build());
        permissionService.savePermission(PermissionEntity.builder().name("crud movies").role(roleAdmin).build());

    }

    private void seedgnere (){
        List<GenreDTO> genreDTOList = Arrays.asList(
                new GenreDTO("Comedy"),
                new GenreDTO("Action"),
                new GenreDTO("Thriller"),
                new GenreDTO("Horror"),
                new GenreDTO("Romance"),
                new GenreDTO("Sci-Fi"),
                new GenreDTO("Fantasy"),
                new GenreDTO("Mystery"),
                new GenreDTO("Adventure"),
                new GenreDTO("Animation"),
                new GenreDTO("Documentary")
        );

        // Saving each genre in the list
        genreDTOList.forEach(genreService::saveGenre);
    }
}
