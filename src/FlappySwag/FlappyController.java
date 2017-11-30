package FlappySwag;

import nn.Generation;
import nn.NeuralNetwork;
import nn.Unit;

import java.util.Random;

public class FlappyController {

    public static void main(String[] args) {
        new FlappyController();
    }

    public FlappyController() {
        Unit.Strategy strategy = new Unit.Strategy() {
            @Override
            public NeuralNetwork createNetwork() {
                return new NeuralNetwork(new int[] {2, 10, 1});
            }

            @Override
            public Unit breed(Unit u1, Unit u2) {
                Unit offspring = new Unit(u1.getStrategy());

                Random random = new Random();

                NeuralNetwork neuralNetwork = offspring.getNetwork();

                for (int l = 0; l < neuralNetwork.getLayerCount() - 1; l++) {
                    int connectionCount = neuralNetwork.countNeurons(l) * neuralNetwork.countNeurons(l + 1);
                    for (int n = 0; n < neuralNetwork.countNeurons(l); n++) {
                        for (int n1 = 0; n1 < neuralNetwork.countNeurons(l + 1); n1++) {
                            double currentWeight = neuralNetwork.getWeight(l, n, n1);
                            double weight1 = u1.getNetwork().getWeight(l, n, n1);
                            double weight2 = u2.getNetwork().getWeight(l, n, n1);
                            double newWeight;

                            //crossover
                            double r = random.nextDouble() * 0.1;
                            newWeight = r * weight1 + (1-r) * weight2;
                            //newWeight = n > neuralNetwork.countNeurons(l) / 3 ? weight1 : weight2;

                            //mutation
                            if (random.nextInt(connectionCount) < 2) {
                                newWeight = random.nextDouble() * 2D - 1D;
                            }

                            neuralNetwork.setWeight(l, n, n1, newWeight);
                        }
                    }
                }
                return offspring;
            }
        };

        Generation generation = new Generation(strategy, 10);
    }

}
