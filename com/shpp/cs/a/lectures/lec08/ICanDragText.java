package com.shpp.cs.a.lectures.lec08;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ICanDragText extends WindowProgram {

    @Override
    public void run() {
        createWords();
        addMouseListeners();
    }

    private GObject wordLabel;
    private double lastX, lastY;

    @Override
    public void mousePressed(MouseEvent press) {
        wordLabel = getElementAt(press.getX(), press.getY());
        lastX = press.getX();
        lastY = press.getY();
    }

    @Override
    public void mouseDragged(MouseEvent drag) {
        if (wordLabel != null) {
            double dx = drag.getX() - lastX;
            double dy = drag.getY() - lastY;

            wordLabel.move(dx, dy);

            lastX = drag.getX();
            lastY = drag.getY();
        }
    }

    public void mouseClicked(MouseEvent click) {
        wordLabel = getElementAt(click.getX(), click.getY());
        if (wordLabel != null) {
            wordLabel.setColor(RandomGenerator.getInstance().nextColor());
        }
    }

    private void createWords() {

        String fileLink = "Goods.txt";

        try {
            BufferedReader myBro = new BufferedReader(new FileReader(fileLink));
            String word;
            String stop = "stop";
            while ((word = myBro.readLine()) != null) {
                if (word.contains(stop)) break;
                addWords(word);
                addMouseListeners();
            }
            myBro.close();
        } catch (IOException e) {
            println("Failure");
        }

    }

    public void addWords(String word) {
        RandomGenerator newGen = RandomGenerator.getInstance();

        GLabel wordToShow = new GLabel(word);
        wordToShow.setFont("serif-" + newGen.nextInt(40, 60));
        wordToShow.setColor(newGen.nextColor());

        double x = newGen.nextDouble(0, getWidth() - wordToShow.getWidth());
        double y = newGen.nextDouble(wordToShow.getAscent(), getHeight() - wordToShow.getDescent());

        wordToShow.setLocation(x, y);
        add(wordToShow);
    }

}
