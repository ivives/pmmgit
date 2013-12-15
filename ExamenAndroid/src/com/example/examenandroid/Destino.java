package com.example.examenandroid;

public class Destino {

	private String zona;
	private String continente;
	private String precio;
	private int mapa;
	

	public Destino(String zon, String con, String pre, int map){
		zona = zon;
		continente = con;
		precio = pre;
		mapa = map;
	}
	
	public String getZona(){
		return zona;
	}
	
	public String getContinente(){
		return continente;
	}
	
	public String getPrecio(){
		return precio;
	}
	
	public int getMapa(){
		return mapa;
	}
	
	
}
