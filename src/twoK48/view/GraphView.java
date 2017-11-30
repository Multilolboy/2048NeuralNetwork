package twoK48.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphView extends JPanel {

    private List<Double> graph = new ArrayList<>();

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.clearRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.BLACK);
        if (graph.size() > 0) {
            for (int x = 0; x < getWidth(); x++) {
                double val = graph.get((int) ((x / (double) getWidth()) * graph.size()));
                g2d.drawLine(x, getHeight(), x, getHeight() - (int) val);
            }
        }
    }

    public void putValue(double val) {
        graph.add(val);

        repaint();
    }

}
