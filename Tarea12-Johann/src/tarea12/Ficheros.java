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
			sql = "select NIA, nombre, apellidos, genero, fecha_nacimiento, ciclo, curso, id_grupo from alumnos";
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
				int id_grupo = resultado.getInt(8);

				bw.write(String.format("%d, %s, %s, %s, %s, %s, %s, %d, %n", nia, nombre, apellidos, genero, nacimiento,
						ciclo, curso, id_grupo));
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

		String sqlAlumnos = "select NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, id_grupo from alumno where id_grupo = ?";
		String sqlGrupos = "select cod_grupo, nombre, ciclo, aula from grupos";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);
			Statement sentenciaGrupos = conexion.createStatement();
			PreparedStatement sentenciaAlumnos = conexion.prepareStatement(sqlAlumnos);

			ResultSet resultadoGrupos = sentenciaGrupos.executeQuery(sqlGrupos);

			JSONArray listaGrupos = new JSONArray();

			while (resultadoGrupos.next()) {
				JSONObject grupo = new JSONObject();
				grupo.put("cod_grupo", resultadoGrupos.getInt("cod_grupo"));
				grupo.put("nombre", resultadoGrupos.getString("nombre"));
				grupo.put("ciclo", resultadoGrupos.getString("ciclo"));
				grupo.put("aula", resultadoGrupos.getInt("aula"));

				sentenciaAlumnos.setInt(1, resultadoGrupos.getInt("cod_grupo"));
				ResultSet resultadoAlumno = sentenciaAlumnos.executeQuery();
				JSONArray listaAlumnos = new JSONArray();

				while (resultadoAlumno.next()) {
					JSONObject alumno = new JSONObject();
					alumno.put("NIA", resultadoAlumno.getInt("NIA"));
					alumno.put("nombre", resultadoAlumno.getString("nombre"));
					alumno.put("apellidos", resultadoAlumno.getString("apellidos"));
					alumno.put("genero", resultadoAlumno.getString("fechaNacimiento"));
					alumno.put("ciclo", resultadoAlumno.getString("ciclo"));
					alumno.put("curso", resultadoAlumno.getString("curso"));
					alumno.put("id_grupo", resultadoAlumno.getInt("id_grupo"));

					listaAlumnos.add(alumno);
				}

				grupo.put("alumnos", listaAlumnos);

				listaGrupos.add(grupo);
			}

			FileWriter fw = new FileWriter("grupos.json");

			fw.write(listaGrupos.toJSONString());
			System.out.println("Fichero grupos.json creado correctamente.");

			fw.close();
			resultadoGrupos.close();
			sentenciaAlumnos.close();
			sentenciaGrupos.close();
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

		sql = "insert into alumnos(NIA, nombre, apellidos, genero, fecha_nacimiento, ciclo, curso, id_grupo) values(?,?,?,?,?,?,?,?)";

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
				int id_grupo = Integer.parseInt(alumnoData[7]);

				Psentencia.setInt(1, nia);
				Psentencia.setString(2, nombre);
				Psentencia.setString(3, apellidos);
				Psentencia.setString(4, genero);
				Psentencia.setDate(5, Date.valueOf(fechaNacimiento));
				Psentencia.setString(6, ciclo);
				Psentencia.setString(7, curso);
				Psentencia.setInt(8, id_grupo);

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

		String sqlGrupos = "insert into grupos(cod_grupo, nombre, ciclo, aula) values(?,?,?,?)";
		String sqlAlumnos = "insert into alumno(NIA, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, id_grupo) values(?,?,?,?,?,?,?,?)";
		JSONParser parser = new JSONParser();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement sentenciaAlumnos = conexion.prepareStatement(sqlAlumnos);
			PreparedStatement sentenciaGrupo = conexion.prepareStatement(sqlGrupos);

			FileReader fr = new FileReader("grupos.json");
			Object obj = parser.parse(fr);

			JSONArray listaGrupos = (JSONArray) obj;

			for (Object o : listaGrupos) {
				JSONObject grupoJSON = (JSONObject) o;
				int cod_grupo = ((Long) grupoJSON.get("cod_grupo")).intValue();
				String nombre = (String) grupoJSON.get("nombre");
				String ciclo = (String) grupoJSON.get("ciclo");
				int aula = ((Long) grupoJSON.get("aula")).intValue();

				sentenciaGrupo.setInt(1, cod_grupo);
				sentenciaGrupo.setString(2, nombre);
				sentenciaGrupo.setString(3, ciclo);
				sentenciaGrupo.setInt(4, aula);
				sentenciaGrupo.executeUpdate();

				JSONArray listaAlumnos = (JSONArray) grupoJSON.get("alumnos");

				for (Object a : listaAlumnos) {
					JSONObject alumnoJson = (JSONObject) a;

					int NIA = ((Long) alumnoJson.get("NIA")).intValue();
					String nombreAlumno = (String) alumnoJson.get("nombre");
					String apellidos = (String) alumnoJson.get("apellidos");
					String genero = (String) alumnoJson.get("genero");
					String nacimiento = (String) alumnoJson.get("nacimiento");
					String cicloAl = (String) alumnoJson.get("ciclo");
					String curso = (String) alumnoJson.get("curso");
					int id_grupo = cod_grupo;

					sentenciaAlumnos.setInt(1, NIA);
					sentenciaAlumnos.setString(2, nombreAlumno);
					sentenciaAlumnos.setString(3, apellidos);
					sentenciaAlumnos.setString(4, genero);
					sentenciaAlumnos.setDate(5, Date.valueOf(nacimiento));
					sentenciaAlumnos.setString(6, cicloAl);
					sentenciaAlumnos.setString(7, curso);
					sentenciaAlumnos.setInt(8, id_grupo);

					sentenciaAlumnos.executeUpdate();
				}
			}

			System.out.println("Se inserto correctamente la informacion en la base de datos.");

			sentenciaAlumnos.close();
			sentenciaGrupo.close();
			fr.close();
			conexion.close();

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