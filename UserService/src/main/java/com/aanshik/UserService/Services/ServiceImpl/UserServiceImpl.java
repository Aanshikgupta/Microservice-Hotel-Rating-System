package com.aanshik.UserService.Services.ServiceImpl;

import com.aanshik.UserService.Entities.Hotel;
import com.aanshik.UserService.Entities.Rating;
import com.aanshik.UserService.Entities.User;
import com.aanshik.UserService.Exceptions.ResourceNotFoundException;
import com.aanshik.UserService.Repository.UserRepo;
import com.aanshik.UserService.Services.UserService;
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
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {

        //Got the user from UserId here
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));

        String user_Id = user.getId();
        //fetch rating for above user from rating service
        //http://localhost:8083/ratings/users/{userID} for getting rating of a user using his/her ID

        ResponseEntity<Rating[]> ratingsForUser = restTemplate.getForEntity("http://localhost:8083/ratings/users/" + user_Id, Rating[].class);
        List<Rating> ratings = Arrays.asList(ratingsForUser.getBody());
        logger.info("{}", ratings);

        for (Rating rating : ratings) {
            Hotel hotel = getHotelByRating(rating.getHotelId());
            rating.setHotel(hotel);
        }

        user.setRatings(Arrays.asList(ratingsForUser.getBody()));
        User savedUser = userRepo.save(user);

        return savedUser;
    }

    private Hotel getHotelByRating(String hotelId) {
        Hotel hotel = restTemplate.getForEntity("http://localhost:8082/hotels/" + hotelId, Hotel.class).getBody();
        return hotel;
    }

}
