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

        int scale = (int) (getWidth() / (double) graph.size());
        for (int x = 0; x < graph.size(); x++) {
            g2d.fillRect(x * scale, 0, scale, getHeight());
        }
    }

    public void putValue(double val) {
        graph.add(val);

        repaint();
    }

}
