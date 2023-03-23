package com.aanshik.HotelService.Services.ServiceImpl;

import com.aanshik.HotelService.Entities.Hotel;
import com.aanshik.HotelService.Exceptions.ResourceNotFoundException;
import com.aanshik.HotelService.Repository.HotelRepo;
import com.aanshik.HotelService.Services.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepo hotelRepo;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel", hotelId));
        return hotel;
    }
}
