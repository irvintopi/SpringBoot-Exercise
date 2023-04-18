package org.lhind.SpringBootExercise.mainImpl;

import org.lhind.SpringBootExercise.model.User;
import org.lhind.SpringBootExercise.model.UserDetails;
import org.lhind.SpringBootExercise.service.UserDetailsService;
import org.lhind.SpringBootExercise.service.UserService;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
@Component
public class UserDetailsMain {
    UserDetailsService userDetailsService;
    UserService userService;
    UserDetailsMain(UserDetailsService userDetailsService, UserService userService){
        this.userDetailsService= userDetailsService;
        this.userService= userService;
    }
    public UserDetails add(User usser){
        Scanner read = new Scanner(System.in);
        Integer id = 0;

        System.out.println("\nAdding the user details\n");
        System.out.println("Enter the first name");
        String fn = read.nextLine();
        System.out.println("Enter the last name");
        String ln = read.nextLine();
        System.out.println("Enter the email");
        String email = read.nextLine();
        System.out.println("Enter the phone number");
        String phone = read.nextLine();
        UserDetails ud = new UserDetails();
        ud.setFirstName(fn);
        ud.setLastName(ln);
        ud.setEmail(email);
        ud.setPhoneNumber(phone);
        ud.setUser(usser);
        userDetailsService.save(ud);
        return ud;
    }
    public  void findId(){
        Scanner read = new Scanner(System.in);
        Integer find = 0;

        System.out.println("\nFinding user details by id\n");
        try {
            System.out.println("Enter the user details id you want to find");
            find = read.nextInt();
            read.nextLine();
        }catch (InputMismatchException e){
            read.nextLine();
            System.out.println("Id should be a number");
        }
        Optional<UserDetails> userDetails1 = userDetailsService.findById(find);
        if (userDetails1.isPresent()){
            UserDetails byId = userDetails1.get();
            System.out.println(byId);
        }
        else
            System.out.println("These user details dont exist");
    }

}