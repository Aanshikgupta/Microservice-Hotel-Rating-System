package com.aanshik.RatingService.Services;




import com.aanshik.RatingService.Entities.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> getAllRatings();

    Rating getRating(String ratingId);

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

    //TODO: Delete
    //TODO: Update
}
