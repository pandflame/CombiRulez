import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class TestFrame extends JFrame {

    private PassWordDialog passDialog;

    public TestFrame() {
        passDialog = new PassWordDialog(this, true);
        passDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new TestFrame();
                frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(400,400);
                frame.setTitle("Programma");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}
