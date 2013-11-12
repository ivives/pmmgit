package com.example.spinnerpersona;


public class Persona
{
	private String nombre;
	private String apellido;
	private int edad;
	private int foto;
	
	public Persona(String nom, String ap, int ed, int fo)
	{
			nombre = nom;
			apellido = ap;
			edad=ed;
			this.foto=fo;
	}
	public String getNombre(){
		return nombre;
	}

	public String getApellido(){
		return apellido;
	}
	public int getEdad(){
		return edad;
	}

	public int getFoto(){
		return foto;
	}
}