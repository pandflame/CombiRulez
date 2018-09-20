package View;

import Data.FilePackage.DatabaseManager;
import Data.FilePackage.RequestManager;
import Data.ObjectPackage.Order;
import Data.ObjectPackage.Restock;
import Data.ObjectPackage.RestockItem;
import Data.ObjectPackage.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private JPanel campiPanel;
    private JButton addButton;
    private JTextField txtSettore;
    private JTextField txtArticolo;
    private JTable tableMovimentiUscita;
    private JScrollPane TablePanel;


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
        DatabaseManager dbMan = new DatabaseManager();
        Order ordine = new Order();
        Restock restockOrder = new Restock();
        List<RestockItem> restockItems = new ArrayList<RestockItem>();

        // Creo un array di String[] che contiene gli elementi nel database
        List<String[]> movimenti = new ArrayList<>();
        try {
            movimenti = movimentiUscita.dbView(utente.getUserRole(), 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Creo la tabella con i movimenti in uscita del magazzino
        String[] titoloTableMovimenti = {"Codice", "Data", "Negozio", "Articolo", "Quantità", "Costo"};
        String[][] datiTableMovimenti = new String[movimenti.size()][6];

        for (int i = 0; i < movimenti.size(); i++) {
            String[] item = movimenti.get(i);
            for (int j = 0; j < 6; j++) {
                datiTableMovimenti[i][j] = item[j];
            }
        }
        // Aggiungo all'interfaccia la tabella dei movimenti in uscita dal magazzino
        //tableMovimentiUscita = new JTable(datiTableMovimenti, titoloTableMovimenti);
        tableMovimentiUscita.setModel(new NonEditableModel(datiTableMovimenti, titoloTableMovimenti));
        TablePanel.setViewportView(tableMovimentiUscita);


        // Seleziono l'ordine che voglio evadere selezionandolo dalla tabella con il mouse
        tableMovimentiUscita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object orderCode = tableMovimentiUscita.getValueAt(tableMovimentiUscita.getSelectedRow(), 0);
                String oCode = (String) orderCode;
                ordine.setOrderCode(Integer.valueOf(oCode));
            }
        });

        // Viene confermato un movimento in uscita premendo il tasto Conferma relativo
        // A livello di DB, cambia lo status da 1 a 2
        confermaButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent outConfirmEvent) {
                if (movimentiUscita.completeOrder(ordine) == 1) {
                    JOptionPane.showMessageDialog(null, "Ordine evaso con successo");
                } else {
                    JOptionPane.showMessageDialog(null, "Errore");
                }
            }
        });


        // Aggiunta articolo a un ordine in entrata premendo il bottone di aggiunta
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestockItem restockItem = new RestockItem(txtArticolo.getText(), Integer.valueOf(txtSettore.getText()));
                restockItems.add(restockItem);
            }
        });

        // Viene confermato un movimento in entrata premendo il tasto Conferma relativo
        confermaButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent inConfirmEvent) {
                Integer esito = 0;
                restockOrder.setRestockCode(dbMan.getLastEntryNumber()+1);
                restockOrder.setRestockDate(LocalDate.now());
                restockOrder.setRestockItems(restockItems);

                try { esito = movimentiUscita.dbAction(restockOrder); } catch (IOException |SQLException e) {e.printStackTrace();}

                if (esito == 1) {
                    JOptionPane.showMessageDialog(null, "Inserimento avvenuto con successo.");
                } else {
                    JOptionPane.showMessageDialog(null, "Errore nell'inserimento.");
                }
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


    // Creo un nuovo modello di tabella per rendere le caselle non editabili
    public class NonEditableModel extends DefaultTableModel {

        public NonEditableModel(Object[][] data, Object[] columnNames){
            super(data,columnNames);
        }
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

}
