package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.Calc;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoubleHillClimbing extends HillClimbing{
    public DoubleHillClimbing(DoubleChomosome[] population, double initialNeighbourhood, double eps) {
        super(population, initialNeighbourhood, eps);
    }

    Chromosome[] run() {
        while(initialNeighbourhood>=eps){
            for (int i=0;i<population.length;i++){
                int random = Calc.randomInt(0,population[i].decode().length);
                boolean change =true;
                while (change){
                    change =false;
                    for(int j=random;j<population[i].decode().length;j++){
                    }
                }
            }
        }
        return population;
    }
}
