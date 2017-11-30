package FlappySwag;

import nn.Unit;
import util.Util;

public class Bird {

    public static final int SIZE = 20;

    private Unit ai;
    private int height;
    private double speed;


    public Bird(Unit ai, int height) {
        this.ai = ai;
        this.height = height;
        speed = 0;
    }

    public void update(double deltaTime, Pipe nearestPipe){
        height += (int) speed * deltaTime;
        if(height + SIZE > FlappyGame.GAME_HEIGHT){
            this.die(0);
        }else if(height-SIZE < 0){
            this.die(1);
        }else if(
                Util.isR1CollidingWithR2(FlappyGame.BIRD_X_COORD,this.height,SIZE,SIZE,
                        nearestPipe.getX(), nearestPipe.getHeight(),Pipe.WIDTH,FlappyGame.GAME_HEIGHT-nearestPipe.getHeight()) ){
            this.die(2);
        }else if(
                Util.isR1CollidingWithR2(FlappyGame.BIRD_X_COORD,this.height,SIZE,SIZE,
                        nearestPipe.getX(), 0,Pipe.WIDTH,nearestPipe.getHeight()-nearestPipe.getHoleSize())) {
            this.die(3);
        }
        
    }


    /**
     *
     * @param cause 0 = Hit the Bottom
     *              1 = Hit the Top
     *              2 = Hit the Lower Pipe
     *              3 = Hit the Upper Pipe
     */
    public void die(int cause){

    }

    public void jump(){

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
