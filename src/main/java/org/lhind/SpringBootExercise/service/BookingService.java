package org.lhind.SpringBootExercise.service;

import org.lhind.SpringBootExercise.dto.BookingDTO;
import org.lhind.SpringBootExercise.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookingService {
    public Booking save(Booking b);
    public Optional<Booking> findById(Integer id);
    public List<Booking> findAll();
    public void delete(Booking u);
    BookingDTO converter(Booking b);
    List<Integer> findAllFlights(Integer id);

}