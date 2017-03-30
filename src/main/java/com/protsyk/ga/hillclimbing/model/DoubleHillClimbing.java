package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.utils.Utils;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoubleHillClimbing {
    double initialNeighbourhood;
    double eps;
    DoubleChomosome[] population;

    public DoubleHillClimbing(DoubleChomosome[] population, double initialNeighbourhood, double eps) {
        this.population = population;
        this.initialNeighbourhood = initialNeighbourhood;
        this.eps = eps;
    }

    public Chromosome[] run() {
        int dimention = population[0].values.length;
        TwoDFisualizer visual = new TwoDFisualizer(population[0].getFunction());


        boolean change = true;
        while (initialNeighbourhood >= eps) {
            for (int i = 0; i < population.length; i++) {
                int random = Calc.randomInt(0, population[i].values.length);
                change = true;
                while (change) {
                    change = false;
                    for (int j = random; j < population[i].values.length; j++) {
                        double currentFitness = population[i].fitness();
                        population[i].values[j] = population[i].values[j] + initialNeighbourhood;
                        if (population[i].fitness() < currentFitness) {
                            population[i].values[j] = population[i].values[j] - 2 * initialNeighbourhood;
                            if (population[i].fitness() < currentFitness) {
                                population[i].values[j] = population[i].values[j] + initialNeighbourhood;

                            } else {
                                change = true;
                            }
                        } else {
                            change = true;
                        }

                    }


                }


            }

            //TwoDPrinter.printPopulation(population[0].getFunction(),population,frame);
            if (dimention == 1) {
                visual.printPopulation(population);
            }
            initialNeighbourhood = initialNeighbourhood / 2;
            System.out.println(initialNeighbourhood);


        }
        return population;
    }
}
