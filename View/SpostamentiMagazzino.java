package View;

import Data.FilePackage.RequestManager;
import Data.ObjectPackage.WarehouseItem;
import Data.ObjectPackage.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpostamentiMagazzino extends JFrame {
    private JButton esciButton;
    private JPanel frmSpostamenti;
    private JComboBox NewSector;
    private JButton confermaButton;
    private JLabel Label1;
    private JPanel FrontPanel;
    private JPanel OptionsPanel;
    private JScrollPane TablePanel;
    private JTable tableMagazzino;


    public SpostamentiMagazzino(User utente) {

        // Imposto l'operazione di chiusura
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prendo il contentPane
        Container contentPane = this.getContentPane();
        contentPane.add(frmSpostamenti);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

        WarehouseItem articolo = new WarehouseItem();
        RequestManager gestioneSpostamento = new RequestManager();

        Label1.setText("Sei loggato come magazziniere: " + utente.getUserFirstName() + " " + utente.getUserLastName());

        // Creo il comboBox per selezionare il settore
        Integer sector1 = 1;
        NewSector.addItem(sector1);
        Integer sector2 = 2;
        NewSector.addItem(sector2);
        Integer sector3 = 3;
        NewSector.addItem(sector3);
        Integer sector4 = 4;
        NewSector.addItem(sector4);
        Integer sector5 = 5;
        NewSector.addItem(sector5);


        // Creo un array di String[] che contiene gli elementi nel database
        List<String[]> articles = new ArrayList<>();
        try {
            articles = gestioneSpostamento.dbView(utente.getUserRole(), 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Creo la tabella con gli elementi del magazzino
        String[] titoloTableMagazzino = {"Codice", "Tipo", "Costo", "Data", "Settore"};
        String[][] datiTableMagazzino = new String[articles.size()][5];

        for (int i = 0; i < articles.size(); i++) {
            String[] item = articles.get(i);
            for (int j = 0; j < 5; j++) {
                datiTableMagazzino[i][j] = item[j];
            }
        }

        // Aggiungo all'interfaccia la tabella degli articoli in magazzino
        tableMagazzino = new JTable(datiTableMagazzino, titoloTableMagazzino);
        //tableMagazzino.setModel(new NonEditableModel(datiTableMagazzino, titoloTableMagazzino));
        TablePanel.setViewportView(tableMagazzino);

        // Seleziono l'articolo che mi interessa spostare con un listener sulla tabella che legge cosa seleziono col mouse
        tableMagazzino.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1) {
                    Object itemCode = tableMagazzino.getValueAt(tableMagazzino.getSelectedRow(), 0);
                    String iCode = (String) itemCode;
                    articolo.setWarehouseItemCode(iCode);
                }
            }
        });

        // Se premuto il tasto conferma, faccio la richiesta di spostamento
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (gestioneSpostamento.changeItemSector(articolo, (Integer) NewSector.getSelectedItem()) == 1) {
                    JOptionPane.showMessageDialog(null, "Spostamento avvenuto con successo");
                } else {
                    JOptionPane.showMessageDialog(null, "Errore");
                }
            }
        });

        // Se premo il tasto esci, torno all'interfaccia del magazziniere
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

