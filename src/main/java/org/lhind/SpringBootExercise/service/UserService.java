package org.lhind.SpringBootExercise.service;

import org.lhind.SpringBootExercise.dto.UserDTO;
import org.lhind.SpringBootExercise.model.Booking;
import org.lhind.SpringBootExercise.model.User;
import org.lhind.SpringBootExercise.model.UserDetails;
import org.lhind.SpringBootExercise.dto.UserDTO;
import java.util.List;

public interface UserService {
    User save(User u);
    User findById(Integer id);
    List<UserDTO> findAll();
    void delete(User u);
    UserDTO converter(User u);
    UserDTO findUserByBookings(Booking b);

    UserDTO findAllByUserDetails(UserDetails ud);
    List<Integer> findAllBookings(Integer id);
    List<Integer> findAllFlights(int id);


}