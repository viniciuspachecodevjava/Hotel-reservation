package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.entities.Reservation;
import model.exceptions.MainException;

public class ReservationController {
	private static List<Reservation> reservations = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	public void verifyOption(Integer option) throws MainException {
		if (option == 1) {
		} else if (option == 2) {
		} else if (option == 3) {
		} else if (option == 4) {
		} else {
//			throw new MainException("\nUNEXPECTED: You must enter a valid option\n");
			System.out.println("\nUNEXPECTED: You must enter a valid option\n");
			verifyOption(option);
		}
	}

	public void checkDates(Date checkin, Date checkout) throws MainException {
		 Date currentDate = new Date();
		 
		if (checkin.before(currentDate) || checkout.before(currentDate) ) {
			System.out.println("\nTo add a reservation, you must enter future dates only\n");
			addReservation();
//			throw new MainException("\nTo add a reservation, you must enter future dates only\n");
		}


	}
	public static boolean hasNumberRoom(List<Reservation> roomNumberList, int roomNumber) {
		Reservation reservationRoom = reservations.stream().filter(x -> x.getRoomNumber() == roomNumber).findFirst().orElse(null);
		return reservationRoom != null;
	}

	public void addReservation() {
		try {

			System.out.println("\nRoom number: ");
			Integer roomNumber = scanner.nextInt();
			while(hasNumberRoom(reservations, roomNumber)) {
				System.out.println("Room number already taken!");
				System.out.println("Enter the room number again:\n");
				roomNumber = scanner.nextInt();
			}
			System.out.println("Check-in date (dd/MM/yyyy): ");
			Date checkin = sdf.parse(scanner.next());
			System.out.println("Check-out date (dd/MM/yyyy): ");
			Date checkout = sdf.parse(scanner.next());
			checkDates(checkin, checkout);
			reservations.add(new Reservation(roomNumber, checkin, checkout));
			System.out.println("Sucess!");

		} catch (ParseException e) {
			System.out.println("Unspected error!");
		} catch (MainException checkDates) {
			System.out.println(checkDates.getMessage());
		}
	}

	public void updateReservation() {
		try {
			System.out.println("Enter room number: ");
			int roomNumber = scanner.nextInt();
			Reservation reservationUpdated = null;
			for (Reservation reservation : reservations) {
				if (reservation.getRoomNumber() == roomNumber) {
					reservationUpdated = reservation;
				}
			}
			if (reservationUpdated == null) {
				throw new Exception("Room number does no exists");
			}
			System.out.println("New check-in date (dd/MM/yyyy): ");
			Date checkin = sdf.parse(scanner.next());
			System.out.println("New check-out date (dd/MM/yyyy): ");
			Date checkout = sdf.parse(scanner.next());
			reservationUpdated.updateDates(checkin, checkout);
			System.out.println("Updated!");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void viewReservations() {
		System.out.println("Show all the reservations: ");
		for (Reservation reservation : reservations) {
			System.out.println(reservation.toString());
		}
	}

}
