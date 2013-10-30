 

import javax.swing.*;
import java.awt.*; /* Para GridLayout */
import java.awt.event.*;

public class CuatroBotones{
@SuppressWarnings("uncheked")
  public CuatroBotones() {
		/*
			Componentes - cuatro botones.
		*/
    String[] nombres = {"Uno", "Dos", "Tres", "Cuatro"};
    JButton  b = null;
		/*
			Contenedor - una ventana
		*/
    JFrame jf = new JFrame("Una ventana con cuatro botones");
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
			Disposici�n - una cuadr�cula con 20 p�xeles de separaci�n
			horizontal y vertical entre componentes.
		*/
		jf.setLayout(new GridLayout(2,2,20,20));
		/*
			Se a�aden los componentes al contenedor
		*/
    for(String s : nombres)
    	jf.add(new JButton(s));
    /*
    	Se prepara el contenedor para su visualizaci�n
    */
		jf.pack();
		jf.setLocationRelativeTo(null);
		/*
			Finalmente, se hace visible el contenedor.
			Por omisi�n, todos los componentes de Java se crean
			sin visibilidad.
		*/
		jf.setVisible(true);
	}
	public static void main(String[] args) {
			CuatroBotones c = new CuatroBotones();
	}
}
