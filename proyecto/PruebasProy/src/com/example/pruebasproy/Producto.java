package com.example.pruebasproy;

public class Producto {
	
	
		public String codigo;
		public String descripcion;
		public Double precio;
		public int cantidad;

	    public Producto(String codigo, String descripcion, double precio, int cantidad) {
	        this.codigo = codigo;
	        this.descripcion = descripcion;
	        this.precio = precio;
	        this.cantidad = cantidad;
	    }

	   
	    public String getCodigo() {
	        return codigo;
	    }

	   
	    public String getDescripcion() {
	        return descripcion;
	    }

	    	    
	    public double getPrecio() {
	        return precio;
	    }

	    public int getCantidad(){
	    	return cantidad;
	    }
	    
	    public void setCantidad(int cantidad){
	    	this.cantidad = cantidad;
	    }
	    
	    public String toString() {
	        return "["+this.getDescripcion()+"] ["+this.getPrecio()+"] ["+this.getCantidad()+"]";
	    }
	    
}  
	


