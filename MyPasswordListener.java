import java.awt.event.*;
import javax.swing.*;

public class MyPasswordListener implements ActionListener {

  // Inizializzo una serie di variabili che mi serviranno in seguit, nella dichiarazione della classe
  private MyPasswordFrame myFrame;
  private static final JButton north = new JButton("North");

  MyPasswordListener(MyPasswordFrame frame) { this.myFrame = frame; }

  public void actionPerformed (ActionEvent e) {
    //JOptionPane.showMessageDialog(null, myFrame.getUsername().getText());
    //JOptionPane.showMessageDialog(null, myFrame.getPassword().getText());

    // Salvo su file il contenuto di username e password
    String usr = myFrame.getUsername().getText();
    String pswd = myFrame.getUsername().getText();

    // Rimuovo tutto il contenuto del frame
    myFrame.getContentPane().removeAll();

    // Aggiungo al ContentPane
    myFrame.getContentPane().add(north);

    // Ripopolo il Frame
    myFrame.validate();
    myFrame.repaint();

  }
  
}