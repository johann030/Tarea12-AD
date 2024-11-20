package tarea12;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConectarBaseDatos {
	private String sql;
	private String url = "jdbc:mysql://localhost/johann06";
	private String usuario = "johann";
	private String contrasenia = "manager";
	Scanner sc = new Scanner(System.in);
	InsertarAlumnos IA = new InsertarAlumnos();
	Alumnos[] alumno = IA.alumnos();

	public void insertarAlumnos() {

		sql = "insert into alumno(NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo) values(?,?,?,?,?,?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement Psentencia = conexion.prepareStatement(sql);

			for (Alumnos Alumno : alumno) {
				Psentencia.setInt(1, Alumno.getNia());
				Psentencia.setString(2, Alumno.getNombre());
				Psentencia.setString(3, Alumno.getApellidos());
				Psentencia.setString(4, Alumno.getGenero());
				Psentencia.setDate(5, Date.valueOf(Alumno.getNacimiento()));
				Psentencia.setString(6, Alumno.getCiclo());
				Psentencia.setString(7, Alumno.getCurso());
				Psentencia.setObject(8, Alumno.getGrupo());

				Psentencia.executeUpdate();
			}
			System.out.println("Se ha guardado correctamente al alumno.");

			Psentencia.close();
			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void mostrarAlumnos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);
			Statement sentencia = conexion.createStatement();
			sql = "select NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, id_grupo from alumno";
			ResultSet resultado = sentencia.executeQuery(sql);
			while (resultado.next()) {
				System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s %n", resultado.getInt(1), resultado.getString(2),
						resultado.getString(3), resultado.getString(4), resultado.getDate(5), resultado.getString(6),
						resultado.getString(7), resultado.getString(8));
			}
			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modificarNombre() {
		sql = "update alumno set nombre = ? where NIA = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			System.out.println("Digame la NIA de la persona que quiera cambiar el nombre: ");
			int nia = sc.nextInt();
			sc.nextLine();
			System.out.println("Digame el nuevo nombre: ");
			String nuevoNombre = sc.nextLine();

			PreparedStatement Psentencia = conexion.prepareStatement(sql);
			Psentencia.setInt(2, nia);
			Psentencia.setString(1, nuevoNombre);
			int filas = Psentencia.executeUpdate();

			if (filas > 0) {
				System.out.println("El nombre se actualizo correctamente.");
			} else {
				System.out.println("No se encontro un alumno con esa NIA.");
			}

			Psentencia.close();
			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void eliminarPK() {
		sql = "delete from alumno where NIA = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			System.out.println("Digame la NIA de la persona que quiere eliminar: ");
			int nia = sc.nextInt();
			sc.nextLine();

			PreparedStatement Psentencia = conexion.prepareStatement(sql);
			Psentencia.setInt(1, nia);
			int filas = Psentencia.executeUpdate();

			if (filas > 0) {
				System.out.println("El alumno se borro correctamente");
			} else {
				System.out.println("No se encontro un alumno con esa NIA.");
			}

			Psentencia.close();
			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void eliminarGrupos() {

	}

	public void insertarGrupos() {

		Grupos[] grupo = new Grupos[1];
		sql = "insert into grupos(id_grupo, nombre, ciclo, aula) values(?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement Psentencia = conexion.prepareStatement(sql);

			for (Grupos grupos : grupo) {
				Psentencia.setObject(1, grupos.getCod_grupo());
				Psentencia.setObject(2, grupos.getNombre());
				Psentencia.setObject(3, grupos.getCiclo());
				Psentencia.setObject(4, grupos.getAula());

				Psentencia.executeUpdate();
			}

			Psentencia.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}