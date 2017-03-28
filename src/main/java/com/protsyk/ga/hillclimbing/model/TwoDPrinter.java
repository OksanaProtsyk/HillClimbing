package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;
import  org.jdom2.*;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/28/17
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class TwoDPrinter {
    public static void printPopulation(FitnessFunction f, Chromosome[] population,JFrame frame){
        Plot2DPanel plot = new Plot2DPanel();
        double[] x = increment(f.a(), 0.01, f.b());
        double[] y = new double[x.length];
        for(int i =0; i<y.length;++i) {
            y[i] = f.fitONE(x[i]);
        }

        // add a line plot to the PlotPanel
        plot.addLinePlot("Deb2",x,y);
        for(int i=0;i<population.length;i++) {

            double[] fun = new double[population[i].decode().length];
            fun[0]= f.fit(population[i].decode());
            plot.addScatterPlot("points", Color.RED,population[i].decode(),fun);
            System.out.println(population[i].decode()[0]+":" +fun[0]);
        }

        // put the PlotPanel in a JFrame, as a JPanel
        frame.setContentPane(plot);
        frame.setVisible(true);
        frame.setSize(1000,700);
    }

    public static double[] increment(double start, double step, double end) {
        double range = end - start;
        int steps = (int)(range / step);
        double[] rv = new double[steps];
        for (int i = 0; i<steps; i++) {
            rv[i] = start + ((step / range) * i);
        }
        return rv;
    }
}
