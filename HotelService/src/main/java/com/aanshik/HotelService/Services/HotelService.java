package com.aanshik.HotelService.Services;


import com.aanshik.HotelService.Entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String hotelId);

    //TODO: Delete
    //TODO: Update
}
