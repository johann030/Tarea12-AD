package tarea12;

import java.util.Scanner;

public class Menu {
	Scanner sc = new Scanner(System.in);
	int num = 0;
	ConectarBaseDatos abd = new ConectarBaseDatos();
	Ficheros f = new Ficheros();

	public void menu() {
		do {
			System.out.println("----------------------------------------------------------------");
			System.out.println("1. Insertar nuevo alumno.");
			System.out.println("2. Insertar nuevo grupo.");
			System.out.println("3. Mostrar alumnos.");
			System.out.println("4. Guardar alumnos en un fichero.");
			System.out.println("5. Guardar en la BD los alumnos del fichero.");
			System.out.println("6. Modificar nombre en la base de datos.");
			System.out.println("7. Eliminar un alumno a partir de su PK.");
			System.out.println("8. Eliminar los alumnos a partir de su curso.");
			System.out.println("9. Guardar grupos en fichero JSON.");
			System.out.println("10. Guardar en la BD los grupos del fichero JSON.");
			System.out.println("11. Salir.");
			System.out.println("----------------------------------------------------------------");
			try {
				num = sc.nextInt();
				sc.nextLine();
				switch (num) {
				case 1:
					abd.insertarAlumnos();
					break;

				case 2:
					abd.insertarGrupos();
					break;

				case 3:
					abd.mostrarAlumnos();
					break;

				case 4:
					f.guardarFicheroTexto();
					break;

				case 5:
					f.leerFicheroTexto();
					break;

				case 6:
					abd.modificarNombre();
					break;

				case 7:
					abd.eliminarPK();
					break;

				case 8:
					abd.eliminarGrupos();
					break;

				case 9:
					f.guardarFicheroJSON();
					break;

				case 10:
					f.leerFicheroJSON();
					break;

				case 11:
					System.out.println("Programa terminado.");
					break;

				default:
					System.out.println("Opcion no valida.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		} while (num != 11);
	}
}