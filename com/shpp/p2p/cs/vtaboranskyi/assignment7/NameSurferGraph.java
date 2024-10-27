package com.shpp.p2p.cs.vtaboranskyi.assignment7;

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    // List of entries to be shown in graphs on the canvas.
    private ArrayList<NameSurferEntry> entriesToShow;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        this.entriesToShow = new ArrayList<>();
    }


    /**
     * Clears the list of name surfer
     * entries stored inside this class.
     */
    public void clear() {
        this.entriesToShow.clear();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries
     * on the display if queried name is in database.
     * Checks if actual name wasn't added before
     * to avoid duplications; shows logs.
     *
     * @param entry Saves info about single name.
     */
    public void addEntry(NameSurferEntry entry) {
        if (entry != null) {
            // The flag for duplications.
            boolean isNew = true;

            for (NameSurferEntry entryToShow : entriesToShow) {
                if (entry.getName().equals(entryToShow.getName())) {
                    isNew = false;
                    break;
                }
            }

            if (isNew) {
                entriesToShow.add(entry);
            } else {
                System.out.println("This name has been already added.");
            }
        } else {
            System.out.println("This name is not found in database.");
        }
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Application calls update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawNet();
        drawCompleteDiagram();
    }

    /**
     * Draws ranks diagram for all the queried names.
     * Shows all the entries string representation
     * in the console.
     * Picks different color for every entry.
     */
    private void drawCompleteDiagram() {
        // The flag used for indicating restart colors assignment.
        int colorIterator = 0;
        System.out.println(entriesToShow.isEmpty() ? "No names were queried." : "Queried names ranks: ");

        for (NameSurferEntry entryToShow : entriesToShow) {
            drawSingleDiagram(entryToShow, COLORS[colorIterator]);
            System.out.println(entryToShow);

            // Flag is increased with every entry.
            colorIterator++;
            if (colorIterator == COLORS.length) {
                colorIterator = 0;
            }
        }
        System.out.println();
    }

    /**
     * Draws a diagram for an entry, shows names
     * and ranks within labels at edge points.
     *
     * @param entry Saves info about single name.
     * @param actualColor Color for all actual entry elements.
     */
    private void drawSingleDiagram(NameSurferEntry entry, Color actualColor) {
        // Initial and final points of graphs lines.
        double startX, startY, endX, endY;

        // The height of the measuring scale wi diapason of [0-1000], in px.
        double pxScaleHeight = getHeight() - GRAPH_MARGIN_SIZE * 2;

        // The name is converted so that only the first letter is in uppercase.
        String name = entry.getName().toLowerCase();
        name = name.replaceFirst(String.valueOf(name.charAt(0)), String.valueOf(name.charAt(0)).toUpperCase());

        for (int i = 0; i < NDECADES - 1; i++) {
            // Every X point is at the edge between decades or at whole graph's edge.
            startX = getWidth() * ((double) i / NDECADES);

            // Every Y point is proportional value on the same scale.
            startY = GRAPH_MARGIN_SIZE + pxScaleHeight * ((double) entry.getRank(i) / MAX_RANK);

            // In case such name wasn't top 1000 that decade we move it to the end of the scale.
            if (startY == GRAPH_MARGIN_SIZE) {
                startY += pxScaleHeight;
            }

            endX = getWidth() * ((double) (i + 1) / NDECADES);
            endY = GRAPH_MARGIN_SIZE + pxScaleHeight * ((double) entry.getRank(i + 1) / MAX_RANK);
            if (endY == GRAPH_MARGIN_SIZE) {
                endY += pxScaleHeight;
            }

            // The line connecting two rank values between decades edges.
            GLine line = new GLine(startX, startY, endX, endY);
            line.setColor(actualColor);
            this.add(line);

            // Draws all the labels except the last one.
            drawLabel(name, startX, startY, entry, i, actualColor);

            // Draws the last label.
            if (i == NDECADES - 2) {
                drawLabel(name, endX, endY, entry, ++i, actualColor);
            }
        }
    }

    /**
     * Draws the label indicating certain
     * connection to a particular graph
     * and rank value in certain decade.
     *
     * @param name A queried name.
     * @param x Line's final point on X axis.
     * @param y Line's final point on Y axis.
     * @param entry Saves info about single name.
     * @param decade Decade's sequential number.
     * @param actualColor Color set for actual entry.
     */
    private void drawLabel(String name, double x, double y, NameSurferEntry entry, int decade, Color actualColor) {
        GLabel rankMark = new GLabel(name + " " + (entry.getRank(decade) == 0 ? "*" : entry.getRank(decade)));
        rankMark.setFont(RANK_FONT);
        rankMark.setLocation(x, y - rankMark.getDescent());
        rankMark.setColor(actualColor);
        this.add(rankMark);
    }

    /**
     * Draws background net to visualize and
     * distinguish decades and scale's borders.
     * Adds labels to the decades.
     */
    private void drawNet() {
        drawVerticalLines();
        drawHorizontalLines();
        showDecades();
    }

    /**
     * Marks every decade on the background net
     * with a relevant edge-year.
     */
    private void showDecades() {
        for (int i = 0; i < NDECADES; i++) {
            GLabel year = new GLabel(String.valueOf(START_DECADE + i * DECADES_GAP));
            year.setFont(DECADE_FONT);
            year.setLocation(getWidth() * ((double) i / NDECADES), getHeight() - year.getDescent());
            add(year);
        }
    }

    /**
     * Draws horizontal lines to set borders
     * to the measuring scale, where upper one
     * stands for top popularity and the bottom
     * one means the name is out of top 1000.
     */
    private void drawHorizontalLines() {
        GLine topEdge = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        GLine bottomEdge = new GLine(0,
                getHeight() - GRAPH_MARGIN_SIZE,
                getWidth(),
                getHeight() - GRAPH_MARGIN_SIZE);
        add(topEdge);
        add(bottomEdge);
    }

    /**
     * Draws vertical lines to set
     * borders between the decades.
     */
    private void drawVerticalLines() {
        for (int i = 0; i < NDECADES; i++) {
            GLine vertical = new GLine(getWidth() * ((double) i / NDECADES),
                    0,
                    getWidth() * ((double) i / NDECADES),
                    getHeight());
            add(vertical);
        }
    }


    /**
     * Implementation of the ComponentListener interface.
     * Redraws the canvas with the same data whenever
     * the window's size is changed.
     */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
