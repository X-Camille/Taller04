package model;

import java.util.*;

public class EventoMusical {
	private ArrayList<Artista> artistasAsociados;
	private ArrayList<Entrada> entradas;
	private String nombreEvento;
	private String fechaEvento;
	private String lugar;

	public EventoMusical(String nombreEvento, String fechaEvento, String lugar){
		this.nombreEvento = nombreEvento;
		this.fechaEvento = fechaEvento;
		this.lugar = lugar;
		this.artistasAsociados = new ArrayList<>(){};
	}

	public ArrayList<Artista> getArtistasAsociados(){
		return artistasAsociados;
	}

	public ArrayList<Entrada> getEntradas(){
		return entradas;
	}

	public String getNombreEvento(){
		return nombreEvento;
	}

	public String getFechaEvento(){
		return fechaEvento;
	}

	public String getLugar(){
		return lugar;
	}
	public String toString(){
		return getNombreEvento() + "/" + getFechaEvento() + "/" + getLugar();
	}
}