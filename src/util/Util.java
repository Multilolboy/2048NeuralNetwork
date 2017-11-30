package util;
public class Util {

    public static boolean isR1CollidingWithR2(int x1,int y1, int w1, int h1, int x2, int y2, int w2, int h2){
            return x1 < y1 + w2  &&
                    y1 < x1 + w1  &&
                    y1 < y2 + h2 &&
                    y2 < y1 + h1;


    }
}
