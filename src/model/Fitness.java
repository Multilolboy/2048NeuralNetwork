package model;

public class Fitness implements Comparable<Fitness>{
    private int mergeCount = 0;
    private int highestValue = 0;
    private int moveCount = 0;


    public int getMergeCount() {
        return mergeCount;
    }

    public int getHighestValue() {
        return highestValue;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMergeCount(int mergeCount) {
        this.mergeCount = mergeCount;
    }

    public void setHighestValue(int highestValue) {
        this.highestValue = highestValue;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    @Override
    public int compareTo(Fitness fitness) {
        if(this.highestValue > fitness.getHighestValue())
            return 1;
        if(this.highestValue == fitness.getHighestValue()) {
            if (this.mergeCount > fitness.getMergeCount())
                return 1;
            if (this.mergeCount == fitness.getMergeCount())
                if (this.moveCount < fitness.getMoveCount())
                    return 1;
        }
        return -1;

    }

    public void mergeIn(int mergeCount, int highestValue, int moveCount){
        this.mergeCount = (this.mergeCount+mergeCount)/2;
        this.highestValue = (this.highestValue + highestValue)/2;
        this.moveCount = (this.moveCount + moveCount) / 2;
    }

    public void mergeIn(Fitness fitness){
        mergeIn(fitness.getMergeCount(), fitness.getHighestValue(), fitness.getMoveCount());
    }

    @Override
    public String toString() {
        return "\n mergeCount: " + mergeCount + "\n highestValue: "+ highestValue +" \n moveCount: "+ moveCount;
    }
}
