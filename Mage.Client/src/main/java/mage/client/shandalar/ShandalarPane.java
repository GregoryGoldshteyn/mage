package mage.shandalar.game;

import mage.client.MagePane;
import mage.client.MageFrame;

//import java.awt.*;
import javax.swing.BorderFactory;

public class ShandalarPane extends MagePane {

    private mage.shandalar.game.ShandalarPanel shandalarPanel;
    
    public ShandalarPane(){
        initComponents();
        setTitle("Shandalar Pane");
    }

    public void initComponents(){
        shandalarPanel = new mage.shandalar.game.ShandalarPanel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shandalarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(shandalarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );

        MageFrame.getDesktop().add(shandalarPanel, javax.swing.JLayeredPane.MODAL_LAYER);
        /*
        jScrollPane1.setViewportView(shandalarPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(0, 400, Short.MAX_VALUE)
        );
        */
    }

    /*
    @Override
    public void deactivated() {
        shandalarPanel.deactivated();
    }

    @Override
    public void activated() {
        shandalarPanel.activated();
    }

    @Override
    public void handleEvent(AWTEvent event) {
        shandalarPanel.handleEvent(event);
    }
    */
}
