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


    private List<Generation> generations = new ArrayList<>();
    private List<GameView> views = new ArrayList<GameView>();

    public MainController(){
        frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);

        contentPane = new JPanel();
        frame.add(contentPane);

        //input = new Input();
        //frame.addKeyListener(input);

        contentPane.setLayout(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < 9; i++) {
            GameView v = new GameView();
            views.add(v);

            contentPane.add(v);
        }

        frame.setVisible(true);

        Generation g = new Generation(9);

        for (int i = 0; i < g.getPopulationCount(); i++) {
            GameView gameView = views.get(i);
            Game game = new Game(4);
            gameView.setGame(game);

            double fitness = g.getAI(i).play(game, gameView, 50);
            System.out.println(fitness);
        }
    }
}
