package com.protsyk.ga.hillclimbing.function;

import com.protsyk.ga.hillclimbing.model.Chromosome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Deb1Function extends AbstractFunction {

    public Deb1Function(int spaceSize) {
        this.spaceSize = spaceSize;
    }

    public Deb1Function(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = 0;
        for (int i = 0; i < values.length; i++) {
            res += Math.pow(Math.sin(5 * values[i] * Math.PI), 6);
        }
        return res / values.length;
    }

    @Override
    public double fitONE(double data) {
        return Math.pow(Math.sin(5 * data * Math.PI), 6);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double fitTwo(double x, double y) {
        return 0.5 * (Math.pow(Math.sin(5 * x * Math.PI), 6) + Math.pow(Math.sin(5 * y * Math.PI), 6));
    }

    @Override
    public Map<double[], Double> allMaximas() {
        Map<double[], Double> map = new HashMap<>();
        double[] arr = {0.1, 0.3, 0.5, 0.7, 0.9};
        // Calculate the number of arrays we should create
        int numArrays = (int) Math.pow(arr.length, spaceSize);
        // Create each array
        for (int i = 0; i < numArrays; i++) {
            double[] current = new double[spaceSize];
            // Calculate the correct item for each position in the array
            for (int j = 0; j < spaceSize; j++) {
                // This is the period with which this position changes, i.e.
                // a period of 5 means the value changes every 5th array
                int period = (int) Math.pow(arr.length, spaceSize - j - 1);
                // Get the correct item and set it
                int index = i / period % arr.length;
                current[j] = arr[index];
            }
            map.put(current, fit(current));
        }
        return map;
    }

    @Override
    public Map<double[], Double> globalMaxima() {
        return allMaximas();
    }

    @Override
    public int numberOfExtremas() {
        return (int) Math.pow(5, spaceSize);
    }

}
