package tarea12;

public class Grupos {

	private int cod_grupo, aula;
	private String nombre, ciclo;

	public Grupos(int cod_grupo, String nombre, String ciclo, int aula) {
		this.cod_grupo = cod_grupo;
		this.nombre = nombre;
		this.ciclo = ciclo;
		this.aula = aula;
	}

	public Grupos() {
	}

	public int getCod_grupo() {
		return cod_grupo;
	}

	public void setCod_grupo(int cod_grupo) {
		this.cod_grupo = cod_grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public int getAula() {
		return aula;
	}

	public void setAula(int aula) {
		this.aula = aula;
	}

	@Override
	public String toString() {
		return "Grupos [cod_grupo=" + cod_grupo + ", nombre=" + nombre + ", ciclo=" + ciclo + ", aula=" + aula + "]";
	}
}