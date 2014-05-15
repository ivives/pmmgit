package com.example.pruebasproy;

public class Producto {
	
	
		public String codigo;
		public String descripcion;
		public double precio;
		public int cantidad;
//		public String total;

	    public Producto(String codigo, String descripcion, double precio, int cantidad) {
	        this.codigo = codigo;
	        this.descripcion = descripcion;
	        this.precio = precio;
	        this.cantidad = cantidad;
//	        this.total = total;
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
	    
//	    public String getTotal(){
//	    	return total;
//	    }
//	    
//	    public void setTotal(String total){
//	    	this.total = total;
//	    }
	    
	  	    
}  
	


