package com.drama.house.dtos;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PersonDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private String nationality;
    private String biography;
    private Date birthDate;
}
