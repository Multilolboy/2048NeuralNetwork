package FlappySwag;

public class Pipe {

    public static final int WIDTH = 20;

    int x, height, holeSize;

    public Pipe(int x, int height, int holeSize) {
        this.x = x;
        this.height = height;
        this.holeSize = holeSize;

    }

    public int getX() {
        return x;
    }

    public int getHeight() {
        return height;
    }

    public int getHoleSize() {
        return holeSize;
    }

    public void update(double deltaTime){
        x -= (int) FlappyGame.GAME_SPEED * deltaTime;
    }
}
