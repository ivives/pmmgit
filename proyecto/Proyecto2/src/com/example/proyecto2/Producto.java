package com.example.proyecto2;

public class Producto {
	
		
		private String codigo;
		private String descripcion;
		private String precio;
		private String cantidad;
		private String total;

	    public Producto(String codigo, String descripcion, String precio, String cantidad, String total) {
	        this.codigo = codigo;
	        this.descripcion = descripcion;
	        this.precio = precio;
	        this.cantidad = cantidad;
	        this.total = total;
	        
	    }

	      
	   
	    public String getCodigo() {
	        return codigo;
	    }

	   
	    public String getDescripcion() {
	        return descripcion;
	    }

	    	    
	    public String getPrecio() {
	        return precio;
	    }

	    public String getCantidad(){
	    	return cantidad;
	    }
	    
	    public void setCantidad(String cantidad){
	    	this.cantidad = cantidad;
	    }
	    
	    public String getTotal(){
	    	return total;
	    }
	    
	    public void setTotal(String total){
	    	this.total = total;
	    }
	    
	  	    
}  
	


