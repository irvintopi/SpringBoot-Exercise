package org.lhind.SpringBootExercise.service.impl;

import org.lhind.SpringBootExercise.dto.FlightDTO;
import org.lhind.SpringBootExercise.model.Flight;
import org.lhind.SpringBootExercise.repository.FlightRepository;
import org.lhind.SpringBootExercise.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{

    private FlightRepository flightRepository;
    private FlightDTO flightDTO;

    FlightServiceImpl(FlightRepository flightRepository, FlightDTO flightDTO){
        this.flightRepository = flightRepository;
        this.flightDTO = flightDTO;
    }

    public Flight save(Flight f) { return flightRepository.save(f); }

    public Optional<Flight> findById(Integer id) {
        return flightRepository.findById(id);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void delete(Flight f) { flightRepository.delete(f); }

    @Override
    public FlightDTO converter(Flight f) {
        if (f == null) {
            return null;
        }
        FlightDTO flightDTO = new FlightDTO(f.getAirline(), f.getOrigin(), f.getDestination(), f.getStatus(), f.getDepartureDate(), f.getArrivalDate());
        return flightDTO;
    }

    @Override
    public List<Integer> findAllBookings(Integer id) {
        return null;
    }

    @Override
    public List<Integer> findAllUsers(int id) {
        return null;
    }
}
