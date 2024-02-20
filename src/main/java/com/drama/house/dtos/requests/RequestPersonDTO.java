package com.drama.house.dtos.requests;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestPersonDTO {

    private String name;
    private MultipartFile imageFile;
    private String nationality;
    private String biography;
    private String birthDate;
}
