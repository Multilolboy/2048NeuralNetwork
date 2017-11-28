package view;

import model.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private GameView gameView;

    public Input(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                gameView.move(Direction.LEFT);
                break;

            case KeyEvent.VK_RIGHT:
                gameView.move(Direction.RIGHT);

                break;

            case KeyEvent.VK_UP:
                gameView.move(Direction.UP);

                break;

            case KeyEvent.VK_DOWN:
                gameView.move(Direction.DOWN);

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
