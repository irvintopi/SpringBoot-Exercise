package org.lhind.SpringBootExercise;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.lhind.SpringBootExercise.mainImpl.*;
import java.util.Scanner;

@SpringBootApplication
public class Main {
	private Scanner scanner;
	UserMain userMain;
	BookingMain bookingMain;
	FlightMain flightMain;

	@Autowired
	public Main(UserMain userMain, BookingMain bookingMain, FlightMain flightMain) {
		this.userMain = userMain;
		this.bookingMain = bookingMain;
		this.flightMain = flightMain;
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@PostConstruct
	private void run() {
		Scanner read = new Scanner(System.in);
		String menu = "";

		while (!(menu.equalsIgnoreCase("E"))) {
			System.out.println("Choose the menu\n1.Menu User\n2.Menu Flights\n3.Menu Bookings\nPress E to exit");
			menu = read.nextLine();

			switch (menu) {
				case "1":
					userMenu(read);
					break;

				case "2":
					flightMenu(read);
					break;

				case "3":
					bookingMenu(read);
					break;

				case "E":
					break;

				default:
					System.out.println("Wrong option!");
					break;
			}
		}
	}

	private void userMenu(Scanner read) {
		String result = "";
		while (!(result.equalsIgnoreCase("6"))) {
			System.out.println("\nMenu User\n1.Save User\n2.Find user by id\n3.Find all users\n4.Delete a user\n5.Find all bookings of a user\n6.Exit User Menu\nChoose an option (1-6)");
			result = read.nextLine();

			switch (result) {
				case "1":
					userMain.add();
					break;

				case "2":
					userMain.findId();
					break;

				case "3":
					userMain.find();
					break;

				case "4":
					userMain.remove();
					break;

				case "5":
					userMain.findAllBookings();
					break;

				case "6":
					break;

				default:
					System.out.println("Wrong option!");
					break;
			}
		}
	}

	private void flightMenu(Scanner read) {
		String result = "";
		while (!(result.equalsIgnoreCase("7"))) {
			System.out.println("\nMenu Flights\n1.Save Flight\n2.Find flight by id\n3.Find all flights\n4.Delete a flight\n5.Find all bookings of a flight\n6.Find all users of a flight\n7.Exit Flight Menu\nChoose an option (1-7)");
			result = read.nextLine();

			switch (result) {
				case "1":
					flightMain.add();
					break;

				case "2":
					flightMain.findId();
					break;

				case "3":
					flightMain.find();
					break;

				case "4":
					flightMain.remove();
					break;

				case "5":
					flightMain.findAllBookings();
					break;

				case "6":
					flightMain.findAllUsers();
					break;

				case "7":
					break;

				default:
					System.out.println("Wrong option!");
					break;
			}
		}
	}

	private void bookingMenu(Scanner read) {
		String result = "";
		while (!(result.equalsIgnoreCase("6"))) {
			System.out.println("\nMenu Booking\n1.Save Booking\n2.Find booking by id\n3.Find all bookings\n4.Delete a booking\n5.Find all flights of a booking\n6.Exit Bookings Menu\nChoose an option (1-6)");
			result = read.nextLine();

			switch (result) {
				case "1":
					bookingMain.add();
					break;

				case "2":
					bookingMain.findId();
					break;

				case "3":
					bookingMain.find();
					break;

				case "4":
					bookingMain.remove();
					break;

				case "5":
					bookingMain.findAllFlights();
					break;

				case "6":
					break;

				default:
					System.out.println("Wrong option!");
					break;
			}
		}
	}
	/*@PostConstruct
	private void run() {
		Scanner read = new Scanner(System.in);
		String menu = "";
		String result = "";
		while (!(menu.equalsIgnoreCase("E"))) {
			System.out.println("Choose the menu\n1.Menu User\n2.Menu Flights\n3.Menu Bookings\nPress E to exit");
			menu = read.nextLine();
			if (menu.equalsIgnoreCase("1")) {
				result = "";
				while (!(result.equalsIgnoreCase("13"))) {
					System.out.println("\nMenu User\n1.Save User\n2.Find user by id\n3.Find all users\n4.Delete a user\n5.Find all bookings of a user\n6.Exit User Menu\nChoose an option (1-12)");
					result = read.nextLine();
					switch (result) {
						case "1":
							userMain.add();
							break;

						case "2":
							userMain.findId();
							break;

						case "3":
							userMain.find();
							break;

						case "4":
							userMain.remove();
							break;

						case "5":
							userMain.findAllBookings();
							break;

						case "6":
							break;

						default:
							System.out.println("Wrong option!");
							break;
					}
				}
			} else if (menu.equalsIgnoreCase("2")) {
				result = "";
				while (!(result.equalsIgnoreCase("7"))) {
					System.out.println("\nMenu Flights\n1.Save Flight\n2.Find flight by id\n3.Find all flights\n4.Delete a flight\n5.Find all bookings of a flight\n6.Find all users of a flight\n7.Exit Flight Menu\nChoose an option (1-7)");
					result = read.nextLine();
					switch (result) {
						case "1":
							flightMain.add();
							break;

						case "2":
							flightMain.findId();
							break;

						case "3":
							flightMain.find();
							break;

						case "4":
							flightMain.remove();
							break;

						case "5":
							flightMain.findAllBookings();
							break;

						case "6":
							flightMain.findAllUsers();
							break;

						case "7":
							break;

						default:
							System.out.println("Wrong option!");
							break;
					}
				}
			} else if (menu.equalsIgnoreCase("3")) {
				result = "";

				while (!(result.equalsIgnoreCase("6"))) {
					System.out.println("\nMenu Booking\n1.Save Booking\n2.Find booking by id\n3.Find all bookings\n4.Delete a booking\n5.Find all flights of a booking\n6.Exit Bookings Menu\nChoose an option (1-6)");
					result = read.nextLine();
					switch (result) {
						case "1":
							bookingMain.add();
							break;

						case "2":
							bookingMain.findId();
							break;

						case "3":
							bookingMain.find();
							break;

						case "4":
							bookingMain.remove();
							break;

						case "5":
							bookingMain.findAllFlights();
							break;

						case "6":
							break;

						default:
							System.out.println("Wrong option!");
							break;

					}
				}
			} else if (!(menu.equalsIgnoreCase("E")))
				System.out.println("Wrong option");
		}
	}*/
}
