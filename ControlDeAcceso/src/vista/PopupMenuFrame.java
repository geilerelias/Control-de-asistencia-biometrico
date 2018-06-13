/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author JORDI MORA V
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class PopupMenuFrame extends JFrame {
   private JPopupMenu popup;

   public PopupMenuFrame() {
       try {
           jbInit(); //para inicializar las propiedades del Frame
       } catch (Exception exception) {
           exception.printStackTrace();
       }
   }

   private void jbInit() throws Exception {
       setBounds(100, 100, 200, 200); //definimos la ubicacion del frame
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //... como salimos
       // definimos un 'adaptador' para manejar los eventos del ratón
       this.addMouseListener(new PopupMenuFrame_this_mouseAdapter(this));
       /*el adaptador es por fines de modularidad, aunque tambien declarariamos
        la clase PopupMenuFrame que implemente a la interfaz MouseAdapter*/
      
       popup = new JPopupMenu(); //creamos el menu saliente
       popup.add(new JMenuItem("Un elemento del menu")); //agregamos elemento
       popup.add(new JMenuItem("Otro elemento del menu")); //.. y otro elemento
   }
/* esto hará cuando se presione el boton del raton*/
   public void this_mousePressed(MouseEvent e) {
       mostrarPopupMenu(e);

   }
   /* esto hará cuando se libere el boton del raton. En algunos VM funcionan
    diferente. Este metodo y el anterior al final llaman
    al metodo mostrarPopupMenu()*/

   public void this_mouseReleased(MouseEvent e) {
       mostrarPopupMenu(e);
   }
/* Este metodo se encarga de mostrar el menu saliente */
   private void mostrarPopupMenu(MouseEvent e) {
      
       if (e.isPopupTrigger()) { //si se desea mostrar el menu saliente...
           popup.show(e.getComponent(),
                      e.getX(), e.getY()); //... mostramos el menu en la ubicacion del raton
       }

   }

   public static void main(String[] args) {
       new PopupMenuFrame().setVisible(true);
   }

}


class PopupMenuFrame_this_mouseAdapter extends MouseAdapter {
   private PopupMenuFrame adaptee;
   PopupMenuFrame_this_mouseAdapter(PopupMenuFrame adaptee) {
       this.adaptee = adaptee;
   }

   public void mousePressed(MouseEvent e) {
       adaptee.this_mousePressed(e);
   }

   public void mouseReleased(MouseEvent e) {
       adaptee.this_mouseReleased(e);
   }
}

