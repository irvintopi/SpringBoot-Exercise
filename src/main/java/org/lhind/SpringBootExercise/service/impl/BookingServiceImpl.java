package org.lhind.SpringBootExercise.service.impl;

import org.lhind.SpringBootExercise.dto.BookingDTO;
import org.lhind.SpringBootExercise.model.Booking;
import org.lhind.SpringBootExercise.repository.BookingRepository;
import org.lhind.SpringBootExercise.service.BookingService;
import org.lhind.SpringBootExercise.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private FlightService flightService;

    public BookingServiceImpl(BookingRepository bookingRepository, FlightService flightService) {
        this.bookingRepository = bookingRepository;
        this.flightService = flightService;
    }

    public Booking save(Booking b){
        return bookingRepository.save(b);
    }
    public Optional<Booking> findById(Integer id){
        return bookingRepository.findById(id);
    }
    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }
    public void delete(Booking u){
        bookingRepository.delete(u);
    }

    @Override
    public BookingDTO converter(Booking b) {
        if (b == null) {
            return null;
        }

        BookingDTO bookingDTO = new BookingDTO(b.getUser().getUserName(), b.getStatus(), b.getBookingDate());
        return bookingDTO;
    }

    @Override
    public List<Integer> findAllFlights(Integer id) {
        return bookingRepository.findAllById(id);
    }
}