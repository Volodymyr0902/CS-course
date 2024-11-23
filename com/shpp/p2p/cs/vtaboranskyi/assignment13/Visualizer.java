package com.shpp.p2p.cs.vtaboranskyi.assignment13;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

public class Visualizer extends WindowProgram {
    public void run() {
        GImage img = new GImage(ImageManager.prepareImage(new String[]{"img.png"}));
        add(img);
    }
}
