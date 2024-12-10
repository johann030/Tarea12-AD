package src.ficheros;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MainJSON {

	public static void main(String[] args) {

		try {
			JSONArray JA = new JSONArray();
			JSONObject JO = new JSONObject();
			FileInputStream FI = new FileInputStream("./empleados.dat");
			ObjectInputStream OI = new ObjectInputStream(FI);
			FileWriter fw = new FileWriter("./empleados.json");

			while (OI.available() > 0) {
				JO.put("departamentos", OI.readObject());
				JA.add(JO);
			}

			fw.write(JA.toJSONString());
			System.out.println("Se guardo correctamente en el fichero JSON.");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
