 

import javax.swing.*;
import java.awt.*; /* Para GridLayout */
import java.awt.event.*;

public class CuatroBotonesConTexto {
	private JTextField texto;
	@SuppressWarnings("uncheked")
	public CuatroBotonesConTexto() {
		/*
		 Componentes - cuatro botones.
		 */
		String[] n = {"Norte", "Sur", "Este", "Oeste"};
		
		JButton  b = null;
		texto = new JTextField(20);
		//texto.setHorizontalAlignment(JTextField.CENTER);
		/*
		 Contenedor - una ventana
		 */
		JFrame jf = new JFrame("Una ventana con cuatro botones");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 Disposición - Vamos a utilizar la disposición predeterminada,
		 que es un BorderLayout.
		 */
		String[] disp = {
		   	BorderLayout.NORTH,
			BorderLayout.SOUTH,
			 BorderLayout.EAST,
			BorderLayout.WEST
			
		};
		Oyente oy= new Oyente();
		/*
		 Se añaden los componentes al contenedor
		 */
		for(int i=0; i<n.length;i++)
		{
			b = new JButton(n[i]);
			b.addActionListener(oy);
			jf.add(b,disp[i]);
		}
		jf.add(texto,BorderLayout.CENTER);
		/*
		 Se prepara el contenedor para su visualización
		 */
		jf.pack();
		jf.setLocationRelativeTo(null);
		/*
		 Finalmente, se hace visible el contenedor.
		 Por omisión, todos los componentes de Java se crean
		 sin visibilidad.
		 */
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
		CuatroBotonesConTexto c = new CuatroBotonesConTexto();
	}
	
	class Oyente implements ActionListener{
	    public void actionPerformed(ActionEvent ae)
	   {
	    	JButton b = (JButton)ae.getSource();
	    	texto.setText(b.getText());
	    }
     }
	
}
