package com.protsyk.ga;

import org.math.plot.FrameView;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/30/17
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        Plot2DPanel p2 = new Plot2DPanel();
        FrameView frameView = new FrameView(p2);
        for (int k = 0; k < 20; k++) {
            p2.removeAll();
            for (int i = 0; i < 3; i++) {
                double[][] XYZ = new double[10][2];
                for (int j = 0; j < XYZ.length; j++) {
                    XYZ[j][0] = /*1 + */ Math.random();
                    XYZ[j][1] = /*100 * */ Math.random();
                }
                p2.addScatterPlot("toto" + i, XYZ);
            }

            p2.setLegendOrientation(PlotPanel.SOUTH);
            frameView.setContentPane(p2);
        }
    }

}
