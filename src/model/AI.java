package model;

import view.GameView;

import java.util.Random;

public class AI {

    private Random random = new Random();

    private NeuralNetwork neuralNetwork;
    private double fitness = -1D;

    public AI() {
        this.neuralNetwork = new NeuralNetwork(new int[] {16, 46, 4});
    }

    public AI(AI male, AI female) {
        this();

        this.neuralNetwork.mixWeights(male.neuralNetwork, female.neuralNetwork);
    }

    public double play(Game game, GameView view, int delay) {
        int countMoves = 0;
        int countSameDirection = 0;
        Direction lastDirection = Direction.UP;
        while(true) {
            Direction nextMove = nextMove(game.getField());
            if(nextMove == lastDirection){
                countSameDirection++;
            } else {
                countSameDirection = 0;
                lastDirection = nextMove;
            }

            boolean canMove = game.move(nextMove);
            countMoves++;

            view.repaint();

            if (canMove && countSameDirection < 10) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                fitness = countMoves;//TODO improve calculation!

                return fitness;
            }
        }
    }

    private Direction nextMove(int[][] field) {
        double[] input = new double[field.length * field.length];
        for(int i = 0 ; i < field.length ; i++){
            for(int j = 0 ; j < field.length ; j++){
                input[i+j*4] = (double) field[i][j] / 2048D;
            }
        }

        double[] output = neuralNetwork.calculate(input);

        double maxValue = 0;
        int max = 0;
        for(int i = 0 ; i < output.length ; i++) {
            if (output[i] >= maxValue){
                maxValue = output[i];
                max = i;
            }
        }

        return Direction.values()[max];
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
