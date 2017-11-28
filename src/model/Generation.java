package model;

import java.util.ArrayList;
import java.util.List;

public class Generation {

    private List<AI> ai = new ArrayList<>();

    public Generation(int population){
        for(int i = 0; i < population ; i++ ){
            ai.add(new AI());
        }
    }

    public int getPopulationCount() {
        return ai.size();
    }

    public AI getAI(int i) {
        return ai.get(i);
    }

}
