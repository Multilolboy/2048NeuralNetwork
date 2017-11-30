package nn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

    private List<Unit> population = new ArrayList<>();

    /**
     * Constructor for breeding
     *
     * @param previousGeneration
     */
    public Generation(Generation previousGeneration) {
        Random random = new Random();

        List<Unit> best = previousGeneration.findBest(previousGeneration.getPopulationCount() / 3);
        for (Unit unit : best) {
            unit.incrementAge();
        }
        population.addAll(best);

        for (int i = best.size(); i < previousGeneration.getPopulationCount(); i++) {
            Unit male = best.get(random.nextInt(best.size() - 1));
            Unit female = best.get(random.nextInt(best.size() - 1));

            Unit offspring = male.produceOffspring(female);
            population.add(offspring);
        }
    }

    public Generation(Unit.Strategy strategy, int population){
        for(int i = 0; i < population ; i++ ){
            this.population.add(new Unit(strategy));
        }
    }

    public List<Unit> findBest(int count) {
        List<Unit> result = new ArrayList<>();

        for (int c = 0; c < count; c++) {
            Unit best = null;
            for (int i = 0; i < getPopulationCount(); i++) {
                Unit current = population.get(i);

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

        for (Unit unit : population) {
            sumFitness += unit.getFitness();
        }

        return sumFitness/ getPopulationCount();
    }

    public int getPopulationCount() {
        return population.size();
    }

    public Unit getUnit(int i) {
        return population.get(i);
    }

}
