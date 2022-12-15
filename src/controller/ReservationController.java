package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.entities.Reservation;

public class ReservationController {
	private List<Reservation> reservations = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public void addReservation() {



		try {
			System.out.println("\nRoom number: ");
			Integer roomNumber = scanner.nextInt();
			System.out.println("Check-in date (dd/MM/yyyy): ");
			Date checkin = sdf.parse(scanner.next());
			System.out.println("Check-out date (dd/MM/yyyy): ");
			Date checkout = sdf.parse(scanner.next());

			reservations.add(new Reservation(roomNumber, checkin, checkout));
			System.out.println("Sucess!");

		} catch (ParseException e) {
			System.out.println("Unspected error!");
		}

	}

	public void viewReservations() {
		System.out.println("Show all the reservations: ");
		for (Reservation reservation : reservations) {
			System.out.println(reservation.toString());
		}
	}

	public void updateReservation() {
		try {
			System.out.println("Enter room number: ");
			int roomNumber = scanner.nextInt();
			Reservation reservationUpdated = null;
			for(Reservation reservation : reservations) {
				if(reservation.getRoomNumber() == roomNumber) {
					reservationUpdated = reservation;
				}
			}
			if( reservationUpdated == null) {
				throw new Exception("Room number does no exists"); 
			}
			System.out.println("New check-in date (dd/MM/yyyy): ");
			Date checkin = sdf.parse(scanner.next());
			System.out.println("New check-out date (dd/MM/yyyy): ");
			Date checkout = sdf.parse(scanner.next());
			reservationUpdated.updateDates(checkin, checkout);
			System.out.println("Updated!");
			}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
