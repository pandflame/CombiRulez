package View;

import Data.FilePackage.RequestManager;
import Data.ObjectPackage.Order;
import Data.ObjectPackage.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InterfacciaResponsabile extends JFrame {
    private JPanel frmResponsabile;
    private JPanel panelOrder;
    private JPanel panelCrono;
    private JButton confermaButton;
    private JButton esciButton;
    private JLabel Label1;
    private JScrollPane cronPanel;
    private JTable tableCrono;

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

        RequestManager order = new RequestManager();

        // Creo un array di String[] che contiene gli elementi nel database
        List<String[]> ordini = new ArrayList<>();
        try {
            ordini = order.dbView(utente.getUserRole(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Creo la tabella con gli elementi del magazzino
        String[] titoloTableOrdini = {"Codice", "Data", "Negozio", "Articolo", "Quantità", "Costo"};
        String[][] datiTableOrdini = new String[ordini.size()][6];

        for (int i = 0; i < ordini.size(); i++) {
            String[] item = ordini.get(i);
            for (int j = 0; j < 6; j++) {
                datiTableOrdini[i][j] = item[j];
            }
        }

        // Aggiungo all'interfaccia la tabella degli articoli in magazzino
        tableCrono = new JTable(datiTableOrdini, titoloTableOrdini);
        cronPanel.setViewportView(tableCrono);

        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exitEvent) {
                Login schermataLogin = new Login();
                dispose();
            }
        });

    }

}
