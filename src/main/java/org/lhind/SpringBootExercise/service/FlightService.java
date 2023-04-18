package org.lhind.SpringBootExercise.service;

import org.lhind.SpringBootExercise.dto.FlightDTO;
import org.lhind.SpringBootExercise.model.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    public Flight save(Flight f);
    public Optional<Flight> findById(Integer id);
    public List<Flight> findAll();
    public void delete(Flight f);
    public FlightDTO converter(Flight f);
    List<Integer> findAllBookings(Integer id);
    public List<Integer> findAllUsers(int id);

}