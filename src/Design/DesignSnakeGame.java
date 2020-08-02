package Design;

/*  353. Design Snake Game
    Design a Snake game that is played on a device with screen size = width x height.
    The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
    You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
    Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
    When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

    Example:
    Given width = 3, height = 2, and food = [[1,2],[0,1]].

    Snake snake = new Snake(width, height, food);

    Initially the snake appears at position (0,0) and the food at (1,2).

    |S| | |
    | | |F|

    snake.move("R"); -> Returns 0

    | |S| |
    | | |F|

    snake.move("D"); -> Returns 0

    | | | |
    | |S|F|

    snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

    | |F| |
    | |S|S|

    snake.move("U"); -> Returns 1

    | |F|S|
    | | |S|

    snake.move("L"); -> Returns 2 (Snake eats the second food)

    | |S|S|
    | | |S|

    snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */

import java.util.LinkedList;
import java.util.Queue;

public class DesignSnakeGame {

    Queue<Integer> queue;
    boolean[][] snake;
    int[][] food;
    int foodIdx;
    int height, width;
    int row, col;
    int score;

    /** Initialize your structure here.  */
    public DesignSnakeGame(int width, int height, int[][] food) {
        this.snake = new boolean[height][width];
        this.food = food;
        this.foodIdx = 0;
        // row * width + col
        this.queue = new LinkedList<>();
        this.queue.offer(0);
        this.snake[0][0] = true;

        this.width = width;
        this.height = height;

        this.row = 0;
        this.col = 0;

        this.score = 0;
    }

    public int move(String direction) {
        if (score == -1) return score;

        switch (direction) {
            case "U":
                row--;
                break;
            case "L":
                col--;
                break;
            case "R":
                col++;
                break;
            case "D":
                row++;
                break;
        }

        // cross boundary
        if (row < 0 || row >= height || col < 0 || col >= width) {
            score = -1;
            return score;
        }

        // not food
        if (foodIdx == food.length || !(row == food[foodIdx][0] && col == food[foodIdx][1])) {
            // 因为 Queue 是先进先出的，在贪吃蛇里表现为前进一格，在 Queue 里表现为 poll 出蛇尾
            int idx = queue.poll();
            snake[idx / width][idx % width] = false;
        } else {
            // eats food
            score++;
            foodIdx++;
        }

        // bite itself
        if (snake[row][col]) {
            score = -1;
            return score;
        } else {
            // 贪吃蛇前进一个，在 Queue 里表现为 offer 进蛇头
            queue.offer(row * width + col);
            snake[row][col] = true;
        }
        return score;
    }
}
