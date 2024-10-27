package com.shpp.p2p.cs.vtaboranskyi.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;
import com.shpp.cs.a.lectures.lec10.StdAudio;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * File Breakout.java.
 * This program is arcade game with some bricks in the
 * upper window's part, a ball in the center and a paddle
 * in the bottom.
 * Once player clicks mouse the game starts and the ball falls
 * downwards with a random angle and the player has to bounce off
 * the ball by the paddle by moving it left/right.
 * The game is won if all the bricks are broken by the ball.
 * The player has 3 attempts to do it and every attempt finishes
 * when the ball falls on the floor or the game is won.
 * If all the attempts are used and there are some bricks left,
 * the game is lost.
 * GOOD LUCK!
 */
public class BreakoutExt extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 800;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_CORNER_CURVATURE = PADDLE_HEIGHT / 2;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Initial number of all the bricks
     */
    private static final int BRICKS_NUM = NBRICKS_PER_ROW * NBRICK_ROWS;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Number of separations in a row, including edges
     */
    private static final int BRICK_SEP_NUM = NBRICKS_PER_ROW + 1;

    /**
     * Sum width of all separations in a row, including edges
     */
    private static final int BRICK_SEPS_WIDTH = BRICK_SEP * BRICK_SEP_NUM;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 15;

    /**
     * The part cut off every brick's corner size
     */
    private static final int BRICK_CORNER_CURVATURE = 4;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Diameter of the ball in pixels
     */
    private static final int BALL_DIAMETER = BALL_RADIUS * 2;

    /**
     * Offset of the top brick row from the left
     */
    private static final int FIRST_BRICK_X_OFFSET = BRICK_SEP;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * The time period between every two ball's moves;
     * also between game report's blinks.
     */
    private static final int PAUSE_TIME = 1000 / 120;

    /**
     * The time period while the final report is blinking.
     */
    private static final int REPORT_BLINK_DURATION = 3000;

    /**
     * The colors of final victory and defeat report, respectively.
     */
    private static final Color VICTORY_COLOR = new Color(128, 0, 128);
    private static final Color DEFEAT_COLOR = Color.RED;

    /**
     * The labels shown in the final report for the victory or defeat
     * outcome, respectively.
     */
    private static final String VICTORY_REPORT = "YOU WIN!";
    private static final String DEFEAT_REPORT = "YOU LOSE!";

    private static final double ACCELERATION_INDEX = 0.025;

    /**
     * The paddle to be added in the bottom part, used for
     * bouncing the ball.
     */
    GRoundRect paddle;

    /**
     * The absolute paddle's position in the
     * window on Y axis.
     */
    private int actualPaddleYOffset;

    /**
     * The ball's velocity, in fact, the distance
     * it has to overcome in a single move on X and Y
     * axles, respectively.
     */
    private double vx, vy = 3;

    @Override
    public void run() {
        setBackground(Color.lightGray);
        createPaddle();
        createBricksMatrix();
        addMouseListeners();
        playGame();
    }

    /**
     * Creates matrix of bricks, based on
     * given constants values.
     */
    private void createBricksMatrix() {
        // Abstract reflection of the matrix, saved in a multidimensional array.
        GRoundRect[][] bricksMatrix = new GRoundRect[NBRICK_ROWS][NBRICKS_PER_ROW];

        for (int i = 0; i < NBRICK_ROWS; i++) {
            createBricksRow(bricksMatrix[i], i);
        }
    }

    /**
     * Creates a row of bricks, actually filling
     * an array as its abstract copy.
     * Creates brick by brick increasing every next
     * brick's offset on X axis.
     *
     * @param actualRow A row to be filled with bricks.
     * @param rowNum    This actual row's number, correspond to its index in the super array.
     */
    private void createBricksRow(GRoundRect[] actualRow, int rowNum) {
        // The total height of the rows already built.
        int matrixFilledHeight = (BRICK_HEIGHT + BRICK_SEP) * rowNum;
        // The offset of any brick in the actual row on Y axis.
        int brickYOffset = BRICK_Y_OFFSET + matrixFilledHeight;

        // The total bricks' row's width with no separations.
        double allBricksWidth = getWidth() - BRICK_SEPS_WIDTH;
        // The width of a single brick.
        double brickWidth = allBricksWidth / NBRICKS_PER_ROW;

        // The every brick's color in the actual row.
        Color actualColor = chooseBrickColor(rowNum);

        for (int j = 0; j < NBRICKS_PER_ROW; j++) {
            // The width of the already built row's part.
            double rowFilledWidth = (BRICK_SEP + brickWidth) * j;
            // The actual brick's offset on X axis.
            double brickXOffset = FIRST_BRICK_X_OFFSET + rowFilledWidth;

            // The actual brick.
            actualRow[j] = createBrick(brickXOffset, brickYOffset, brickWidth, actualColor);
        }
    }

    /**
     * Picks up color for a bricks' row, based on its
     * sequence number.
     *
     * @param rowNum The number of the actual brick's row.
     * @return The color all the brick's row will be paint in.
     */
    private Color chooseBrickColor(int rowNum) {
        if (rowNum < 2) {
            return Color.RED;
        } else if (rowNum < 4) {
            return Color.ORANGE;
        } else if (rowNum < 6) {
            return Color.YELLOW;
        } else if (rowNum < 8) {
            return Color.GREEN;
        }

        return Color.CYAN;
    }

    /**
     * Creates a bricks with specific parameters
     * and shows it in the window.
     *
     * @param x     The actual brick's offset on X axis.
     * @param y     The actual brick's offset on Y axis.
     * @param width The actual brick's width.
     * @param color The actual brick's color.
     * @return The brick as the smallest part of the multi array.
     */
    private GRoundRect createBrick(double x, double y, double width, Color color) {
        GRoundRect brick = new GRoundRect(x, y, width, BRICK_HEIGHT, BRICK_CORNER_CURVATURE);
        brick.setColor(color);
        brick.setFilled(true);
        add(brick);

        return brick;
    }

    /**
     * Creates a ball and shows it
     * in the window.
     *
     * @return The created ball.
     */
    private GOval createBall() {
        // The ball's offset on X and Y axles, respectively.
        double ballXOffset = getWidth() / 2.0 - BALL_RADIUS;
        double ballYOffset = getHeight() / 2.0 - BALL_RADIUS;

        GOval ball = new GOval(ballXOffset, ballYOffset, BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        add(ball);

        return ball;
    }

    /**
     * Starts the game, as soon as player clicks
     * the mouse, proceeds it until the attempts are
     * over or the game is won and shows the final report.
     */
    private void playGame() {
        // The broken bricks' number.
        int bricksBrokenCounter = 0;

        // The actual round number.
        GLabel roundNum = showRoundNum();

        // The game's score.
        GLabel score = showScore(bricksBrokenCounter);

        for (int i = 0; i < NTURNS && !isGameWon(bricksBrokenCounter); i++) {
            // New ball is created with every attempt.
            GOval ball = createBall();
            roundNum.setLabel("ROUND " + (i + 1));
            waitForClick();
            bricksBrokenCounter = playRound(ball, bricksBrokenCounter, score, roundNum);
        }

        getReport(bricksBrokenCounter);
    }

    /**
     * Creates and shows the actual round
     * number in the down right corner.
     *
     * @return The actual round number.
     */
    private GLabel showRoundNum() {
        // Saves additional space for a number
        GLabel roundNum = new GLabel("ROUND\t\t\t\t");
        int fontSize = getWidth() / 36;
        roundNum.setFont("Monospace-bold-" + fontSize);
        roundNum.setLocation(getWidth() - roundNum.getWidth(),
                getHeight() - roundNum.getDescent());
        add(roundNum);

        return roundNum;
    }

    /**
     * Creates and shows the game's score in the
     * down left corner indicating the broken bricks'
     * number out of the total number.
     *
     * @param bricksBrokenCounter The broken bricks' number.
     * @return The score label.
     */
    private GLabel showScore(int bricksBrokenCounter) {
        GLabel score = new GLabel("SCORE: " + bricksBrokenCounter + "/" + BRICKS_NUM);
        int fontSize = getWidth() / 36;
        score.setFont("Monospaced-bold-" + fontSize);
        score.setLocation(0, getHeight() - score.getDescent());
        add(score);

        return score;
    }

    /**
     * Picks up the report, based on the game's result
     * and shows it in the window.
     *
     * @param bricksBrokenCounter The broken bricks' number.
     */
    private void getReport(int bricksBrokenCounter) {
        if (isGameWon(bricksBrokenCounter)) {
            showReport(VICTORY_REPORT, VICTORY_COLOR);
        } else {
            showReport(DEFEAT_REPORT, DEFEAT_COLOR);
        }
    }

    /**
     * Starts a single attempt: resets ball's
     * horizontal velocity and moves it until
     * the attempt is lost, then removes the ball.
     * Updates the score all the time.
     *
     * @param ball                The ball just created for this very attempt.
     * @param bricksBrokenCounter The broken bricks' number.
     * @return The broken bricks' number.
     */
    private int playRound(GOval ball, int bricksBrokenCounter, GLabel score, GLabel roundNum) {
        setVX();

        while (!ballHitsBottom(ball) && !isGameWon(bricksBrokenCounter)) {
            ball.move(vx, vy);
            bricksBrokenCounter = updateScene(ball, bricksBrokenCounter, score, roundNum);
            score.setLabel("SCORE: " + bricksBrokenCounter + "/" + BRICKS_NUM);
            pause(PAUSE_TIME - bricksBrokenCounter * ACCELERATION_INDEX);
        }

        remove(ball);
        return bricksBrokenCounter;
    }

    /**
     * Randomly sets balls' velocity on X axis
     * in a defined diapason.
     */
    private void setVX() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);

        if (rgen.nextBoolean(0.5))
            vx = -vx;
    }

    /**
     * Updates the game scene, according to the
     * changed conditions (bricks are broken, ball
     * hits a brick or any window's part, except
     * the bottom). Ball-paddle interactions aren't
     * described here.
     *
     * @param ball                The actual attempt's ball.
     * @param bricksBrokenCounter The broken bricks' number.
     * @return The broken bricks' number.
     */
    private int updateScene(GOval ball, int bricksBrokenCounter, GLabel score, GLabel roundNum) {
        // The paddle or any brick.
        GObject collider = getCollidingObject(ball);

        if (ballHitsHorizontalEdge(ball)) { // left/right
            StdAudio.playInBackground("mixkit-light-impact-on-the-ground-2070.wav");
            vx = -vx;
        } else if ((collider == paddle && vy > 0) || ballHitsTop(ball)) { // paddle/top
            StdAudio.playInBackground("mixkit-light-impact-on-the-ground-2070.wav");
            vy = -vy;
        } else if (collider != paddle &&
                collider != null &&
                collider != score &&
                collider != roundNum) { // brick
            StdAudio.playInBackground("mixkit-arcade-game-jump-coin-216.wav");
            changeDirection(ball, collider);
            remove(collider);
            bricksBrokenCounter++;
        }

        return bricksBrokenCounter;
    }

    /**
     * Determines if all the bricks are broken,
     * thus game is won.
     *
     * @param bricksBrokenCounter The broken bricks' number.
     * @return The boolean value.
     */
    private boolean isGameWon(int bricksBrokenCounter) {
        return bricksBrokenCounter == BRICKS_NUM;
    }

    /**
     * Changes ball's direction when it hits
     * a brick as in real life: if the hit was
     * horizontal the ball is bounced off with
     * inverted horizontal velocity value, if
     * vertical - with inverted vertical
     * velocity value.
     *
     * @param ball     The ball of the actual attempt.
     * @param collider Any brick.
     */
    private void changeDirection(GOval ball, GObject collider) {
        if (isHitHorizontal(ball, collider)) {
            vx = -vx;
        } else { // The hit is vertical.
            vy = -vy;
        }
    }

    /**
     * Checks whether the actual brick was hit
     * from the left or the right or none of these.
     *
     * @param ball     The ball of the actual attempt.
     * @param collider Any brick.
     * @return True if the hit was horizontal and vice versa.
     */
    private boolean isHitHorizontal(GOval ball, GObject collider) {
        // The ball's actual pos is precisely left to the brick or some pxs more,
        // assuming it might have penetrated the brick for some pxs.
        boolean hitsLeft = ball.getX() + BALL_DIAMETER >= collider.getX() &&
                ball.getX() + BALL_DIAMETER < collider.getX() + vx;

        // The ball's actual pos is precisely right to the brick or some pxs less,
        // assuming it might have penetrated the brick for some pxs.
        boolean hitsRight = ball.getX() <= collider.getX() + collider.getWidth() &&
                ball.getX() > collider.getX() + collider.getWidth() + vx;

        return hitsLeft || hitsRight;
    }

    /**
     * Determines the object hit by the ball
     * (paddle or brick).
     *
     * @param ball The ball of the actual attempt.
     * @return The object hit if such exists.
     */
    private GObject getCollidingObject(GOval ball) {
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getX() + BALL_DIAMETER, ball.getY()) != null) {
            return getElementAt(ball.getX() + BALL_DIAMETER, ball.getY());
        } else if (getElementAt(ball.getX(), ball.getY() + BALL_DIAMETER) != null) {
            return getElementAt(ball.getX(), ball.getY() + BALL_DIAMETER);
        } else if (getElementAt(ball.getX() + BALL_DIAMETER, ball.getY() + BALL_DIAMETER) != null) {
            return getElementAt(ball.getX() + BALL_DIAMETER, ball.getY() + BALL_DIAMETER);
        }

        return null;
    }

    /**
     * Checks if the ball hits the upper
     * window's edge.
     *
     * @param ball The ball of the actual attempt.
     * @return True if the ball overcomes this border.
     */
    private boolean ballHitsTop(GOval ball) {
        return ball.getY() < 0;
    }

    /**
     * Checks if the ball hits the bottom
     * window's edge.
     *
     * @param ball The ball of the actual attempt.
     * @return True if the ball overcomes this border.
     */
    private boolean ballHitsBottom(GOval ball) {
        return ball.getY() + BALL_DIAMETER > getHeight();
    }

    /**
     * Checks if the ball hits either
     * left or the right window's edge.
     *
     * @param ball The ball of the actual attempt.
     * @return True if the ball overcomes any of these borders.
     */
    private boolean ballHitsHorizontalEdge(GOval ball) {
        // The ball overcomes the left window's edge.
        boolean ballHitsLeft = ball.getX() < 0;
        // The ball overcomes the right window's edge.
        boolean ballHitsRight = ball.getX() + BALL_DIAMETER > getWidth();

        return ballHitsLeft || ballHitsRight;
    }

    /**
     * Shows the report on the game in the
     * window's center, blinking it for some time
     * and then finally adds it.
     *
     * @param reportText The info about game's result.
     * @param color      The report's text's color based on the game's result
     */
    private void showReport(String reportText, Color color) {
        GLabel report = createReport(reportText, color);
        blinkReport(report);
        add(report);
    }

    /**
     * Makes the report appear and disappear very
     * quickly during some time.
     *
     * @param report The report to be blinked.
     */
    private void blinkReport(GLabel report) {
        // The time before blinking.
        long startTime = System.currentTimeMillis();
        // The time after one blink.
        long updTime = 0;

        while (updTime - startTime < REPORT_BLINK_DURATION) {
            add(report);
            pause(PAUSE_TIME);
            remove(report);
            pause(PAUSE_TIME);
            updTime = System.currentTimeMillis();
        }
    }

    /**
     * Creates the final game's report,
     * based on its result.
     *
     * @param reportText The report's text to be shown.
     * @param color      The report's text's color.
     * @return The created report.
     */
    private GLabel createReport(String reportText, Color color) {
        GLabel report = new GLabel(reportText);
        report.setColor(color);

        int fontSize = getWidth() / 12;
        report.setFont("Monospaced-bold-" + fontSize);

        // The report's initial coordinate on X and Y axles, respectively.
        double reportXOffset = getWidth() / 2.0 - report.getWidth() / 2.0;
        double reportYOffset = getHeight() / 2.0 + report.getDescent();
        report.setLocation(reportXOffset, reportYOffset);

        return report;
    }

    /**
     * Creates the paddle centered in the
     * window's bottom and add it to the
     * window.
     */
    private void createPaddle() {
        // The paddle's initial coordinate on X axis.
        int actualPaddleXOffset = getWidth() / 2 - PADDLE_WIDTH / 2;
        actualPaddleYOffset = getHeight() - PADDLE_Y_OFFSET;

        paddle = new GRoundRect(actualPaddleXOffset, actualPaddleYOffset, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_CORNER_CURVATURE);
        paddle.setColor(Color.BLACK);
        paddle.setFilled(true);
        add(paddle);
    }

    /**
     * Makes the paddle movable on X axis,
     * limited by the window's edges.
     *
     * @param e The single mouse's move.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // The new paddle's initial coordinate on X axis.
        int newX = e.getX() - PADDLE_WIDTH / 2;

        if (e.getX() > PADDLE_WIDTH / 2 && e.getX() < getWidth() - PADDLE_WIDTH / 2) {
            paddle.setLocation(newX, actualPaddleYOffset);
        }
    }

}