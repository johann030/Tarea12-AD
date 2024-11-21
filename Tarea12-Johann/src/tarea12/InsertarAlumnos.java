package tarea12;

import java.time.LocalDate;
import java.util.Scanner;

public class InsertarAlumnos {
	Alumnos alumno[] = new Alumnos[1];
	Scanner sc = new Scanner(System.in);

	public Alumnos[] alumnos() {
		for (int i = 0; i < alumno.length; i++) {
			System.out.println("Introduzca la Nia del alumno: ");
			int nia = sc.nextInt();
			sc.nextLine();

			System.out.println("Introduzca el nombre del alumno: ");
			String nombre = sc.nextLine();

			System.out.println("Introduzca los apellidos del alumno: ");
			String apellidos = sc.nextLine();

			System.out.println("Introduzca el genero del alumno: ");
			String genero = sc.nextLine();

			System.out.println("Introduzca la fecha de nacimiento del alumno(yyyy-MM-dd): ");
			LocalDate nacimiento = null;
			String fechaEntrada = sc.nextLine();
			nacimiento = LocalDate.parse(fechaEntrada);

			System.out.println("Introduzca el cliclo del alumno: ");
			String ciclo = sc.nextLine();

			System.out.println("Introduzca el curso del alumno: ");
			String curso = sc.nextLine();

			System.out.println("Introduzca el codigo del grupo del alumno: ");
			int grupo = sc.nextInt();

			alumno[i] = new Alumnos(nia, nombre, apellidos, genero, nacimiento, ciclo, curso, grupo);
		}
		return alumno;
	}
}