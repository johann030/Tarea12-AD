package ficheros;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainEmpleados {

	public static void main(String[] args) {

		Empleados empleado[] = new Empleados[3];
		Scanner sc = new Scanner(System.in);

		try {
			FileOutputStream FOS = new FileOutputStream("./empleados.dat");
			ObjectOutputStream dataOb = new ObjectOutputStream(FOS);

			for (int i = 0; i < empleado.length; i++) {
				System.out.println("Introduzca el nombre del empleado: ");
				String nombre = sc.nextLine();

				System.out.println("Introduzca el puesto del empleado: ");
				String puesto = sc.nextLine();

				System.out.println("Introduzca el departamento al que pertenece el empleado: ");
				String depto = sc.nextLine();

				System.out.println("Introduzca el salario del empleado: ");
				int salario = sc.nextInt();
				sc.nextLine();

				Empleados empleados = new Empleados(nombre, puesto, depto, salario);

				dataOb.writeObject(empleados);
			}

			System.out.println("Empleados guardados correctamente.");

			dataOb.close();
			FOS.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}
}