package ui;

import main.Point2D;
import main.PointSet;
import main.convexhull.ConvexHull;
import main.convexhull.Graham;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author aeyoa
 */
class Surface extends JPanel {

    public void draw(Graphics g) {

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        final Dimension size = getSize();
        final Insets insets = getInsets();

        int width = size.width - insets.left - insets.right;
        int height = size.height - insets.top - insets.bottom;

        final Random random = new Random();
        final ArrayList<Point2D> point2Ds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            int x = random.nextInt(width - 200) + 100;
            int y = random.nextInt(width - 200) + 100;
            point2Ds.add(new Point2D(x, y));
            g2d.drawOval(x - 5, y - 5, 10, 10);
        }
        final ConvexHull convexHull = new Graham().build(PointSet.getSetFromPoints(point2Ds));
        for (int i = 0; i < convexHull.getPoints().size(); i++) {
            g2d.drawLine((int) convexHull.getPoint(i).getX(), (int) convexHull.getPoint(i).getY(),
                    (int) convexHull.getPoint((i + 1) % convexHull.size()).getX(), (int) convexHull.getPoint((i + 1) % convexHull.size()).getY());
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                repaint();
            }
        });
        draw(g);
    }
}
