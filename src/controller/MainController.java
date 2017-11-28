package controller;

import model.AI;
import model.Game;
import model.Generation;
import view.GameView;
import view.Input;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    public static void main(String[] args){
        new MainController();
    }

    private JFrame frame;
    private JPanel contentPane;
    private Input input;

    public MainController(){
        frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);

        contentPane = new JPanel();
        frame.add(contentPane);

        //input = new Input();
        //frame.addKeyListener(input);

        contentPane.setLayout(new GridLayout(3, 3, 10, 10));

        List<GameView> views = new ArrayList<GameView>();
        for (int i = 0; i < 9; i++) {
            GameView v = new GameView();
            views.add(v);

            contentPane.add(v);
        }

        frame.setVisible(true);

        List<Generation> generations = new ArrayList<>();
        Generation generation = null;
        while(true) {
            if (generation == null) {
                System.out.println("Starting Evolution...");
                generation = new Generation(9);
            } else {
                System.out.println("Breeding Generation number " + generations.size());
                generation = new Generation(generation);//breeding
            }
            generations.add(generation);

            for (int i = 0; i < generation.getPopulationCount(); i++) {
                GameView gameView = views.get(i);
                Game game = new Game(4);
                gameView.setGame(game);

                double fitness = generation.getAI(i).play(game, gameView, 5);
                System.out.println("AI(" + i + ") performed with fitness: " + fitness);
            }

            for (GameView v : views) {
                v.setGame(null);
                v.repaint();
            }
        }
    }
}
