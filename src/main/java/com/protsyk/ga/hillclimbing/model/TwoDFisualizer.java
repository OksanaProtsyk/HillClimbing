package com.protsyk.ga.hillclimbing.model;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/30/17
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */

import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/28/17
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class TwoDFisualizer {
    JFrame frame = new JFrame("Hill Climbing");
    Plot2DPanel plot = new Plot2DPanel();
    FitnessFunction f;
    int k = 0;
    List<Integer> list = new ArrayList<Integer>();

    public TwoDFisualizer(FitnessFunction f) {
        this.f = f;

    }


    public  void printPopulation( Chromosome[] population){
        double[] x = increment(f.a(), 0.001,f.b());
        double[] y = new double[x.length];
        for(int i =0; i<y.length;++i) {
            y[i] = f.fitONE(x[i]);
        }
        plot = new Plot2DPanel();



        // add a line plot to the PlotPanel
       int k=  plot.addLinePlot("Deb2",x,y);


        for(int i=0;i<population.length;i++) {

            double[] fun = new double[population[i].decode().length];
            fun[0]= f.fit(population[i].decode());
            list.add(plot.addScatterPlot("points", Color.RED,population[i].decode(),fun));
        }
        frame.setContentPane(plot);
        frame.setVisible(true);
        frame.setSize(1000, 700);



    }

    public  double[] increment(double start, double step, double end) {
        double range = end - start;
        int steps = (int)(range / step);
        double[] rv = new double[steps];
        for (int i = 0; i<steps; i++) {
            rv[i] = start + ((step / range) * i);
        }
        return rv;
    }
}
