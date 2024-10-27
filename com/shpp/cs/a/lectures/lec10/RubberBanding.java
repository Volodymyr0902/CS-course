package com.shpp.cs.a.lectures.lec10;

import acm.graphics.GLine;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.event.MouseEvent;

public class RubberBanding extends WindowProgram {

    private GLine line;

    @Override
    public void run() {
        addMouseListeners();
    }

    @Override
    public void mousePressed(MouseEvent press) {
        line = new GLine(press.getX(), press.getY(), press.getX(), press.getY());
        add(line);
    }

    @Override
    public void mouseDragged(MouseEvent drag) {
        line.setEndPoint(drag.getX(), drag.getY());
    }

    @Override
    public void mouseClicked(MouseEvent click) {
        removeAll();
    }
}
