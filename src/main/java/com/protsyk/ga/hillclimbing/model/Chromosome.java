package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.hillclimbing.function.FitnessFunction;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Chromosome extends Comparable<Chromosome> {

    public double fitness();

    /* random chromosome */
    //public Chromosome newInstance();

    public double[] decode();

    public void replace(int nuber, double value);
    public FitnessFunction getFunction();

    public void setFunction(FitnessFunction function);
    public void setValues(double[] values);

}
