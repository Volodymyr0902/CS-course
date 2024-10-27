package com.shpp.cs.a.lectures.lec10;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class MyAudio extends TextProgram {

    @Override
    public void run() {
        RandomGenerator rg = RandomGenerator.getInstance();

        //double[] sounds = new double[80000];
        double[] sounds = StdAudio.read("mixkit-trumpet-fanfare-2293.wav");
        for (int i = 0; i < sounds.length; i++) {
            //sounds[i] = rg.nextDouble(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            println(sounds[i]);
        }

        pause(1000);
        StdAudio.play(sounds);

        pause(1000);
        StdAudio.play(slowed(sounds));

        pause(1000);
        StdAudio.play(speed(sounds));
    }

    private double[] slowed(double[] orig) {
        double[] sl = new double[orig.length * 2];

        for (int i = 0; i < sl.length; i++) {
            sl[i] = orig[i / 2];
        }

        return sl;
    }

    private double[] speed(double[] orig) {
        double[] sp = new double[orig.length / 2];

        for (int i = 0; i < sp.length; i++) {
            sp[i] = orig[i * 2];
        }

        return sp;
    }

}
