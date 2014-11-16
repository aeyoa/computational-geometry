package ui;

import main.Point2D;
import main.PointSet;
import main.convexhull.ConvexHull;
import main.convexhull.Graham;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author aeyoa
 */
public class GrahamUI {

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Graham");
        final Graham graham = new Graham();
        final HashSet<Point2D> pointSet = new HashSet<>();

        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT)) {

            {
                final ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        pointSet.clear();
                        repaint();
                    }
                };

                add(new JButton("Reset") {{
                    addActionListener(listener);
                }});

                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(final MouseEvent e) {
                        pointSet.add(new Point2D(e.getX(), e.getY()));
                        repaint();
                    }
                });
            }

            @Override
            public void paintComponent(final Graphics g) {
                super.paintComponent(g);
                final Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.BLUE);
                if (!pointSet.isEmpty()) {
                    for (Point2D point : pointSet) {
                        g2d.drawOval((int) point.getX() - 5, (int) point.getY() - 5, 10, 10);
                    }
                    final ConvexHull convexHull = graham.build(PointSet.getSetFromPoints(pointSet));
                    for (int i = 0; i < convexHull.getPoints().size(); i++) {
                        g2d.drawLine(
                                (int) convexHull.getPoint(i).getX(),
                                (int) convexHull.getPoint(i).getY(),
                                (int) convexHull.getPoint((i + 1) % convexHull.size()).getX(),
                                (int) convexHull.getPoint((i + 1) % convexHull.size()).getY());
                    }
                }
            }

        };

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
