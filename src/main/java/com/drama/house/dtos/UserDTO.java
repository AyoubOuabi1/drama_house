package com.drama.house.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String profilePicture;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

}

