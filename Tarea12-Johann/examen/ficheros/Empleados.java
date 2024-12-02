package ficheros;

import java.io.Serializable;

public class Empleados implements Serializable {

	private static final long serialVersionUID = 2037580543093454800L;

	private String nombre, puesto, departamento;
	private int salario;

	public Empleados(String nombre, String puesto, String depto, int salario) {
		this.nombre = nombre;
		this.puesto = puesto;
		this.departamento = depto;
		this.salario = salario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}
}