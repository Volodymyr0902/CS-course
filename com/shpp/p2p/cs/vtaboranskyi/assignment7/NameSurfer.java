package com.shpp.p2p.cs.vtaboranskyi.assignment7;

import com.shpp.cs.a.simple.SimpleProgram;
import java.awt.event.*;
import javax.swing.*;

/**
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */
public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    // The sign indicating input type.
    private final JLabel nameLabel = new JLabel("Name: ");

    // The field for names input.
    private JTextField nameInput = new JTextField(INPUT_COLS);

    // The button for adding new ranking graph to the canvas.
    private JButton graphButton = new JButton("Graph");

    // The button for clearing all added graphs.
    private JButton clearButton = new JButton("Clear");

    // Saves names and their ranks in a map.
    private NameSurferDataBase dataBase;

    // The canvas for the background net and ranking graphs.
    private NameSurferGraph graph;

    /**
     * Adds interacting elements to the window;
     * makes text field and buttons listen to events;
     * sets database and opens canvas.
     */
    public void init() {
        add(nameLabel, NORTH);
        add(nameInput, NORTH);

        // To show graph by pressing Enter.
        nameInput.setActionCommand("Enter");
        nameInput.addActionListener(this);

        add(graphButton, NORTH);
        add(clearButton, NORTH);
        addActionListeners();

        this.dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        this.graph = new NameSurferGraph();
        add(graph);
    }

    /**
     * Checks the event type and depending on
     * the result either adds new graph to the canvas
     * if the input is not empty field,
     * or removes all of them.
     * In both cases redraws the canvas to
     * show actual graph content;
     * shows logs in the console.
     *
     * @param e Enter was pressed while text field was active/
     *          any button was pressed.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(graphButton) || e.getActionCommand().equals("Enter")) {
            if (!nameInput.getText().isEmpty()) {
                System.out.println(graphButton.getText() + " : " + nameInput.getText());
                graph.addEntry(dataBase.findEntry(nameInput.getText()));
                graph.update();
            }
        } else if (e.getSource().equals(clearButton)) {
            System.out.println(clearButton.getText());
            graph.clear();
            graph.update();
        }
    }
}
