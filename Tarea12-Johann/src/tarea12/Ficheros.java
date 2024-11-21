package tarea12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Ficheros {
	private String sql;
	private String url = "jdbc:mysql://localhost/johann06";
	private String usuario = "johann";
	private String contrasenia = "manager";

	public void guardarFicheroTexto() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);
			Statement sentencia = conexion.createStatement();
			sql = "select NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo from alumno";
			ResultSet resultado = sentencia.executeQuery(sql);
			BufferedWriter bw = new BufferedWriter(new FileWriter("alumnos.txt"));

			while (resultado.next()) {
				int nia = resultado.getInt(1);
				String nombre = resultado.getString(2);
				String apellidos = resultado.getString(3);
				String genero = resultado.getString(4);
				Date fecha = resultado.getDate(5);
				LocalDate nacimiento = fecha.toLocalDate();
				String ciclo = resultado.getString(6);
				String curso = resultado.getString(7);
				int grupo = resultado.getInt(8);

				bw.write(String.format("%d, %s, %s, %s, %s, %s, %s, %d, %n", nia, nombre, apellidos, genero, nacimiento,
						ciclo, curso, grupo));
				bw.newLine();
			}
			System.out.println("Fichero alumnos.txt creado correctamente.");
			bw.close();
			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void guardarFicheroJSON() {
		// TODO

		JSONArray listaAlumnos = new JSONArray();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);
			Statement sentencia = conexion.createStatement();
			sql = "select NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo from alumno";
			ResultSet resultado = sentencia.executeQuery(sql);
			FileWriter fw = new FileWriter("grupos.json");

			while (resultado.next()) {
				JSONObject alumno = new JSONObject();
				alumno.put("NIA", resultado.getInt("NIA"));
				alumno.put("nombre", resultado.getString("nombre"));
				alumno.put("apellidos", resultado.getString("apellidos"));
				alumno.put("genero", resultado.getString("genero"));
				alumno.put("nacimiento", resultado.getString("fechaNacimiento"));
				alumno.put("ciclo", resultado.getString("ciclo"));
				alumno.put("curso", resultado.getString("curso"));
				alumno.put("grupo", resultado.getInt("grupo"));

				listaAlumnos.add(alumno);
			}

			fw.write(listaAlumnos.toJSONString());
			System.out.println("Fichero grupos.json creado correctamente.");

			fw.close();
			resultado.close();
			sentencia.close();
			conexion.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void leerFicheroTexto() {

		sql = "insert into alumno(NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo) values(?,?,?,?,?,?,?,?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement Psentencia = conexion.prepareStatement(sql);
			BufferedReader br = new BufferedReader(new FileReader("alumnos.txt"));
			String lineas;
			while ((lineas = br.readLine()) != null) {
				String[] alumnoData = lineas.split(",");
				int nia = Integer.parseInt(alumnoData[0]);
				String nombre = alumnoData[1].trim();
				String apellidos = alumnoData[2].trim();
				String genero = alumnoData[3].trim();
				String fechaNacimiento = alumnoData[4].trim();
				String ciclo = alumnoData[5].trim();
				String curso = alumnoData[6].trim();
				int grupo = Integer.parseInt(alumnoData[7]);

				Psentencia.setInt(1, nia);
				Psentencia.setString(2, nombre);
				Psentencia.setString(3, apellidos);
				Psentencia.setString(4, genero);
				Psentencia.setDate(5, Date.valueOf(fechaNacimiento));
				Psentencia.setString(6, ciclo);
				Psentencia.setString(7, curso);
				Psentencia.setInt(8, grupo);

				Psentencia.executeUpdate();
			}

			System.out.println("Los alumnos se insertaron correctamente en la base de datos.");

			Psentencia.close();
			conexion.close();
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void leerFicheroJSON() {
		// TODO

		sql = "insert into alumno(NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo) values(?,?,?,?,?,?,?,?)";
		JSONParser parser = new JSONParser();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement Psentencia = conexion.prepareStatement(sql);

			FileReader fr = new FileReader("grupos.json");
			Object obj = parser.parse(fr);
			JSONArray listaAlumnos = (JSONArray) obj;

			for (Object o : listaAlumnos) {
				JSONObject alumnoJson = (JSONObject) o;

				int NIA = ((Long) alumnoJson.get("NIA")).intValue();
				String nombre = (String) alumnoJson.get("nombre");
				String apellidos = (String) alumnoJson.get("apellidos");
				String genero = (String) alumnoJson.get("genero");
				String nacimiento = (String) alumnoJson.get("nacimiento");
				String ciclo = (String) alumnoJson.get("ciclo");
				String curso = (String) alumnoJson.get("curso");
				int grupo = ((Long) alumnoJson.get("grupo")).intValue();

				Psentencia.setInt(1, NIA);
				Psentencia.setString(2, nombre);
				Psentencia.setString(3, apellidos);
				Psentencia.setString(4, genero);
				Psentencia.setDate(5, Date.valueOf(nacimiento));
				Psentencia.setString(6, ciclo);
				Psentencia.setString(7, curso);
				Psentencia.setInt(8, grupo);

				Psentencia.executeUpdate();
			}

			System.out.println("Se inserto correctamente la informacion en la base de datos.");

			Psentencia.close();
			conexion.close();
			fr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}