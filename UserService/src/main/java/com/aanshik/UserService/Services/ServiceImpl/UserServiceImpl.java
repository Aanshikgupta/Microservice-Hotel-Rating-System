package com.aanshik.UserService.Services.ServiceImpl;

import com.aanshik.UserService.Entities.Hotel;
import com.aanshik.UserService.Entities.Rating;
import com.aanshik.UserService.Entities.User;
import com.aanshik.UserService.Exceptions.ResourceNotFoundException;
import com.aanshik.UserService.Repository.UserRepo;
import com.aanshik.UserService.Services.UserService;
import com.aanshik.UserService.Utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            user = getUser(user.getId());
        }
        return users;
    }

    @Override
    public User getUser(String userId) {

        //Got the user from UserId here
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));


        List<Rating> ratings = getRatingByUserId(userId);
        logger.info("{}", ratings);


        user.setRatings(ratings);
        User savedUser = userRepo.save(user);

        return savedUser;
    }

    public List<Rating> getRatingByUserId(String userId) {
        //fetch rating for above user from rating service
        //http://localhost:8083/ratings/users/{userID} for getting rating of a user using his/her ID
        ResponseEntity<Rating[]> ratingsForUser = restTemplate.getForEntity("http://" + Constants.RATING_SERVICE + "/ratings/users/" + userId, Rating[].class);
        List<Rating> ratings = Arrays.asList(ratingsForUser.getBody());

        for (Rating rating : ratings) {
            Hotel hotel = getHotelByHotelId(rating.getHotelId());
            rating.setHotel(hotel);
        }

        return ratings;
    }

    private Hotel getHotelByHotelId(String hotelId) {
        Hotel hotel = restTemplate.getForEntity("http://" + Constants.HOTEL_SERVICE + "/hotels/" + hotelId, Hotel.class).getBody();
        return hotel;
    }

}
