 
import java.awt.FlowLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.JComboBox; 
import javax.swing.JFrame; 

public class JComboBoxTest { 
@SuppressWarnings("uncheked")
public static void main(final String args[]) { 
final String labels[]= {"OPCION_A", "OPCION_B", "OPCION_C", "OPCION_D", "OPCION_E"}; 
JFrame f = new JFrame("JComboBox"); 
f.setSize(350,100); 
f.getContentPane().setLayout(new FlowLayout()); //Coloca los componentes de forma consecutiva 
JComboBox comboBox = new JComboBox(labels); 
comboBox.setSelectedIndex(0); 
f.getContentPane().add(comboBox);//Agrega el el combo al Contenedor 
/*Forma 1*/ 
comboBox.addActionListener(new ActionListener() { 
public void actionPerformed(ActionEvent ev){ 
JComboBox cb = (JComboBox)ev.getSource(); 
String opcionSelec = (String)cb.getSelectedItem(); 
System.out.println("Selected: " + opcionSelec); 
} 
}); 

/*Forma 2*/ 
/*ActionListener actionListener = new ActionListener() { 
public void actionPerformed(ActionEvent ev){ 
JComboBox cb = (JComboBox)ev.getSource(); 
String opcionSelec = (String)cb.getSelectedItem(); 
System.out.println("Selected: " + opcionSelec); 
} 
}; 
comboBox.addActionListener(actionListener);*/ 
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Cierra la ventana 
f.setVisible(true); 
}// end main() 
}