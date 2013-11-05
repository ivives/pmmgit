import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CampoTexto extends JFrame
{
  JTextField t1;
  JButton textoControl;
  @SuppressWarnings("uncheked")
  
  
  public CampoTexto(){
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
   setLayout(new FlowLayout());
   t1 = new JTextField("Madrid",20); add(t1);
   
   textoControl= new JButton("Texto: ");
   add(textoControl);
   t1.addActionListener( new ActionListener(){    
        public void actionPerformed(ActionEvent e)
       {  textoControl.setText( "Texto: " + t1.getText() ); 
           }
    });
     
   
     setSize(300,300);
     setVisible(true);
     
   } //constructor
   
   public static void main( String args[]){
      CampoTexto pruebaCampoTexto=new CampoTexto();
}
}