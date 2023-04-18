package org.lhind.SpringBootExercise.service.impl;
import org.lhind.SpringBootExercise.dto.UserDTO;
import org.lhind.SpringBootExercise.model.Booking;
import org.lhind.SpringBootExercise.model.User;
import org.lhind.SpringBootExercise.model.UserDetails;
import org.lhind.SpringBootExercise.repository.UserRepository;
import org.lhind.SpringBootExercise.service.BookingService;
import org.lhind.SpringBootExercise.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BookingService bookingService;

    UserServiceImpl(UserRepository userRepository, BookingService bookingService) {
        this.userRepository = userRepository;
        this.bookingService = bookingService;
    }

    @Override
    public User save(User u) {
        User user = userRepository.save(u);
        return user;
    }

    @Override
    public User findById(Integer id) {
        Optional <User> user = userRepository.findById(id);
        if (user.isPresent()){
            User user1 = user.get();
            return user1;
        }
        else {
            return null;
        }
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOs = userRepository.findAll().stream().map(this::converter).collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    public void delete(User u) {
        userRepository.delete(u);
    }

    @Override
    public UserDTO converter(User u) {
        if (u == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO(u.getUserName(), u.getUserDetails().getFirstName(), u.getUserDetails().getLastName(), u.getRole() , u.getUserDetails().getPhoneNumber(), u.getUserDetails().getEmail());
        return userDTO;
    }

    @Override
    public UserDTO findUserByBookings(Booking b) {
        User user = userRepository.findUserByBookings(b);
        return converter(user);
    }

    @Override
    public UserDTO findAllByUserDetails(UserDetails ud) {
        User user = userRepository.findAllByUserDetails(ud);
        return converter(user);
    }

    @Override
    public List<Integer> findAllBookings(Integer id) {
        return userRepository.findAllById(id);
    }

    @Override
    public List<Integer> findAllFlights(int id) {
        return userRepository.findAllById(id);
    }

}