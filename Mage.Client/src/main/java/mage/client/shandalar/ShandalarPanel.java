package mage.shandalar.game;

import mage.client.MagePane;

import java.awt.*;
import javax.swing.*;

import za.co.swinggamelibrary.Animation;
import za.co.swinggamelibrary.AnimationCache;
import za.co.swinggamelibrary.DesignMetrics;
import za.co.swinggamelibrary.Director;
import za.co.swinggamelibrary.Graphics2DHelper;
import za.co.swinggamelibrary.KeyBinder;
import za.co.swinggamelibrary.Scene;
import za.co.swinggamelibrary.SpriteFrame;
import za.co.swinggamelibrary.SpriteFrameCache;

public class ShandalarPanel extends JPanel {
    
    public ShandalarPanel(){
        createAndShowGui();
        this.setSize(1200,900);
        this.setVisible(true);
    }

    private void createAndShowGui() {
        // Designmetrics should be initialised before creating an instance of the director
        Director director = new Director(new Dimension(800,600));
        // show FPS and objects rendered counter
        director.setRenderDebugInfo(true);
        // draw red rectangles around nodes for debugging purposes (helps check collisions etc)
        director.setDrawDebugMasks(true);

        // create the Scene which will hold the player sprites
        final Scene scene = new Scene();
        director.setScene(scene);

        JPanel buttonPanel = new JPanel();
        // create buttons to control game loop start pause/resume and stop
        final JButton startButton = new JButton("Start");
        final JButton pauseButton = new JButton("Pause");
        pauseButton.setEnabled(false);
        final JButton stopButton = new JButton("Stop");
        stopButton.setEnabled(false);

        // add buttons to panel
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        // add game panel and button panel to panel

        this.add(director, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

}
