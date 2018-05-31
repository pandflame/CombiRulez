import java.awt.*;
import javax.swing.*;

public class MyPasswordFrame extends JFrame {

  // Lista delle variabili che uso all'interno del codice
  private static final long serialVersionUID = 1L;
  private static final JTextField usernameField = new JTextField(20);
  private static final JPasswordField passwordField = new JPasswordField(20);
  private static final JPanel textPanel = new JPanel(new FlowLayout());
  private static final JPanel labelPanel = new JPanel(new GridLayout(2,1, 2, 2));
  private static final JPanel buttonPanel = new JPanel();
  private static final JPanel boxPanel = new JPanel(new GridLayout(0,1,2,2));
  private static final JLabel usernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
  private static final JLabel passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
  private static final JButton login = new JButton("Login");

  public MyPasswordFrame() {

    // Imposto il titolo
    super("Benvenuto");

    // Imposto l'operazione di chiusura
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Aggiungo componenti
    labelPanel.add(usernameLabel);
    labelPanel.add(passwordLabel);
    boxPanel.add(usernameField);
    boxPanel.add(passwordField);
    buttonPanel.add(login);
    textPanel.add(labelPanel);
    textPanel.add(boxPanel);

    // Prendo il contentPane
    Container contentPane = this.getContentPane();
    contentPane.add(textPanel, BorderLayout.NORTH);
    contentPane.add(buttonPanel, BorderLayout.SOUTH);

    // Aggiungo un Listener
    MyPasswordListener pswListener = new MyPasswordListener(this);
    login.addActionListener(pswListener);

    // Aggiungo il menu
    MyMenu menu = new MyMenu();
    this.setJMenuBar(menu.returnMenuBar());

    // Setto la posizione
    this.setLocationRelativeTo(null);
    this.pack();
    // Pongo visibile il tutto
    this.setVisible(true);

  }

  // metodo get per username e password
  public static JTextField getUsername() {
    return usernameField;
  }
  public static JTextField getPassword() {
    return passwordField;
  }

}