package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import controller.ReservationController;


public class Reservation extends ReservationController {
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {
		super();
	}

	public Reservation(Integer roomNumber, Date checkin, Date checkout) {

		if (!checkout.after(checkin)) {
//			throw new MainException("\nCheck-out date must be after check-in date\n");
			System.out.println("\nCheck-out date must be after check-in date\n");
			addReservation();
		} else {
			this.roomNumber = roomNumber;
			this.checkin = checkin;
			this.checkout = checkout;
		}
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void updateDates(Date checkin, Date checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}


	@Override
	public String toString() {
		return " Room " + roomNumber + ", " + "check-in: " + sdf.format(checkin) + ", " + "check-out "
				+ sdf.format(checkout) + ", " + duration() + " nights";
	}
}
