package ui;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author aeyoa
 */
public class Main extends JFrame {

    public Main() {
        initUI();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ps = new Main();
                ps.setVisible(true);
            }
        });
    }

    private void initUI() {

        setTitle("Graham Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Surface surface = new Surface();
        add(surface);

        setSize(500, 500);
        setLocationRelativeTo(null);

    }
}
