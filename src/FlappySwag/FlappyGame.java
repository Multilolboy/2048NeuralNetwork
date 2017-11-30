package FlappySwag;

import nn.Generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlappyGame {

    public static final int GAME_HEIGHT = 600;
    private static final int PIPE_DISTANCE = 200;
    public static final double GAME_SPEED = 50D;
    public static final int BIRD_X_COORD = 100;

    private int score;
    private int indexOfNearestPipe;
    private List<Bird> birds = new ArrayList<>();
    private List<Pipe> pipes = new ArrayList<>();

    public FlappyGame(Generation generation) {
        Random random = new Random();
        for (int i = 0; i < generation.getPopulationCount(); i++) {
            birds.add(new Bird(generation.getUnit(i), 50));
        }
        for(int i = 0; i < 1000 ; i++){
            pipes.add(new Pipe(400+ i * PIPE_DISTANCE ,random.nextInt(300),100 ));
        }
        indexOfNearestPipe = 0;
    }



    public void play(){
        double deltaTime;
        long startTime = System.nanoTime();
        long totalTime = startTime;
        int frames = 0;
        while (true) {

            long currentTime = System.nanoTime();
            deltaTime =  ((currentTime - startTime) / 1000000000D);
            startTime = currentTime;
            frames++;
            if (currentTime - totalTime > 1000000000) {
                System.out.println("fps: " + frames);
                frames = 0;

                totalTime = currentTime;
            }


            for(int i = 0 ; i < birds.size() ; i++){
                birds.get(i).update(deltaTime,pipes.get(indexOfNearestPipe));
            }

            for(int i = 0 ; i < pipes.size() ; i++){
                pipes.get(i).update(deltaTime);
            }
            if(pipes.get(indexOfNearestPipe).getX()+Pipe.WIDTH <= BIRD_X_COORD){
                indexOfNearestPipe++;
                score++;
            }



        }

    }




}
