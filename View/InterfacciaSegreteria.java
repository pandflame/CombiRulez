package View;

import Data.FilePackage.RequestManager;
import Data.ObjectPackage.Item;
import Data.ObjectPackage.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InterfacciaSegreteria extends JFrame {
    private JPanel frmSegreteria;
    private JButton esciButton;
    private JButton confermaButton;
    private JLabel Label1;
    private JTextField textNome;
    private JTable tableEntrata;
    private JTable tableUscita;
    private JScrollPane entratePanel;
    private JScrollPane uscitePanel;
    private JTextField textDescrizione;
    private JTextField textSport;
    private JTextField textMateriale;

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

        RequestManager order = new RequestManager();
        Item articolo = new Item();

        // Creo un array di String[] che contiene gli elementi nel database degli ordini in entrata nel magazzino
        List<String[]> ordiniEntrata = new ArrayList<>();
        try {
            ordiniEntrata = order.dbView(utente.getUserRole(), 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Creo la tabella con gli ordini in entrata nel magazzino
        String[] titoloTableEntrata = {"Codice", "Articolo", "Data", "Settore"};
        String[][] datiTableEntrata = new String[ordiniEntrata.size()][4];

        for (int i = 0; i < ordiniEntrata.size(); i++) {
            String[] item = ordiniEntrata.get(i);
            for (int j = 0; j < 4; j++) {
                datiTableEntrata[i][j] = item[j];
            }
        }

        // Aggiungo all'interfaccia la tabella delle entrate in magazzino
        tableEntrata = new JTable(datiTableEntrata, titoloTableEntrata);
        entratePanel.setViewportView(tableEntrata);


        // Creo un array di String[] che contiene gli elementi nel database degli ordini in uscita dal magazzino
        List<String[]> ordiniUscita = new ArrayList<>();
        try {
            ordiniUscita = order.dbView(utente.getUserRole(), 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Creo la tabella con gli ordini in uscita dal magazzino
        String[] titoloTableUscita = {"Codice", "Articolo", "Data", "Corriere"};
        String[][] datiTableUscita = new String[ordiniUscita.size()][4];

        for (int i = 0; i < ordiniUscita.size(); i++) {
            String[] item = ordiniUscita.get(i);
            for (int j = 0; j < 4; j++) {
                datiTableUscita[i][j] = item[j];
            }
        }

        // Aggiungo all'interfaccia la tabella delle uscite in magazzino
        tableUscita = new JTable(datiTableUscita, titoloTableUscita);
        uscitePanel.setViewportView(tableUscita);

        // Confermo l'inserimento di un nuovo tipo articolo
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent addConfirmEvent) {
                Integer esito = 0;
                articolo.setName(textNome.getText());
                articolo.setDescription(textDescrizione.getText());
                articolo.setMaterial(textMateriale.getText());
                articolo.setSport(textSport.getText());

                try { esito = order.dbAction(articolo); } catch (IOException |SQLException e) { e.printStackTrace(); }

                if (esito == 1) {
                    JOptionPane.showMessageDialog(null, "Hai aggiunto un nuvo tipo di articolo");
                } else {
                    JOptionPane.showMessageDialog(null, "Errore");
                }
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
