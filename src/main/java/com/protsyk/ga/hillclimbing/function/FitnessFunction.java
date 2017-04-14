package com.protsyk.ga.hillclimbing.function;


import com.protsyk.ga.hillclimbing.model.Chromosome;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FitnessFunction {

    /**
     * Returns the non-negative fitness value of a chromosome. Large values
     * indicate better fitness.
     */
    public double fit(double[] data);

    public double[] decode(int[] chomosome);

    public int[] code(double x[]);

    public double a();

    public double b();

    public int after_comma();

    public int spaceSize();
    public double fitONE(double data);
    public double fitTwo(double x,double y);

    public int maxNFE();
    public  Map<double[],Double> allMaximas();

    public int numberOfExtremas();






}