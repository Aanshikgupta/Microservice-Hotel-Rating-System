package com.aanshik.RatingService.Services.ServiceImpl;


import com.aanshik.RatingService.Entities.Rating;
import com.aanshik.RatingService.Exceptions.ResourceNotFoundException;
import com.aanshik.RatingService.Repository.RatingRepo;
import com.aanshik.RatingService.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepo ratingRepo;

    @Override
    public Rating saveRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }


    @Override
    public Rating getRating(String ratingId) {
        Rating rating = ratingRepo.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating", ratingId));
        return rating;
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
