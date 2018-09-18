package View;

import Data.ObjectPackage.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacciaResponsabile extends JFrame {
    private JPanel frmResponsabile;
    private JPanel panelOrder;
    private JPanel panelCrono;
    private JList listCrono;
    private JButton confermaButton;
    private JButton esciButton;
    private JLabel Label1;

    public InterfacciaResponsabile(User utente) {

        // Imposto l'operazione di chiusura
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prendo il contentPane
        Container contentPane = this.getContentPane();
        contentPane.add(frmResponsabile);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

        Label1.setText("Sei loggato come responsabile: " + utente.getUserFirstName() + " " + utente.getUserLastName());

        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exitEvent) {
                Login schermataLogin = new Login();
                dispose();
            }
        });
    }

}
