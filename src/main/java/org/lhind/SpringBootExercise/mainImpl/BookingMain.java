package org.lhind.SpringBootExercise.mainImpl;

import org.lhind.SpringBootExercise.model.Booking;
import org.lhind.SpringBootExercise.model.Flight;
import org.lhind.SpringBootExercise.model.User;
import org.lhind.SpringBootExercise.service.BookingService;
import org.lhind.SpringBootExercise.service.FlightService;
import org.lhind.SpringBootExercise.service.UserService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookingMain {
    BookingService bookingService;
    UserService userService;
    FlightService flightService;

    public BookingMain(BookingService bookingService, UserService userService, FlightService flightService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.flightService = flightService;
    }
    public void find(){

        List<Booking> bookingList = bookingService.findAll();
        if (bookingList.isEmpty()){
            System.out.println("No bookings yet");
            return;
        }
        System.out.println("All the bookings...\n");
        for (Booking b : bookingList) {
            System.out.println(bookingService.converter(b));
        }
    }
    public Booking add(){
        Scanner read = new Scanner(System.in);

        Integer id = 0;
        System.out.println("\nAdding booking\n");
        System.out.println("Enter the booking id");
        try {
            id = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Id should be a number");
            read.nextLine();
        }
        System.out.println("Enter the id of the user who booked the flight");
        int uId = 0;
        try {
            uId = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Id should be a number");
            read.nextLine();
        }

        User userById = userService.findById(uId);
        if (userById == null){
            System.out.println("Couldnt add booking. Invalid user");
            return null;
        }
        System.out.println("The flights of this booking...");
        int result = 0;

        List<Flight> flights = new ArrayList<>();
        while (result != -1){
            System.out.println("Add one flight id to this booking (-1 to stop)");
            try {
                result = read.nextInt();
                read.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println("Id should be a number");
                read.nextLine();
            }
            Optional<Flight> flightById = flightService.findById(result);
            if (flightById.isPresent()){
                Flight fbyId = flightById.get();
                flights.add(fbyId);
            }
            else if (result != -1){
                System.out.println("This flight doesnt exist");
                return null;
            }
        }
        Date date = new Date(System.currentTimeMillis());
        System.out.println("Enter the status");
        String status = read.nextLine();
        Optional<Booking> bookingById = bookingService.findById(id);
        if (bookingById.isPresent()){
            Booking b1 = bookingById.get();
            b1=setter(b1,status,date,userById,flights);
            System.out.println("Updated booking with id " + b1.getId());
            return b1;
        }
        else {
            Booking b2 = new Booking();
            b2.setId(null);
            b2=setter(b2,status,date,userById,flights);
            System.out.println("Added booking with id " + b2.getId());
            return b2;
        }
    }
    public void findId(){
        Scanner read = new Scanner(System.in);

        Integer find = 0;
        System.out.println("\nFinding booking by id\n");
        try {
            System.out.println("Enter the booking id you want to find");
            find = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            read.nextLine();
            System.out.println("Id should be a number");
        }
        Optional<Booking> booking1 = bookingService.findById(find);
        if (booking1.isPresent()){
            Booking byId = booking1.get();
            System.out.println(bookingService.converter(byId));
        }
        else
            System.out.println("This booking doesnt exist");
    }
    public void findAllFlights(){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter the booking id from which you want to get the flights");
        Integer bId = 0;
        try {
            bId = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Id should be a number");
            read.nextLine();
        }
        Optional<Booking> b1 = bookingService.findById(bId);
        if (!(b1.isPresent())){
            System.out.println("This booking doesnt exist");
            return;
        }
        Booking booking1 = b1.get();
        List<Integer> flights = bookingService.findAllFlights(bId);
        System.out.println("All the flight id-s of this booking\n");
        int i = 0;
        for (Integer f:flights) {
            System.out.println("Flight #"+ (i+1) +" id: " + f);
            i++;
        }
    }
    public void remove(){
        Integer find = 0;
        Scanner read = new Scanner(System.in);

        System.out.println("\nDeleting booking\n");
        try {
            System.out.println("Enter the booking id you want to delete");
            find = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            read.nextLine();
            System.out.println("Id should be a number");
        }
        Optional<Booking> booking2 = bookingService.findById(find);
        if (booking2.isPresent()){
            Booking bdelete = booking2.get();
            bookingService.delete(bdelete);
            System.out.println("Deleted booking with id " + bdelete.getId());
        }
        else
            System.out.println("This booking doesnt exist");
    }
    public Booking setter(Booking b1, String status, Date date, User ubyId, List<Flight> flights){
        b1.setStatus(status);
        b1.setBookingDate(date);
        b1.setUser(ubyId);
        b1.setFlights(flights);
        bookingService.save(b1);
        return b1;
    }
}