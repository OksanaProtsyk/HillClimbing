package com.protsyk.ga.hillclimbing.genotype;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.statistics.SingleRunStatistics;
import com.protsyk.ga.hillclimbing.utils.Utils;
import com.protsyk.ga.hillclimbing.utils.visualisers.ThreeDFisualizer;
import com.protsyk.ga.hillclimbing.utils.visualisers.TwoDFisualizer;

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
        int NFE = 0;
        int dimention = population[0].decode().length;

        TwoDFisualizer visual = new TwoDFisualizer(population[0].getFunction());
        ThreeDFisualizer visual3d = new ThreeDFisualizer(population[0].getFunction());

        boolean chdet = true;
      while (chdet&&(NFE <= population[0].getFunction().maxNFE())) {
          chdet =false;
            if (dimention == 1) {
                visual.printPopulation(population);
            }
            if (dimention == 2) {
                //visual3d.printPopulation(population);
            }

            for (int i = 0; i < population.length; i++) {
               int start = Calc.randomInt(0, population[i].getFunction().spaceSize());
                boolean change = true;
                while (change) {
                    change = false;
                    for (int j = 0; j < population[i].getFunction().spaceSize(); j++) {
                        int begin = j * population[i].bits.length / population[i].getFunction().spaceSize();
                        int end = begin + population[i].bits.length / population[i].getFunction().spaceSize();
                        double currFunc = population[i].fitness();


                        int[] prev = new int[population[i].bits.length];


                        for (int k = 0; k < population[i].bits.length; k++) {
                            prev[k] = population[i].bits[k];

                        }
                        population[i].bits = Utils.addOneBit(population[i].bits, begin, end);
                        if (currFunc >population[i].fitness()) {
                            if (NFE > population[0].getFunction().maxNFE()) {
                                break;
                            }
                            NFE++;
                            population[i].bits = Utils.removeOneBit(population[i].bits, begin, end);

                            population[i].bits = Utils.removeOneBit(population[i].bits, begin, end);
                            if (currFunc > population[i].fitness()) {
                                if (NFE > population[0].getFunction().maxNFE()) {
                                    break;
                                }
                                NFE++;
                                population[i].bits = Utils.addOneBit(population[i].bits, start, end);

                            } else {
                                change = true;
                                chdet = true;

                            }
                        } else {
                            change = true;
                            chdet = true;

                        }
                    }
                    //System.out.println("Cha:="+change);
                }
            }
            //initialNeighbourhood=initialNeighbourhood/2;
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
