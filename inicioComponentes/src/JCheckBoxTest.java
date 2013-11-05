 
import javax.swing.JFrame; 
import javax.swing.JCheckBox; 
import java.awt.FlowLayout; 
import java.awt.Color; 
import java.awt.event.ItemListener; 
import java.awt.event.ItemEvent; 
public class JCheckBoxTest{ 
@SuppressWarnings("uncheked")
    public static void main(String args[]){ 
       JFrame f = new JFrame(); 
       f.setTitle("Ricos Helados");// titulo del JFrame 
       f.setSize(370,145); 
       f.getContentPane().setLayout(new FlowLayout()); 
      // se crean los componentes JCheckBox 
      JCheckBox fresa = new JCheckBox("FRESA");//Se inicializa con FRESA 
      JCheckBox mango = new JCheckBox(); 
      JCheckBox limon = new JCheckBox(); 
      
      fresa.setBackground(Color.pink); // color del fondo, hereda de la clase JComponent 
      fresa.addItemListener(new ItemListener() // se agrega un oyente 
         { 
           public void itemStateChanged(ItemEvent ev){ 
                 fresita(ev);// se delega el evento 
          } 
        }); 
       
        mango.setText("MANGO"); 
        mango.setBackground(Color.yellow); 
        mango.addItemListener(new ItemListener() 
          { 
           public void itemStateChanged(ItemEvent ev){ 
              if(ev.getStateChange()== ItemEvent.SELECTED) 
                  System.out.println("Rico helado de mango"); 
              else 
                  System.out.println("Deselecciono mango"); 
           } 
            }); 

         limon.setText("LIMON"); 
         limon.setBackground(Color.green); 
         limon.addItemListener(new ItemListener() 
            { 
             public void itemStateChanged(ItemEvent ev){ 
                if(ev.getStateChange() == ItemEvent.SELECTED) 
                    System.out.println("Rico helado de lim�n"); 
                else 
                     System.out.println("Deselecciono Lim�n"); 
              } 
}); 

f.getContentPane().add(fresa);//Agrega el componente al Contenedor 
f.getContentPane().add(mango);//Agrega el componente al Contenedor 
f.getContentPane().add(limon);//Agrega el componente al Contenedor 
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// jdk1.3+=> Cerrar una ventana 
f.setVisible(true); 

}//end main() 
private static void fresita(ItemEvent ev){ 
if(ev.getStateChange() == ItemEvent.SELECTED) 
System.out.println("Rico helado de Fresa"); 
else 
System.out.println("Deselecciono Fresa"); 

} 
}