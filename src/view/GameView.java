package view;

import model.Direction;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private Game game;

    public GameView(){
    }

    public void move(Direction dir){
        if (!game.move(dir)) {
            JOptionPane.showMessageDialog(null, "Go kill yourself fuck*** N****");
        }
        this.repaint();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (game != null) {
            int width = getWidth() / game.size();
            int height = getHeight() / game.size();
            for (int i = 0; i < game.size(); i++) {
                for (int j = 0; j < game.size(); j++) {
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawRect(i * width, j * height, width - 1, height - 1);
                    if (game.getField()[i][j] != 0) {
                        g2d.drawString(Integer.toString((int) Math.pow(2, game.getField()[i][j])), i * width + width / 2, j * height + height / 2);
                    }
                }
            }
        }

    }





    
    
    
}
