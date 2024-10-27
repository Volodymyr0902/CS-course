package com.shpp.cs.a.lectures.lec06;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

public class JustMovingBall extends WindowProgram {
    @Override
    public void run() {
        GOval ball = new GOval(0, 0, 100, 100);
        add(ball);
        while (true) {
            ball.move(1, 1);
            pause(15);
        }
    }
}
