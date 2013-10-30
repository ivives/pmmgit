 

import java.awt.FlowLayout; 
import javax.swing.JFrame; 
import javax.swing.JList; 
import javax.swing.JScrollPane; 
//nuevos eventos de swing 
import javax.swing.event.ListSelectionEvent; 
import javax.swing.event.ListSelectionListener; 
public class JListTest { 
public static void main(String[] args) { 
JFrame f=new JFrame(); 
f.setTitle("JListTest"); 
f.setSize(200,230); 
f.getContentPane().setLayout(new FlowLayout()); 
String labels[] = { "OPCION_1", "OPCION_2", "OPCION_3", "OPCION_4", 
"OPCION_5", "OPCION_6", "OPCION_7", "OPCION_8", 
"OPCION_9", "OPCION_10" }; 
JList lista = new JList(labels); 
//f.getContentPane().add(lista); 
JScrollPane scrollPane = new JScrollPane(lista); 
f.getContentPane().add(scrollPane); 
ListSelectionListener listSelectionListener = new ListSelectionListener() { 
public void valueChanged(ListSelectionEvent ev) { 
JList list = (JList) ev.getSource(); 
int indice = list.getSelectedIndex(); 
Object valor = list.getSelectedValue(); 
System.out.println("Indice=> " + indice + "valor=> " + valor); 
} 
}; 
lista.addListSelectionListener(listSelectionListener); 
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
f.setVisible(true); 
}// end main() 
}
