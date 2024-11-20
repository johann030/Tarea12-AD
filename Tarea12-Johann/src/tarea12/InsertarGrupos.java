package tarea12;

import java.util.Scanner;

public class InsertarGrupos {
	Grupos grupo[] = new Grupos[1];
	Scanner sc = new Scanner(System.in);

	public Grupos[] Grupos() {
		for (int i = 0; i < grupo.length; i++) {
			System.out.println("Introduzca el codigo del grupo: ");
			int cod_grupo = sc.nextInt();
			sc.nextLine();

			System.out.println("Introduzca el nombre: ");
			String nombre = sc.nextLine();

			System.out.println("Introduzca el ciclo: ");
			String ciclo = sc.nextLine();

			System.out.println("Introduzca el aula: ");
			int aula = sc.nextInt();
			sc.nextLine();

			grupo[i] = new Grupos(cod_grupo, nombre, ciclo, aula);
		}
		return grupo;
	}
}