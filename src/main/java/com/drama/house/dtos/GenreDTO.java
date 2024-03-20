package com.drama.house.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class GenreDTO {
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private String name;

    public GenreDTO(String name) {
        this.name = name;
    }
}
