package org.srijan;

import org.srijan.algorithms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class MainPanel extends JPanel {
    private final int[] data;
    private final int size;
    private SortMachine sorter;
    private Timer timer = null;
    private final JLabel algorithmHintLabel;

    MainPanel() {
        int n = 64;
        data = new int[n];
        Random rand = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(480 - 10) + 10;
        }

        size = 640 / n;
        algorithmHintLabel = new JLabel();
        algorithmHintLabel.setBackground(Color.WHITE);
        algorithmHintLabel.setFont(new Font("Ubuntu Mono", Font.PLAIN | Font.BOLD, 18));
        add(algorithmHintLabel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                switch (e.getKeyChar()) {
                    case 'i':
                        runSorter(InsertionSort.class);
                        break;
                    case 'm':
                        runSorter(MergeSort.class);
                        break;
                    case 'q':
                        runSorter(QuickSort.class);
                        break;
                    case 's':
                        runSorter(SelectionSort.class);
                        break;
                    case 'b':
                        runSorter(BubbleSort.class);
                        break;
                    case 'h':
                        runSorter(HeapSort.class);
                        break;
                }
            }
        });

        setFocusable(true);
        runSorter(QuickSort.class);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int h = getHeight();
        for (int i = 0; i < sorter.size(); i++) {
            if (sorter.isTargetElement(i))
                g.setColor(Color.RED);
            else if (sorter.isComparedToElement(i))
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.BLACK);

            g.fillRect(i * size, h - sorter.getValue(i), size, sorter.getValue(i));
        }
    }

    private void runSorter(Class<? extends SortMachine> sortingMachine) {
        if (timer != null) timer.stop();

        try {
            sorter = sortingMachine.getDeclaredConstructor(int[].class)
                        .newInstance((Object) data);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.err.println("Error in runSorter: " + e);
            e.printStackTrace();
            return;
        }

        algorithmHintLabel.setText(sortingMachine.getName());

        timer = new Timer(12, actionEvent -> {
            sorter.next();
            repaint();

            if (sorter.isCompleted()) ((Timer) actionEvent.getSource()).stop();
        });

        timer.start();
    }
}
