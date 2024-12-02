package sql;

import java.util.Scanner;

public class VuelosMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Vuelos v = new Vuelos();
		int num = 2;
		do {
			
			System.out.println("Introducir vuelos");
			System.out.println("Eliminar vuelo por numero de vuelo y fecha");
			System.out.println("Mostrar vuelos no cancelados");
			System.out.println("Mostrar vuelos de aeropuerto concreto");
			System.out.println("Cambiar estado de vuelo");
			
			num = sc.nextInt();
			
			switch (num) {
			case 1:
				v.insertarVuelo();
			case 2:
				v.eliminarVuelo();
			case 3:
				v.mostrarvueloNoCancelado();
			case 4:
				v.mostrarvueloConcreto();
			case 5:
				v.mostrarvueloFecha();
			case 6:
				v.cambiarEstado();
			}
		} while (num != 7);
	}
}