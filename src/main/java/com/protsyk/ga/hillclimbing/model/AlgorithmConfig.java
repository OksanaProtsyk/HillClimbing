package com.protsyk.ga.hillclimbing.model;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 4/18/17
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlgorithmConfig {

    public int populationSize;
    public double neighbourhoodSize;
    public double epsSize;

    public AlgorithmConfig(int populationSize, double neighbourhoodSize, double epsSize, double changeSpeed) {
        this.populationSize = populationSize;
        this.neighbourhoodSize = neighbourhoodSize;
        this.epsSize = epsSize;
        this.changeSpeed = changeSpeed;
    }

    public double changeSpeed;


}
