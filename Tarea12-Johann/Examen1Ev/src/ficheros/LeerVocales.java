package src.ficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerVocales {

	public static void main(String[] args) {
		int a = 0;
		int e = 0;
		int i = 0;
		int o = 0;
		int u = 0;
		String linea;

		try {
			BufferedReader br = new BufferedReader(new FileReader("./frases.txt"));

			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
				linea.split("a");
				if (linea.split("a") != null) {
					a++;
				} else if (linea.split("e") != null) {
					e++;
				} else if (linea.split("i") != null) {
					i++;
				} else if (linea.split("o") != null) {
					o++;
				} else if (linea.split("u") != null) {
					u++;
				}
			}
			
			br.close();
			
			System.out.println("A: " + a + "E: " + e + "I: " + i + "O: " + o + "U: " + u);

		} catch (IOException Excep) {

			Excep.printStackTrace();
		}

	}

}
