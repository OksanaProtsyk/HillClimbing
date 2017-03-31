package com.protsyk.ga.hillclimbing.model;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/30/17
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */

import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.utils.Utils;
import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;

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
public class ThreeDFisualizer {
    JFrame frame = new JFrame("Hill Climbing");
    Plot3DPanel plot = new Plot3DPanel();
    FitnessFunction f;
    int k = 0;
    List<Integer> list = new ArrayList<Integer>();

    public ThreeDFisualizer(FitnessFunction f) {
        this.f = f;

    }


    public void printPopulation(Chromosome[] population) {
        double[] x = increment(f.a(), 0.01, f.b());
        double[] y = increment(f.a(), 0.01, f.b());

        double[][] z = new double[y.length][x.length];
        for(int i =0; i<y.length; ++i)
            for(int j=0; j<x.length; ++j)
                z[j][i] =f.fitTwo(x[j],y[i]);

        //double[] z = new double[x.length * y.length];
//        for (int i = 0; i < x.length; ++i) {
//            for (int j = 0; j < y.length; ++j) {
//                z[i + i * j] = f.fitTwo(x[i], y[j]);
//            }
//        }j
        // System.out.println(f.b()+":::::::::::::::::::::::::::::::::::::::"+f.a());
        // Utils.printArr(x);
        plot = new Plot3DPanel();

        plot.addGridPlot("3-D Plot", x, y, z);

        // add a line plot to the PlotPanel
       // int k = plot.addLinePlot("asd", x, y, z);

        for (int i = 0; i < population.length; i++) {

            double[] fun = new double[population[i].decode().length];
            fun[0] = f.fit(population[i].decode());
            //list.add(plot.add
            //addScatterPlot("points",population[i].decode(),fun));
        }
        frame.setContentPane(plot);
        frame.setVisible(true);
        frame.setSize(1000, 700);


    }

    public double[] increment(double start, double step, double end) {
        double range = end - start;
        int steps = (int) (range / step);
        //  System.out.println("RANGE "+range+" STEPS "+steps);
        double[] rv = new double[steps];
        for (int i = 0; i < steps; i++) {
            rv[i] = start + ((step) * i);
        }
        return rv;
    }
}
