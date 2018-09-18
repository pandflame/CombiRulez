package View;

import Data.ObjectPackage.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacciaSegreteria extends JFrame {
    private JPanel frmSegreteria;
    private JButton esciButton;
    private JList list1;
    private JButton confermaButton;
    private JLabel Label1;

    public InterfacciaSegreteria(User utente) {
        // Imposto l'operazione di chiusura
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prendo il contentPane
        Container contentPane = this.getContentPane();
        contentPane.add(frmSegreteria);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

        Label1.setText("Sei loggato come segretario: " + utente.getUserFirstName() + " " + utente.getUserLastName());

        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exitEvent) {
                Login schermataLogin = new Login();
                dispose();
            }
        });
    }

}
