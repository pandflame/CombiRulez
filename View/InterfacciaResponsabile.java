package View;

import Data.FilePackage.DatabaseManager;
import Data.FilePackage.RequestManager;
import Data.ObjectPackage.ItemListComponent;
import Data.ObjectPackage.Order;
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

public class InterfacciaResponsabile extends JFrame {
    private JPanel frmResponsabile;
    private JPanel panelOrder;
    private JPanel panelCrono;
    private JButton confermaButton;
    private JButton esciButton;
    private JLabel Label1;
    private JScrollPane cronPanel;
    private JTable tableCrono;
    private JTextField textArticolo;
    private JTextField textQuantità;
    private JButton addButton;

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
        DatabaseManager dbMan = new DatabaseManager();
        Order ordine = new Order();
        List<ItemListComponent> orderItems = new ArrayList<ItemListComponent>();

        // Aggiunta articolo a un ordine premendo il bottone di aggiunta
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ItemListComponent articolo_ordine = new ItemListComponent(textArticolo.getText(), Integer.valueOf(textQuantità.getText()));
               orderItems.add(articolo_ordine);
            }
        });

        // Aggiungo l'ordine con i campi inseriti dal responsabile
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent addOrderEvent) {
                Integer esito = 0;
                ordine.setOrderCode(dbMan.getLastEntryNumber()+1);
                ordine.setOrderItemList(orderItems);
                ordine.setOrderDate(LocalDate.now());
                ordine.setOrderSource("Decathlon");
                ordine.setOrderCost(15);

                try { esito = order.dbAction(ordine); } catch (IOException |SQLException e) {e.printStackTrace();}

                    if (esito == 1) {
                        JOptionPane.showMessageDialog(null, "Ordine effettuato con successo.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore nell'inserimento.");

                    }

                }
        });

        /*
        // Creo un array di String[] che contiene gli elementi nel database
        List<String[]> ordini = new ArrayList<>();
        try {
            ordini = order.dbView(utente.getUserRole(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Creo la tabella con gli ordini effettuati
        String[] titoloTableOrdini = {"Codice", "Data", "Negozio", "Articolo", "Quantità", "Costo"};
        String[][] datiTableOrdini = new String[ordini.size()][6];

        for (int i = 0; i < ordini.size(); i++) {
            String[] item = ordini.get(i);
            for (int j = 0; j < 6; j++) {
                datiTableOrdini[i][j] = item[j];
            }
        }

        // Aggiungo all'interfaccia la tabella degli articoli in magazzino
        tableCrono.setModel(new NonEditableModel(datiTableOrdini, titoloTableOrdini));
        cronPanel.setViewportView(tableCrono);
        */


        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exitEvent) {
                Login schermataLogin = new Login();
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
