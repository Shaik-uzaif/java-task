// SNAKEGAME //

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SnakeGame extends JFrame {

    private static final long serialVersionUID = 1L;
    private final int GRID_SIZE = 20;
    private final int TILE_SIZE = 20;
    private int snakeLength;
    private int[] snakeX, snakeY;
    private int appleX, appleY;
    private boolean inGame;
    private Timer timer;

    public SnakeGame() {
        setTitle("Snake Game");
        setSize(GRID_SIZE * TILE_SIZE, GRID_SIZE * TILE_SIZE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        snakeX = new int[GRID_SIZE * GRID_SIZE];
        snakeY = new int[GRID_SIZE * GRID_SIZE];

        addKeyListener(new MyKeyAdapter());
        setFocusable(true);

        startGame();
    }

    private void startGame() {
        snakeLength = 3;
        for (int i = 0; i < snakeLength; i++) {
            snakeX[i] = GRID_SIZE / 2 - i;
            snakeY[i] = GRID_SIZE / 2;
        }

        spawnApple();

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (inGame) {
                    move();
                    checkCollision();
                    checkApple();
                    repaint();
                }
            }
        });

        inGame = true;
        timer.start();
    }

    private void move() {
        for (int i = snakeLength; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        snakeX[0]++;
    }

    private void checkCollision() {
        if (snakeX[0] == 0 || snakeX[0] == GRID_SIZE - 1 || snakeY[0] == 0 || snakeY[0] == GRID_SIZE - 1) {
            inGame = false;
            gameOver();
        }

        for (int i = snakeLength; i > 0; i--) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                inGame = false;
                gameOver();
            }
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void checkApple() {
        if (snakeX[0] == appleX && snakeY[0] == appleY) {
            snakeLength++;
            spawnApple();
        }
    }

    private void spawnApple() {
        Random rand = new Random();
        appleX = rand.nextInt(GRID_SIZE - 2) + 1;
        appleY = rand.nextInt(GRID_SIZE - 2) + 1;
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (inGame) {
            g.setColor(Color.RED);
            g.fillRect(appleX * TILE_SIZE, appleY * TILE_SIZE, TILE_SIZE, TILE_SIZE);

            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(snakeX[i] * TILE_SIZE, snakeY[i] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    snakeX[0] = 0;
                    snakeY[0] = -1;
                    break;
                case KeyEvent.VK_DOWN:
                    snakeX[0] = 0;
                    snakeY[0] = 1;
                    break;
                case KeyEvent.VK_LEFT:
                    snakeX[0] = -1;
                    snakeY[0] = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    snakeX[0] = 1;
                    snakeY[0] = 0;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new SnakeGame().setVisible(true);
    }
}