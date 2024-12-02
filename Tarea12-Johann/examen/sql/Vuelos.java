package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Vuelos {

	private String url = "jdbc:musql//localhost/johannExamen06";
	private String nombre = "johann";
	private String contrasenia = "manager";
	private String sql;
	PedirVuelo PV = new PedirVuelo();
	Vuelo vuelo[] = PV.pedirVuelo();
	Scanner sc = new Scanner(System.in);

	public void insertarVuelo() {
		Connection conexion;
		sql = "insert into vuelos(num_vuelo, fecha_vuelo, Aorigen, Adestino, num_paradas, estado) values (?,?,?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, nombre, contrasenia);
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			for (Vuelo Vuelo : vuelo) {
				sentencia.setInt(1, Vuelo.getNum_paradas());
				sentencia.setString(2, Vuelo.getFecha_vuelo().toString());
				sentencia.setString(3, Vuelo.getAorigen());
				sentencia.setString(4, Vuelo.getAdestino());
				sentencia.setInt(5, Vuelo.getNum_paradas());
				sentencia.setString(6, Vuelo.getEstado());
				sentencia.executeUpdate();
			}

			System.out.println("Vuelo insertado correctamente");

			sentencia.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void eliminarVuelo() {
		Connection conexion;
		sql = "delete from vuelos where num_vuelo = ? AND fecha_vuelo = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, nombre, contrasenia);
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			System.out.println("Introduzca el numero de vuelo: ");
			int num_vuelo = sc.nextInt();
			sc.nextLine();

			System.out.println("Y la fecha de vuelo: ");
			LocalDate fecha_vuelo = null;
			String fech = sc.nextLine();
			fecha_vuelo = LocalDate.parse(fech);

			sentencia.setInt(1, num_vuelo);
			sentencia.setString(2, fech);

			sentencia.executeUpdate();

			sentencia.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void mostrarvueloNoCancelado() {
		Connection conexion;
		sql = "select num_vuelo, fecha_vuelo, Aorigen, Adestino, num_paradas, estado from vuelos where estado != cancelado";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, nombre, contrasenia);
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet resultado = sentencia.executeQuery();

			for (int i = 0; i < 200; i++) {
				System.out.format("%s, %d, &d, %d, %s, %d", resultado.getInt(1), resultado.getDate(2),
						resultado.getString(3), resultado.getString(4), resultado.getInt(5), resultado.getString(6));
			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void mostrarvueloConcreto() {
		Connection conexion;
		sql = "select num_vuelo, fecha_vuelo, Aorigen, Adestino, num_paradas, estado from vuelos where Aorigen = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, nombre, contrasenia);
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet resultado = sentencia.executeQuery();

			for (int i = 0; i < 200; i++) {
				System.out.format("%s, %d, &d, %d, %s, %d", resultado.getInt(1), resultado.getDate(2),
						resultado.getString(3), resultado.getString(4), resultado.getInt(5), resultado.getString(6));
			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void mostrarvueloFecha() {
		Connection conexion;
		sql = "select num_vuelo, fecha_vuelo, Aorigen, Adestino, num_paradas, estado from vuelos where ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, nombre, contrasenia);
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet resultado = sentencia.executeQuery();
//TODO
			System.out.println("Introduzca la primera fecha");
			String fecha1 = sc.nextLine();

			System.out.println("Introduzca la segunda fecha");
			String fecha2 = sc.nextLine();

			for (int i = 0; i < 200; i++) {
				System.out.format("%s, %d, &d, %d, %s, %d", resultado.getInt(1), resultado.getDate(2),
						resultado.getString(3), resultado.getString(4), resultado.getInt(5), resultado.getString(6));
			}

			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void cambiarEstado() {
		Connection conexion;
		sql = "update vuelos set estado = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, nombre, contrasenia);
			PreparedStatement sentencia = conexion.prepareStatement(sql);

			System.out.println("Introduzca la fecha");
			String fecha = sc.nextLine();

			for (Vuelo Vuelo : vuelo) {
				sentencia.setString(1, fecha);
				sentencia.executeUpdate();
			}
			System.out.println("Vuelos actualizados.");

			sentencia.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}