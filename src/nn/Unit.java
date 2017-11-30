package nn;

public class Unit {

    public interface Strategy {
        public NeuralNetwork createNetwork();

        public Unit breed(Unit u1, Unit u2);
    }

    private NeuralNetwork network;
    private int fitness;
    private int age;

    private Strategy strategy;

    public Unit(Strategy strategy) {
        this.strategy = strategy;

        this.network = strategy.createNetwork();
    }

    public Unit produceOffspring(Unit partner) {
        return strategy.breed(this, partner);
    }

    public NeuralNetwork getNetwork() {
        return network;
    }

    public int getFitness() {
        return fitness;
    }

    public int getAge() {
        return age;
    }

    public void incrementAge() {
        this.age++;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
