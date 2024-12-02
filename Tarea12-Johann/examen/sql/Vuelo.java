package sql;

import java.time.LocalDate;

public class Vuelo {
	private int num_vuelo, num_paradas;
	private LocalDate fecha_vuelo;
	private String Aorigen, Adestino, estado;

	public Vuelo(int num_vuelo, LocalDate fecha_vuelo, String Aorigen, String Adestino, int num_paradas,
			String estado) {
		this.num_vuelo = num_vuelo;
		this.num_paradas = num_paradas;
		this.fecha_vuelo = fecha_vuelo;
		this.Aorigen = Aorigen;
		this.Adestino = Adestino;
		this.estado = estado;
	}

	public int getNum_vuelo() {
		return num_vuelo;
	}

	public void setNum_vuelo(int num_vuelo) {
		this.num_vuelo = num_vuelo;
	}

	public int getNum_paradas() {
		return num_paradas;
	}

	public void setNum_paradas(int num_paradas) {
		this.num_paradas = num_paradas;
	}

	public LocalDate getFecha_vuelo() {
		return fecha_vuelo;
	}

	public void setFecha_vuelo(LocalDate fecha_vuelo) {
		this.fecha_vuelo = fecha_vuelo;
	}

	public String getAorigen() {
		return Aorigen;
	}

	public void setAorigen(String aorigen) {
		Aorigen = aorigen;
	}

	public String getAdestino() {
		return Adestino;
	}

	public void setAdestino(String adestino) {
		Adestino = adestino;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}