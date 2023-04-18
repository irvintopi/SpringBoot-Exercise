package org.lhind.SpringBootExercise.mainImpl;

import org.lhind.SpringBootExercise.model.Flight;
import org.lhind.SpringBootExercise.service.FlightService;
import org.lhind.SpringBootExercise.service.impl.FlightServiceImpl;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class FlightMain {
    FlightService flight;
    public FlightMain(FlightServiceImpl flight){
        this.flight = flight;
    }
    public void find(){

        List<Flight> flightList = flight.findAll();
        if (flightList.isEmpty()){
            System.out.println("No flights yet");
            return;
        }
        System.out.println("All the flights...\n");
        for (Flight f : flightList) {
            System.out.println(flight.converter(f));
        }
    }
    public Flight add() {
        Scanner read = new Scanner(System.in);

        System.out.println("\nAdding flight\n");
        System.out.println("Enter the origin");
        String origin = read.nextLine();
        System.out.println("Enter the destination");
        String destination = read.nextLine();
        System.out.println("Enter the airline");
        String airline = read.nextLine();
        System.out.println("Enter the flight number");
        Integer flightNumber = read.nextInt();
        System.out.println("Enter the departure date (yyyy-MM-dd)");
        read.nextLine();
        Date departureDate = null;
        try {
            departureDate = new SimpleDateFormat("yyyy-MM-dd").parse(read.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
        }
        System.out.println("Enter the arrival date (yyyy-MM-dd)");
        Date arrivalDate = null;
        try {
            arrivalDate = new SimpleDateFormat("yyyy-MM-dd").parse(read.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
        }
        System.out.println("Enter the status");
        String status = read.nextLine();


        Flight f1 = new Flight();
        f1 = setter(f1, origin, destination, airline, flightNumber, departureDate, arrivalDate, status);
        flight.save(f1);
        return f1;
    }
    public void findId(){
        Scanner read = new Scanner(System.in);

        Integer find = 0;
        System.out.println("\nFinding flight by id\n");
        try {
            System.out.println("Enter the flight id you want to find");
            find = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            read.nextLine();
            System.out.println("Id should be a number");
        }
        Optional<Flight> flight1 = flight.findById(find);
        if (flight1.isPresent()){
            Flight byId = flight1.get();
            System.out.println(flight.converter(byId));
        }
        else
            System.out.println("This flight doesn't exist");
    }
    public void findAllBookings(){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter the flight id to get the bookings");
        Integer fId = 0;
        try {
            fId = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Id should be a number");
            read.nextLine();
        }
        Optional<Flight> f1 = flight.findById(fId);
        if (f1.isEmpty()){
            System.out.println("This flight doesnt exist");
            return;
        }
        Flight flight1 = f1.get();
        List<Integer> bookings = flight.findAllBookings(fId);
        if (bookings.isEmpty()){
            System.out.println("This flight has no bookings");
            return;
        }
        System.out.println("Booking ID's of this flight\n");
        int i = 0;
        for (Integer b:bookings) {
            System.out.println("Booking #"+ (i+1) +" id: " + b);
            i++;
        }
    }
    public void findAllUsers(){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter the flight id from which you want to get the users");
        int fId = 0;
        try {
            fId = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Id should be a number");
            read.nextLine();
        }
        Optional<Flight> f1 = flight.findById(fId);
        if (!(f1.isPresent())){
            System.out.println("This flight doesnt exist");
            return;
        }
        Flight flight1 = f1.get();
        List<Integer> users = flight.findAllUsers(fId);
        if (users.isEmpty()){
            System.out.println("This flight has no users yet");
            return;
        }
        System.out.println("All the user id-s of this flight\n");
        int i = 0;
        List<Integer> userList = new ArrayList<>();
        for (Integer u:users) {
            if (!(userList.contains(u))){
                userList.add(u);
                System.out.println("User #"+ (i+1) +" id: " + u);
                i++;
            }
        }
    }
    public void remove(){
        Integer find = 0;
        Scanner read = new Scanner(System.in);

        System.out.println("\nDeleting flight\n");
        try {
            System.out.println("Enter the flight id you want to delete");
            find = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            read.nextLine();
            System.out.println("Id should be a number");
        }
        Optional<Flight> flight2 = flight.findById(find);
        if (flight2.isPresent()){
            Flight fdelete = flight2.get();
            flight.delete(fdelete);
            System.out.println("Flight deleted successfully!");
        }
        else System.out.println("Flight doesn't exist");
    }
    public static Flight setter(Flight f1, String origin, String dest, String air, Integer fn, Date date, Date date1, String status){
        f1.setOrigin(origin);
        f1.setDestination(dest);
        f1.setAirline(air);
        f1.setFlightNumber(fn);
        f1.setDepartureDate(date);
        f1.setArrivalDate(date1);
        f1.setStatus(status);
        return f1;
    }
}
