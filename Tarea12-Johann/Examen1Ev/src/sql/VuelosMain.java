package src.sql;

import java.util.Scanner;

public class VuelosMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Vuelos v = new Vuelos();
		int num = 0;

		do {
			System.out.println("Introducir vuelos");
			System.out.println("Eliminar vuelo por numero de vuelo y fecha");
			System.out.println("Mostrar vuelos no cancelados");
			System.out.println("Mostrar vuelos de aeropuerto concreto");
			System.out.println("Cambiar estado de vuelo");
			try {
				num = sc.nextInt();
				sc.nextLine();

				switch (num) {
				case 1:
					v.insertarVuelo();
					break;

				case 2:
					v.eliminarVuelo();
					break;

				case 3:
					v.mostrarvueloNoCancelado();
					break;

				case 4:
					v.mostrarvueloConcreto();
					break;

				case 5:
					v.mostrarvueloFecha();
					break;

				case 6:
					v.cambiarEstado();
					break;

				default:
					System.out.println("Opcion no valida.");
				}
			} catch (Exception e) {
				e.getMessage();
			}
		} while (num != 7);

		sc.close();
	}
}