 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class JTextAreaTest extends JFrame 
{ 
private JTextArea areaTexto1, areaTexto2; 
private JButton botonCopiar; 
@SuppressWarnings("uncheked")

public JTextAreaTest() 
{ 
super("JTextArea"); 
setSize(450,150); 
getContentPane().setLayout(new FlowLayout()); 
String cadena = "�sta es una cadena de\ndemostraci�n para\n" + 
"ilustrar c�mo copiar texto\nde un �rea de texto a \n" + 
"otra, utilizando un\nevento externo"; 
// establecer areaTexto1 
areaTexto1 = new JTextArea(cadena,5,10); 
JScrollPane jsp1= new JScrollPane(areaTexto1); 
getContentPane().add(jsp1); 
// establecer botonCopiar 
botonCopiar = new JButton( "Copiar >>>" ); 
getContentPane().add(botonCopiar); 
botonCopiar.addActionListener(new ActionListener() 
{ // establecer en areaTexto2 el texto seleccionado de areaTexto1 
public void actionPerformed(ActionEvent ev) 
{ 
areaTexto2.append(areaTexto1.getSelectedText()); 
} 
}); 
// establecer areaTexto2 
areaTexto2 = new JTextArea(5,10); 
areaTexto2.setEditable(false); 
JScrollPane jsp2= new JScrollPane(areaTexto2); 
getContentPane().add(jsp2); 
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// jdk1.3+=> Cerrar una ventana 
setVisible(true); 
}// fin del constructor 
public static void main( String args[] ) 
{ 
JTextAreaTest aplicacion = new JTextAreaTest(); 
} 
}