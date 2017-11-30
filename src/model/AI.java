package model;

import view.GameView;

import java.util.Random;

public class AI {

    private Random random = new Random();

    private NeuralNetwork neuralNetwork;
    private double fitness;
    private int age = 0;

    public AI() {
        this.neuralNetwork = new NeuralNetwork(new int[] {16, 150, 4});
        this.neuralNetwork.randomizeWeights();
    }

    public AI(AI male, AI female) {
        this();

        this.breed(male.neuralNetwork, female.neuralNetwork);
    }

    private void breed(NeuralNetwork male, NeuralNetwork female) {
        Random random = new Random();

        for (int l = 0; l < neuralNetwork.getLayerCount() - 1; l++) {
            int connectionCount = neuralNetwork.countNeurons(l) * neuralNetwork.countNeurons(l + 1);
            for (int n = 0; n < neuralNetwork.countNeurons(l); n++) {
                for (int n1 = 0; n1 < neuralNetwork.countNeurons(l + 1); n1++) {
                    double currentWeight = neuralNetwork.getWeight(l, n, n1);
                    double weight1 = male.getWeight(l, n, n1);
                    double weight2 = female.getWeight(l, n, n1);
                    double newWeight;

                    //crossover
                    //double r = random.nextBoolean() ? 1.0 : 0.0;
                    double r = random.nextDouble() * 0.2;
                    newWeight = r * weight1 + (1 - r) * weight2;
                    //newWeight = n > neuralNetwork.countNeurons(l) / 3 ? weight1 : weight2;

                    //mutation
                    if (random.nextInt(connectionCount) < 1) {
                        newWeight = random.nextDouble() * 2D - 1D;
                    }

                    neuralNetwork.setWeight(l, n, n1, newWeight);
                }
            }
        }
    }

    public void play(Game game, GameView view, int replays, int delay) {
        double sumFitness = 0.0;
        int gameCount = 0;

        int moveCount = 0;
        int countSameDirection = 0;
        Direction lastDirection = Direction.UP;
        while(true) {
            Direction nextMove = nextMove(game);
            if(nextMove == lastDirection){
                countSameDirection++;
            } else {
                countSameDirection = 0;
                lastDirection = nextMove;
            }

            boolean canMove = game.move(nextMove);
            moveCount++;

            view.repaint();

            if (canMove && countSameDirection < 10) {
                if (delay > 0) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                sumFitness += calcFitness(game.getField(), moveCount, game.getMergeCount(), game.getHighestValue(), game.getMovesWithoutMerge());
                gameCount++;

                if (gameCount >= replays) {
                    break;
                } else {
                    game.resetGame();
                    countSameDirection = 0;
                    moveCount = 0 ;
                }
            }
        }

        this.fitness = sumFitness / (double) replays;
    }

    private double calcFitness(int[][] field, int moveCount, int mergeCount, int highestValue, int movesWithoutMerge) {
        double reward = 0.0;

        if (highestValue >= 5) {
            reward += highestValue * 2.0;
        }

        if (movesWithoutMerge <= 10) {
            reward += (10 - movesWithoutMerge) * 1.6;
        }

        if (highestValue >= 6) {
            if (field[0][0] == highestValue || field[3][0] == highestValue || field[0][3] == highestValue || field[3][3] == highestValue) {
                reward += highestValue * 1.9; //reward!
            }
        }

        return mergeCount + reward;
    }

    private Direction nextMove(Game game) {
        int[][] field = game.getField();
        double[] input = new double[field.length * field.length];
        for(int i = 0 ; i < field.length ; i++){
            for(int j = 0 ; j < field.length ; j++){
                input[i+j*4] = (double) field[i][j] / (double) game.getHighestValue();
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

    public int getAge() {
        return age;
    }

    public void incrementAge() {
        this.age++;
    }


}
