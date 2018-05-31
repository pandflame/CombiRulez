import javax.swing.*;

public class MyMenu extends JFrame {
    // Menu Move
    private static final JMenuItem upIt = new JMenuItem("Up");
    private static final JMenuItem dwnIt = new JMenuItem("Down");
    private static final JMenu moveMenu = new JMenu("Move");
    // Menu Editing
    private static final JMenuItem rndmIt = new JMenuItem("Random");
    private static final JMenu  editMenu = new JMenu("Edit");
    // Menu File
    private static final JMenuItem newIt = new JMenuItem("New");
    private static final JMenuItem openIt = new JMenuItem("Open");
    private static final JMenuItem saveIt = new JMenuItem("Save as...");
    private static final JMenu fileMenu = new JMenu("File");
    // Menu Bar
    private static final JMenuBar menuBar = new JMenuBar();

    // Popoliamo i menù "dal basso verso l'alto" (nel costruttore)
    public MyMenu() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Move Menu
        moveMenu.add(upIt);
        moveMenu.add(dwnIt);
        // Edit Menu
        editMenu.add(moveMenu);
        editMenu.add(rndmIt);
        fileMenu.add(newIt);
        fileMenu.add(openIt);
        fileMenu.add(saveIt);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        /* Imposta la barra dei menu nel frame
        this.setJMenuBar(menuBar);
        // Impostazioni di visualizzazione
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        */
    }

    // Metodo Get
    public JMenuBar returnMenuBar() {
        return menuBar;
    }
}
