package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

    private List<AI> population = new ArrayList<>();

    /**
     * Constructor for breeding
     *
     * @param previousGeneration
     */
    public Generation(Generation previousGeneration) {
        Random random = new Random();

        List<AI> best = previousGeneration.findBest(previousGeneration.getPopulationCount() / 3);
        for (AI ai : best) {
            ai.incrementAge();
        }
        population.addAll(best);

        for (int i = best.size(); i < previousGeneration.getPopulationCount(); i++) {
            AI male = best.get(random.nextInt(best.size() - 1));
            AI female = best.get(random.nextInt(best.size() - 1));

            AI offspring = new AI(male, female);
            population.add(offspring);
        }
    }

    public Generation(int population){
        for(int i = 0; i < population ; i++ ){
            this.population.add(new AI());
        }
    }

    public List<AI> findBest(int count) {
        List<AI> result = new ArrayList<>();

        for (int c = 0; c < count; c++) {
            AI best = null;
            for (int i = 0; i < getPopulationCount(); i++) {
                AI current = population.get(i);

                if (!result.contains(current)) {
                    if (best == null) {
                        best = current;
                    } else {
                        if (current.getFitness()> best.getFitness()) {
                            best = current;
                        }
                    }
                }
            }
            result.add(best);
        }

        return result;
    }

    public double getAverageFitness() {
        double sumFitness = 0F;

        for (AI ai : population) {
            sumFitness += ai.getFitness();
        }

        return sumFitness/ getPopulationCount();
    }

    public int getPopulationCount() {
        return population.size();
    }

    public AI getAI(int i) {
        return population.get(i);
    }

}
