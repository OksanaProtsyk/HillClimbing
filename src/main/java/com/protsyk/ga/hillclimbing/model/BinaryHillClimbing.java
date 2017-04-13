package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.statistics.SingleRunStatistics;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryHillClimbing {
    double initialNeighbourhood;
    double eps;
    BinaryChromosome[] population;

    public BinaryHillClimbing(BinaryChromosome[] population, double initialNeighbourhood, double eps) {
        this.population = population;
        this.initialNeighbourhood = initialNeighbourhood;
        this.eps = eps;
    }

    public SingleRunStatistics run() {
        JFrame frame = new JFrame("sfk");
        int NFE =0;
        int dimention = population[0].decode().length;

        TwoDFisualizer visual = new TwoDFisualizer(population[0].getFunction());
        ThreeDFisualizer visual3d = new ThreeDFisualizer(population[0].getFunction());

        while (initialNeighbourhood > eps&&(NFE<=population[0].getFunction().maxNFE())) {
            if (dimention == 1) {
                visual.printPopulation(population);
            }
            if (dimention == 2) {
                visual3d.printPopulation(population);
            }
            for (int i = 0; i < population.length; i++) {
                int start = Calc.randomInt(0, population[i].bits.length);
                boolean change = true;
                while (change) {
                    change = false;
                    for (int j = start; j < population[i].bits.length; j++) {
                        double currFunc =  population[i].fitness();

                        population[i].bits[j] = 1-population[i].bits[j];
                        if (currFunc>population[i].fitness())  {
                            if (NFE > population[0].getFunction().maxNFE()) {
                                break;
                            }
                            NFE++;
                            population[i].bits[j] = 1-population[i].bits[j];
                        }
                        else {
                            change = true;
                        }
                    }
                }
            }
            initialNeighbourhood=initialNeighbourhood/2;
           // visual.printPopulation( population);
        }
        // System.out.println("Number of fitness function evaluations, NFE = "+NFE);
        BinaryPopulationAnalizer analalizer = new BinaryPopulationAnalizer();
        SingleRunStatistics statistics = analalizer.analyzeFinalPopulation(population);
        statistics.NFE = NFE;
        //System.out.println(statistics);
        return statistics;
    }
}
