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
public class DoubleHillClimbing extends HillClimbing {
    public DoubleHillClimbing(DoubleChomosome[] population, double initialNeighbourhood, double eps) {
        super(population, initialNeighbourhood, eps);
    }

    public Chromosome[] run() {
        JFrame frame = new JFrame("sfk");
        boolean change = true;
        double currentFitness = 0;
        while (initialNeighbourhood >= eps) {
            for (int i = 0; i < population.length; i++) {
                //int random = Calc.randomInt(0, population[i].decode().length);
                change = true;
                while (change) {
                    change = false;
                    for (int j = 0; j < population[i].decode().length; j++) {
                        currentFitness = population[i].fitness();
                        double[] val = population[i].decode();
                        double[] valMinus = population[i].decode();

                        val[j] = val[j] + initialNeighbourhood;
                        System.out.println("++++++++++++"+valMinus[j]);
                        valMinus[j] = valMinus[j] - initialNeighbourhood;
                        System.out.println("------++++++++++++"+valMinus[j]);

                        DoubleChomosome addChromosome = new DoubleChomosome(population[i].getFunction(), val);
                        DoubleChomosome minusChromosome = new DoubleChomosome(population[i].getFunction(), valMinus);

                        if (addChromosome.fitness() > currentFitness) {
                            System.out.println("PREV: " );
                            Utils.printArr(population[i].decode());
                            population[i].setValues(val);
                            System.out.println("NEXT: ");
                            Utils.printArr(population[i].decode());

                            change = true;
                        } else if (minusChromosome.fitness() > currentFitness) {
                            System.out.println("PREV: " );
                            Utils.printArr(population[i].decode());
                            population[i].setValues(valMinus);
                            System.out.println("NEXT: ");
                            Utils.printArr(population[i].decode());
                            change = true;

                        }
                    }


                }



            }

            TwoDPrinter.printPopulation(population[0].getFunction(),population,frame);

            initialNeighbourhood = initialNeighbourhood/2;

        }
        return population;
    }
}
