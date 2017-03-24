package com.protsyk.ga.hillclimbing.model;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryHillClimbing extends  HillClimbing {
    public BinaryHillClimbing(Chromosome[] population, double initialNeighbourhood, double eps) {
        super(population, initialNeighbourhood, eps);
    }
}
