package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import controller.ReservationController;
import model.exceptions.MainException;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ReservationController controller = new ReservationController();
		Integer option = null;
		try {
			do {

				System.out.println("-------------Hotel---------------");
				System.out.println("| Option 1 - New reservation    |");
				System.out.println("| Option 2 - Update reservation |");
				System.out.println("| Option 3 - View reservations  |");
				System.out.println("| Option 4 - Quit               |");
				System.out.println("---------------------------------");
				System.out.println("Choose an option: ");
				try {
				option = sc.nextInt();
				controller.verifyOption(option);
				}
				catch(MainException e){
					System.out.println(e.getMessage());
				}
				switch (option) {

				case 1:
					controller.addReservation();
					break;
				case 2:
					controller.updateReservation();
					break;
				case 3:
					controller.viewReservations();
					break;
				}
				
			} while (option != 4);
		} catch (InputMismatchException e) {
			System.out.println("Atenttion! Only numbers will be accepted!");
		}

		System.out.println("------Closed aplication------");
		sc.close();
	}
}