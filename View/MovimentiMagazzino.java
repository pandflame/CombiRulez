package View;

import Data.FilePackage.RequestManager;
import Data.ObjectPackage.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimentiMagazzino extends JFrame {
    private JPanel frmMovimenti;
    private JPanel inPanel;
    private JPanel outPanel;
    private JButton confermaButton2;
    private JLabel Label1;
    private JButton esciButton;
    private JButton confermaButton1;
    private JList listMovements;
    private JPanel campiPanel;
    private JButton addButton;
    private JTextField txtArticolo;
    private JTextField txtSettore;


    public MovimentiMagazzino(User utente) {

        // Imposto l'operazione di chiusura
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prendo il contentPane
        Container contentPane = this.getContentPane();
        contentPane.add(frmMovimenti);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

        Label1.setText("Sei loggato come magazziniere: " + utente.getUserFirstName() + " " + utente.getUserLastName());

        RequestManager movimentiUscita = new RequestManager();

        // Creo un array di String[] che contiene gli elementi nel database
        List<String[]> movimenti = new ArrayList<>();
        try {
            movimenti = movimentiUscita.dbView(utente.getUserRole(), 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Viene confermato un movimento in entrata premendo il tasto Conferma relativo
        confermaButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent inConfirmEvent) {

            }
        });

        // Viene confermato un movimento in uscita premendo il tasto Conferma relativo
        confermaButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent outConfirmEvent) {

            }
        });

        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exitEvent) {
                InterfacciaMagazziniere magazziniere = new InterfacciaMagazziniere(utente);
                dispose();
            }
        });

    }

}
