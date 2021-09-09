package TetrisV1;

import java.awt.event.KeyEvent;

public class Tetris {
    private Field field;
    private Figure figure;

    private boolean isGameOver;

    public Tetris(int width, int height) {
        field = new Field(width, height);
        figure = null;
    }

    public void run() throws Exception{
        // create object keyboard observer and start him
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        isGameOver = false;
        // create first figure in mid up; x - mid of width, y - 0
        figure = FigureFactory.createRandomFigure(field.getWidth()/2, 0);

        while(!isGameOver) {
            if(keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                if (event.getKeyChar() == 'q') return;
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    figure.left();
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    figure.right();
                else if (event.getKeyCode() == 12)
                    figure.rotate();
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    figure.downMaximum();
            }
            step();
            field.print();
            Thread.sleep(300);
        }

        System.out.println("Game Over");
    }

    public void step(){
        figure.down();
        if (!figure.isCurrentPositionAvailable()){
            figure.up();
            figure.landed();
            isGameOver = figure.getY() <= 1;
            field.removeFullLines();
            figure = FigureFactory.createRandomFigure(field.getWidth()/2, 0);
        }
    }

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public static Tetris game;

    public static void main(String[] args) throws Exception {
        game = new Tetris(10, 20);
        game.run();
    }
}
