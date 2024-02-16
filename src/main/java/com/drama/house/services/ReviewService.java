package com.drama.house.services;

import com.drama.house.dtos.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAllReviews();
    ReviewDTO saveReview(ReviewDTO reviewDTO);
}

