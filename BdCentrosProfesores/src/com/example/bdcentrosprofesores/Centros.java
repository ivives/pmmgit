package com.example.bdcentrosprofesores;

public class Centros {

	private String codCentro;
	private String nomCentro;
	private String direccion;
	public Centros(String cod, String nom, String dir)
	{
		codCentro = cod;
		nomCentro =nom;
		direccion=dir;
	}
	public String getDireccion() 
	{
		return direccion;
	}
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	public String getCodCentro() 
	{
		return codCentro;
	}
	public void setCodCentro(String codCentro) 
	{
		this.codCentro = codCentro;
	}
	public String getNomCentro() 
	{
		return nomCentro;
	}
	public void setNomCentro(String nomCentro) 
	{
		this.nomCentro = nomCentro;
	}
	
	
	
}
