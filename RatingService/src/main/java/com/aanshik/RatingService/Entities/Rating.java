package com.aanshik.RatingService.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "micro_ratings")
public class Rating {

    @Id
    @Column(name = "rating_id")
    private String ratingId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "hotel_id")
    private String hotelId;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "feedback")
    private String feed;

}
