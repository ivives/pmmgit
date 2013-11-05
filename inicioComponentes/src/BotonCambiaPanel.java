 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class BotonCambiaPanel extends JFrame implements ActionListener{
       JPanel panel;
   @SuppressWarnings("uncheked")
   public BotonCambiaPanel(){
   setTitle("Cambia el Color!!!");
   setSize(500,400);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   Container contentpanel = getContentPane();
   
   panel= new JPanel();
   panel.setLayout(new BorderLayout());
   
   JButton boton = new JButton("Azul");
   boton.addActionListener(this);
   
  /* Dimension d = new Dimension();
   d.height = 40;
   d.width = 100;
//Le pongo al bot�n un tama�o preferido, empleando para ello
//el objeto dimensi�n que he creado. 
  boton.setPreferredSize(d);*/
  //a�ado el layout al campo sur del panel
  panel.add(boton,BorderLayout.SOUTH);
  contentpanel.add(panel);
  panel.setBackground(Color.red);
  setVisible (true);
 }
//implementa la interface ActionListener
  public void actionPerformed (ActionEvent e){
    panel.setBackground(Color.blue);
}

public static void main (String[] args){
    BotonCambiaPanel boton = new BotonCambiaPanel();

}
}