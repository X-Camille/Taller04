package model;

import java.util.*;

public class Artista {

	private ArrayList<EventoMusical> eventosMusicales;
	private String nombreArtistico;
	private String generoMusical;

	public Artista(String nombreArtistico, String generoMusical){
		this.nombreArtistico = nombreArtistico;
		this.generoMusical = generoMusical;
	}

	public String getNombreArtistico(){
		return nombreArtistico;
	}

	public String getGeneroMusical(){
		return generoMusical;
	}

	public String toString(){
		return getNombreArtistico() + "/" + getGeneroMusical() + "/";
	}

}