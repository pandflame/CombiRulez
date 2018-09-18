package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Data.ObjectPackage.User;

public class InterfacciaMagazziniere extends JFrame {
    private JButton inserisciMovimentoButton;
    private JButton spostaUnArticoloButton;
    private JPanel frmMagazziniere;
    private JLabel Label1;
    private JLabel Label2;
    private JButton esciButton;


    public InterfacciaMagazziniere(User utente) {

        // Imposto l'operazione di chiusura
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prendo il contentPane
        Container contentPane = this.getContentPane();
        contentPane.add(frmMagazziniere);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

        Label1.setText("Sei loggato come magazziniere: " + utente.getUserFirstName() + " " + utente.getUserLastName());

        inserisciMovimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent1) {
                MovimentiMagazzino movimenti = new MovimentiMagazzino(utente);
                dispose();
            }
        });

        spostaUnArticoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent2) {
                SpostamentiMagazzino spostamenti = new SpostamentiMagazzino(utente);
                dispose();
            }
        });

        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exitEvent) {
                Login schermataLogin = new Login();
                dispose();
            }
        });
    }

}
