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

	public void insertarAlumnos() {

		InsertarAlumnos IA = new InsertarAlumnos();
		Alumnos[] alumno = IA.alumnos();
		sql = "insert into alumno(NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, id_grupo) values(?,?,?,?,?,?,?,?)";
		String sqlGrupo = "insert into grupos(cod_grupo, nombre, ciclo, aula) values(?,?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement sentenciaAlumnos = conexion.prepareStatement(sql);
			PreparedStatement sentenciaGrupo = conexion.prepareStatement(sqlGrupo);

			for (Alumnos Alumno : alumno) {

				sentenciaGrupo.setInt(1, Alumno.getId_grupo());
				sentenciaGrupo.setString(2, Alumno.getCiclo());
				sentenciaGrupo.setString(3, "Basica");
				sentenciaGrupo.setInt(4, 123);

				sentenciaGrupo.executeUpdate();

				sentenciaAlumnos.setInt(1, Alumno.getNia());
				sentenciaAlumnos.setString(2, Alumno.getNombre());
				sentenciaAlumnos.setString(3, Alumno.getApellidos());
				sentenciaAlumnos.setString(4, Alumno.getGenero());
				sentenciaAlumnos.setDate(5, Date.valueOf(Alumno.getNacimiento()));
				sentenciaAlumnos.setString(6, Alumno.getCiclo());
				sentenciaAlumnos.setString(7, Alumno.getCurso());
				sentenciaAlumnos.setInt(8, Alumno.getId_grupo());

				sentenciaAlumnos.executeUpdate();
			}
			System.out.println("Se ha guardado correctamente al alumno.");

			sentenciaAlumnos.close();
			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void mostrarAlumnos() {

		sql = "SELECT A.NIA, A.nombre, A.apellidos, A.genero, A.fechaNacimiento, A.ciclo, A.curso, A.id_grupo, G.nombre, G.aula"
				+ " FROM alumno A INNER JOIN grupos G ON A.id_grupo = G.cod_grupo";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);
			Statement sentencia = conexion.createStatement();

			ResultSet resultado = sentencia.executeQuery(sql);
			while (resultado.next()) {
				System.out.printf("%d, %s, %s, %s, %s, %s, %s, %d, %s, %s, %n", resultado.getInt(1),
						resultado.getString(2), resultado.getString(3), resultado.getString(4), resultado.getDate(5),
						resultado.getString(6), resultado.getString(7), resultado.getInt(8), resultado.getString(9),
						resultado.getString(10));
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

		sql = "delete from alumno where id_grupo = ?";
		String sql2 = "select cod_grupo, nombre from grupos";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			Statement sentencia = conexion.createStatement();

			ResultSet resultado = sentencia.executeQuery(sql2);
			System.out.printf("%d, %s %n", "Codigo_Grupo", "Nombre");

			while (resultado.next()) {
				System.out.printf("%d, %s %n", resultado.getInt(1), resultado.getString(2));
			}

			System.out.println("Digame la grupo para eliminar a sus alumnos: ");
			int grupo = sc.nextInt();
			sc.nextLine();

			PreparedStatement Psentencia = conexion.prepareStatement(sql);
			Psentencia.setInt(1, grupo);
			int filas = Psentencia.executeUpdate();

			if (filas > 0) {
				System.out.println("Los alumnos que pertenecen al grupo " + grupo + " se borraron correctamente");
			} else {
				System.out.println("No se encontro ese grupo.");
			}

			Psentencia.close();
			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insertarGrupos() {

		InsertarGrupos IG = new InsertarGrupos();
		Grupos[] grupo = IG.Grupos();
		sql = "insert into grupos(cod_grupo, nombre, ciclo, aula) values(?,?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement Psentencia = conexion.prepareStatement(sql);

			for (Grupos grupos : grupo) {
				Psentencia.setInt(1, grupos.getCod_grupo());
				Psentencia.setString(2, grupos.getNombre());
				Psentencia.setString(3, grupos.getCiclo());
				Psentencia.setInt(4, grupos.getAula());

				Psentencia.executeUpdate();
			}

			Psentencia.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}