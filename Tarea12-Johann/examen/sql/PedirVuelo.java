package sql;

import java.time.LocalDate;
import java.util.Scanner;

public class PedirVuelo {

	Vuelo vuelo[] = new Vuelo[1];
	Scanner sc = new Scanner(System.in);

	public Vuelo[] pedirVuelo() {
		for (int i = 0; i < vuelo.length; i++) {
			System.out.println("Introduzca el salario del empleado: ");
			int num_vuelo = sc.nextInt();
			sc.nextLine();

			System.out.println("Introduzca el salario del empleado: ");
			LocalDate fecha_vuelo = null;
			String fech = sc.nextLine();
			fecha_vuelo = LocalDate.parse(fech);

			System.out.println("Introduzca el nombre del empleado: ");
			String origen = sc.nextLine();

			System.out.println("Introduzca el puesto del empleado: ");
			String destino = sc.nextLine();

			System.out.println("Introduzca el salario del empleado: ");
			int paradas = sc.nextInt();
			sc.nextLine();

			System.out.println("Introduzca el departamento al que pertenece el empleado: ");
			String estado = sc.nextLine();

			Vuelo vuelo = new Vuelo(num_vuelo, fecha_vuelo, origen, destino, paradas, estado);
		}

		return vuelo;
	}
}