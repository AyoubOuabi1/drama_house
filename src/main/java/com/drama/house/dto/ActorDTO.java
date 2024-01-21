package com.drama.house.dto;

 import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private LocalDate dateOfBirth;
    private String nationality;
    private String bio;
 }
