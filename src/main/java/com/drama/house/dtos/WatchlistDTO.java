package com.drama.house.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WatchlistDTO {
    private Long id;
    private Long userId;
    private List<Long> movieIds;
    private List<Long> seriesIds;
}
