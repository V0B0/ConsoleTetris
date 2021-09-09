package TetrisV1;

import java.util.ArrayList;

public class Field {
    private int width;
    private int height;

    private int[][] matrix;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }

    public void print() {
        int [][] canvas = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                canvas[y][x] = matrix[y][x];
            }
        }

        int left = Tetris.game.getFigure().getX();
        int top = Tetris.game.getFigure().getY();
        int [][] brickMatrix = Tetris.game.getFigure().getMatrix();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (top+y >= height || left+x >= width) continue;
                if (brickMatrix[y][x] == 1)
                    canvas[top+y][left+x] = 2;
            }
        }

        System.out.println("------------------------------------------------------------------------------");

        for (int y = 0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                int i = canvas[y][x];
                switch (i){
                    case 0:
                        System.out.println(" . ");
                        break;
                    case 1:
                        System.out.println(" X ");
                        break;
                    case 2:
                        System.out.println(" X ");
                    default:
                        System.out.println("???");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
    }

    public void removeFullLines() {
        ArrayList<int[]> lines = new ArrayList<>();

        for (int i = 0; i < height; i++){
            int count = 0;
            for (int j = 0; j < width; j++) {
                count += matrix[i][j];
            }
            if (count != width){
                lines.add(matrix[i]);
            }
        }
        while (lines.size() < height){
            lines.add(0,new int[width]);
        }

        matrix = lines.toArray(new int [height][width]);
    }

    public Integer getValue(int x, int y){
        if (x>=0 && x<width && y>=0 && y<height){
            return matrix[y][x];
        }
        return null;
    }

    public void setValue(int x, int y, int value) {
        if (x>=0 && x<width && y>=0 && y<height){
            matrix[y][x] = value;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
