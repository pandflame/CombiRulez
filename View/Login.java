package View;

import Data.FilePackage.AuthenticationManager;
import Data.ObjectPackage.User;
import Data.FilePackage.ObjectManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    
    private JPanel logPanel;
    private JPasswordField txtPasswd;
    private JTextField txtUsername;
    private JButton loginButton;
    private JLabel Label1;

    /*
    Creo la finestra di Login
     */
    public Login() {

        super("Login");
        // Imposto l'operazione di chiusura
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Prendo il contentPane
        Container contentPane = this.getContentPane();
        contentPane.add(logPanel);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);

        // Se premo il bottone di Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    AuthenticationManager aManager = new AuthenticationManager();
                    User utente = new User();
                    utente = aManager.login(txtUsername.getText(), String.valueOf(txtPasswd.getPassword()));
                    if (utente != null) {
                        if (utente.getUserRole() == 3) {
                            InterfacciaMagazziniere magazziniere = new InterfacciaMagazziniere(utente);
                            dispose();
                        } else if (utente.getUserRole() == 1) {
                            InterfacciaResponsabile responsabile = new InterfacciaResponsabile(utente);
                            dispose();
                        } else if (utente.getUserRole() == 2) {
                            InterfacciaSegreteria segretario = new InterfacciaSegreteria(utente);
                            dispose();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "L'utente inserito non esiste");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    /*
    Lancio l'applicazione
     */
    public static void main(String[] args) {
        new Login();
    }
}
