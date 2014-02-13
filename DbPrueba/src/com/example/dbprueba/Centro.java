package com.example.dbprueba;

public class Centro {

	private int codigo;
	private String nombre;
	
	public Centro(int codigo, String nombre){
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	
	public String getNombre(){
		return nombre;
	}
		
	public int getCodigo(){
		return codigo;
	}
	
}
