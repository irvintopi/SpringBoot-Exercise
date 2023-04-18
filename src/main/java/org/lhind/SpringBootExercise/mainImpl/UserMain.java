package org.lhind.SpringBootExercise.mainImpl;


import org.lhind.SpringBootExercise.dto.UserDTO;
import org.lhind.SpringBootExercise.model.User;
import org.lhind.SpringBootExercise.model.UserDetails;
import org.lhind.SpringBootExercise.service.BookingService;
import org.lhind.SpringBootExercise.service.UserDetailsService;
import org.lhind.SpringBootExercise.service.UserService;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Component
public class UserMain {
    UserService userService;
    UserDetailsMain UserDetailsMain;
    BookingService bookingService;
    UserDetailsService userDetailsService;
    public UserMain(UserService userService, UserDetailsMain userDetailsMain, BookingService bookingService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.UserDetailsMain = userDetailsMain;
        this.bookingService = bookingService;
        this.userDetailsService = userDetailsService;
    }

    public void find(){
        List<UserDTO> userList = userService.findAll();
        if (userList.isEmpty()){
            System.out.println("No users found.");
            return;
        }
        System.out.println("Users :\n");
        for (UserDTO us : userList) {
            System.out.println(us);
        }
    }
    public User add(){
        Scanner read = new Scanner(System.in);

        System.out.println("\nAdding user\n");

        System.out.println("Enter the username");
        String name = read.nextLine();
        System.out.println("Enter the user password");
        String pass = read.nextLine();
        System.out.println("Enter the user role");
        String role = read.nextLine();

        User u1 = new User();
        u1=setter(u1,role,pass,name);
        userService.save(u1);
        UserDetails ud = UserDetailsMain.add(u1);
        u1.setUserDetails(ud);
        userService.save(u1);
        return u1;
    }
    public void findId(){
        Scanner read = new Scanner(System.in);
        Integer id = 0;
        System.out.println("\nFinding user by id\n");
        try {
            System.out.println("Enter the user id you want to find");
            id = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            read.nextLine();
            System.out.println("Id must be a number");
        }
        User user1 = userService.findById(id);
        if (user1!=null){
            System.out.println(userService.converter(user1));
        }
        else
            System.out.println("User doesn't exist");
    }
    public void remove(){
        Integer find = 0;
        Scanner read = new Scanner(System.in);

        System.out.println("\nDeleting user\n");
        try {
            System.out.println("Enter the id of the user you want to delete");
            find = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            read.nextLine();
            System.out.println("Id must be a number");
        }
       User user2 = userService.findById(find);
        if (user2!=null){
            userService.delete(user2);
            System.out.println("User Deleted!");
        }
        else
            System.out.println("This user doesn't exist");
    }

    public void findAllBookings(){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter User id to display bookings");
        Integer uId = 0;
        try {
            uId = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Id must be a number");
            read.nextLine();
        }
        User user1 = userService.findById(uId);
        if (user1 == null){
            System.out.println("User doesn't exist");
            return;
        }
        List<Integer> bookings = userService.findAllBookings(uId);
        if (bookings.isEmpty()){
            System.out.println("No bookings found");
            return;
        }
        System.out.println("User's booking id-s\n");
        int i = 0;
        for (Integer b:bookings) {
            System.out.println("Booking "+ (i+1) +" id: " + b);
            i++;
        }
    }
    public static User setter(User u1,String role, String pass, String name){
        u1.setUserName(name);
        u1.setRole(role);
        u1.setPassword(pass);
        return u1;
    }
}