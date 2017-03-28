package com.protsyk.ga.hillclimbing.model;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class HillClimbing {
    double initialNeighbourhood;
    double eps;
    Chromosome[] population;

    public HillClimbing(Chromosome[] population, double initialNeighbourhood, double eps) {
        this.population = population;
        this.initialNeighbourhood = initialNeighbourhood;
        this.eps = eps;
    }



}
