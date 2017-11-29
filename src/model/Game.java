package model;

import java.util.Random;

public class Game {

    private int[][] field;
    private int mergeCount;

    public Game(int size) {
        field = new int[size][size];
        addNumberToField();
        addNumberToField();
        mergeCount = 0;
    }

    public void resetGame() {
        for(int i = 0; i < field.length ; i++){
            for(int j = 0 ; j < field.length ; j++){
                field[i][j] = 0;
            }
        }
        mergeCount = 0;
    }

    public boolean move(Direction dir) {
        switch (dir) {
            case UP:
                if(canMove(Direction.UP)) {
                    for (int i = 0; i < field.length; i++) {
                        for (int j = 1; j < field.length; j++) {
                            int y = j;
                            if (field[i][y] != 0) {
                                while (y > 1 && field[i][y - 1] == 0) {
                                    y--;
                                }
                                int tmp = field[i][j];
                                field[i][j] = 0;

                                if (field[i][y - 1] == tmp) {
                                    field[i][y - 1]++;
                                    mergeCount++;
                                } else if (field[i][y - 1] == 0) {
                                    field[i][y - 1] = tmp;
                                } else {
                                    field[i][y] = tmp;
                                }

                            }

                        }

                    }
                }
                break;
            case DOWN:
                if(canMove(Direction.DOWN)) {
                    for (int i = 0; i < field.length; i++) {
                        for (int j = field.length - 2; j >= 0; j--) {
                            int y = j;
                            if (field[i][y] != 0) {
                                while (y < field.length - 2 && field[i][y + 1] == 0) {
                                    y++;
                                }
                                int tmp = field[i][j];
                                field[i][j] = 0;

                                if (field[i][y + 1] == tmp) {
                                    field[i][y + 1] += 1;
                                    mergeCount++;
                                } else if (field[i][y + 1] == 0) {
                                    field[i][y + 1] = tmp;
                                } else {
                                    field[i][y] = tmp;
                                }

                            }

                        }

                    }
                }
                break;
            case LEFT:
                if(canMove(Direction.LEFT)) {
                    for (int i = 1; i < field.length; i++) {
                        for (int j = 0; j < field.length; j++) {
                            int x = i;
                            if (field[x][j] != 0) {
                                while (x > 1 && field[x - 1][j] == 0) {
                                    x--;
                                }
                                int tmp = field[i][j];
                                field[i][j] = 0;

                                if (field[x - 1][j] == tmp) {
                                    field[x - 1][j]++;
                                    mergeCount++;
                                } else if (field[x - 1][j] == 0) {
                                    field[x - 1][j] = tmp;
                                } else {
                                    field[x][j] = tmp;
                                }

                            }

                        }

                    }
                }
                break;
            case RIGHT:

                if(canMove(Direction.RIGHT)) {
                    for (int i = field.length - 2; i >= 0; i--) {
                        for (int j = 0; j < field.length; j++) {
                            int x = i;
                            if (field[x][j] != 0) {
                                while (x < field.length - 2 && field[x + 1][j] == 0) {
                                    x++;
                                }
                                int tmp = field[i][j];
                                field[i][j] = 0;

                                if (field[x + 1][j] == tmp) {
                                    field[x + 1][j] += 1;
                                    mergeCount++;
                                } else if (field[x + 1][j] == 0) {
                                    field[x + 1][j] = tmp;
                                } else {
                                    field[x][j] = tmp;
                                }

                            }

                        }

                    }
                }
                break;
        }
        addNumberToField();

        return canMove();
    }

    public void addNumberToField(){
        if (!isFieldFull()) {
            Random random = new Random();
            int tmp1, tmp2;
            do {
                tmp1 = random.nextInt(field.length);
                tmp2 = random.nextInt(field.length);
            } while (field[tmp1][tmp2] != 0);

            field[tmp1][tmp2] = random.nextInt(2)+1;
            /*
            for(int i = 0 ; i < field.length ; i++){
                for(int j = 0 ; j < field.length ; j++){
                    if(field[i][j] == 0){
                        Random random = new Random();
                        //if(random.nextBoolean()) {
                            field[i][j] = 1;
                        //}else{
                            field[j][i] = 1;
                        //}
                    }
                }
            }*/
        }
    }

    public int[][] getField() {
        return field;
    }

    public boolean isFieldFull(){
        for(int i = 0; i < field.length ; i++){
            for(int j = 0 ; j < field.length ; j++){
                if(field[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public boolean canMove(Direction dir){
        switch (dir) {
            case UP:
                for (int j = 1; j < field.length; j++) {
                    for (int i = 0; i < field.length; i++) {
                        if (field[i][j] == field[i][j - 1]) {
                            return true;
                        }
                    }
                }
                return false;

            case DOWN:

                for (int j = 0; j < field.length - 1; j++) {
                    for (int i = 0; i < field.length; i++) {
                        if (field[i][j] == field[i][j + 1]) {
                            return true;
                        }
                    }
                }
                return false;

            case LEFT:

                for (int i = 1; i < field.length; i++) {
                    for (int j = 0; j < field.length; j++) {
                        if (field[i][j] == field[i - 1][j]) {
                            return true;
                        }
                    }
                }
                return false;

            case RIGHT:

                for (int i = 0; i < field.length - 1; i++) {
                    for (int j = 0; j < field.length; j++) {
                        if (field[i][j] == field[i + 1][j]) {
                            return true;
                        }
                    }
                }
                return false;

        }
        return false;
    }

    public boolean canMove(){

        if(isFieldFull()){
            for(int j = 0; j < field.length ; j++){
                for(int i = j%2 ; i < field.length ; i+=2) {
                    if(checkIfSurroundingsHaveSameNumber(i,j))
                        return true;
                }
            }
            return false;
        }else {
            return true;
        }

    }

    public boolean checkIfSurroundingsHaveSameNumber(int x, int y) {
        if(x-1 >= 0) {
            if (field[x][y] == field[x - 1][y])
                return true;
        }
        if(y-1 >= 0) {
            if (field[x][y] == field[x][y - 1])
                return true;
        }
        if(x+1 < field.length) {
            if (field[x][y] == field[x + 1][y])
                return true;
        }

        if(y+1 < field.length) {
            if (field[x][y] == field[x][y + 1])
                return true;
        }
        return false;
    }
    public int size(){
        return field.length;
    }

    public int getHighestValue(){
        int max = 0;
        for(int i = 0 ; i < field.length ; i++){
            for(int j = 0 ; j < field.length ; j++){
                if(field[i][j] > max)
                    max = field[i][j];
            }
        }
        return max;
    }

    public int getMergeCount() {
        return mergeCount;
    }
}
