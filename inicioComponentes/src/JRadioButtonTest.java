 

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
public class JRadioButtonTest{ 
@SuppressWarnings("uncheked")
public static void main(String args[]){ 
JFrame f = new JFrame(); 
f.setTitle("JRadioButton Tipos de Letras");// titulo del JFrame 
f.setSize(350,120); 
f.getContentPane().setLayout(new FlowLayout()); 
//Se crea un cuadro de texto inicializado con 30 columnas es final ya k utiliza clases internas 
final JTextField campo = new JTextField( "cambia el estilo del tipo de letra", 30); 
// se crean los componentes JCheckBox 
JRadioButton botonSimple = new JRadioButton("Simple",true); 
JRadioButton botonNegrita = new JRadioButton("Negrita",false); 
JRadioButton botonCursiva = new JRadioButton("Cursiva",false); 
// crear relaci�n l�gica entre objetos JRadioButton para que sean exclusivos 
ButtonGroup grupoBotonesOpcion = new ButtonGroup(); 
grupoBotonesOpcion.add(botonSimple); 
grupoBotonesOpcion.add(botonNegrita); 
grupoBotonesOpcion.add(botonCursiva); 
// registrar eventos para objetos JRadioButton 
botonSimple.addItemListener(new ItemListener(){ 
public void itemStateChanged(ItemEvent ev) 
{ 
Font tipoLetraSimple = new Font("Serif", Font.PLAIN, 14); 
campo.setFont(tipoLetraSimple); 
} 
}); 
// registrar eventos para objetos JRadioButton 
botonNegrita.addItemListener(new ItemListener(){ 
public void itemStateChanged(ItemEvent ev) 
{ 
Font tipoLetraNegrita = new Font("Serif", Font.BOLD, 14); 
campo.setFont(tipoLetraNegrita); 
} 
}); 
// registrar eventos para objetos JRadioButton 
botonCursiva.addItemListener(new ItemListener(){ 
public void itemStateChanged(ItemEvent ev) { 
Font tipoLetraCursiva = new Font("Serif", Font.ITALIC, 14); 
campo.setFont(tipoLetraCursiva); 
} 
}); 
f.getContentPane().add(campo);//Agrega el componente al Contenedor 
f.getContentPane().add(botonSimple);//Agrega el componente al Contenedor 
f.getContentPane().add(botonNegrita);//Agrega el componente al Contenedor 
f.getContentPane().add(botonCursiva);//Agrega el componente al Contenedor 
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// jdk1.3+=> Cerrar una ventana 
f.setVisible(true); 
}//end main() 
}