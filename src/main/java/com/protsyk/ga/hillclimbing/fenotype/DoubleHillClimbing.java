package com.protsyk.ga.hillclimbing.fenotype;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.utils.visualisers.ThreeDFisualizer;
import com.protsyk.ga.hillclimbing.utils.visualisers.TwoDFisualizer;
import com.protsyk.ga.hillclimbing.statistics.SingleRunStatistics;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoubleHillClimbing {
    public double initialNeighbourhood;
    public DoubleChomosome[] population;
    double eps;
    double changeSpeed =2;

    public DoubleHillClimbing(DoubleChomosome[] population, double initialNeighbourhood, double eps, double changeSpeed) {
        this.population = population;

        this.initialNeighbourhood = initialNeighbourhood;
        this.eps = eps;
        this.changeSpeed = changeSpeed;
    }

    public SingleRunStatistics run() {

        int NFE = 0;
        int dimention = population[0].values.length;
        TwoDFisualizer visual = new TwoDFisualizer(population[0].getFunction());
        ThreeDFisualizer visual3d = new ThreeDFisualizer(population[0].getFunction());

        boolean change = true;
        while ((initialNeighbourhood >= eps) && (NFE <= population[0].getFunction().maxNFE())) {
            if (dimention == 1) {
                visual.printPopulation(population);
            }
            if (dimention == 2) {
               visual3d.printPopulation(population);
            }
            for (int i = 0; i < population.length; i++) {

                int random = Calc.randomInt(0, population[i].values.length);
                change = true;
                while (change) {
                    change = false;
                    for (int j = random; j < population[i].values.length; j++) {
                        double currentFitness = population[i].fitness();
                        if (NFE > population[0].getFunction().maxNFE()) {
                            break;
                        }
                        NFE++;
                        population[i].values[j] = population[i].values[j] + initialNeighbourhood;
                        if (population[i].values[j] > population[i].getFunction().b()) {
                            population[i].values[j] = population[i].values[j] - population[i].getFunction().b();
                        }
                        if (population[i].fitness() < currentFitness) {
                            if (NFE > population[0].getFunction().maxNFE()) {
                                break;
                            }
                            NFE++;

                            population[i].values[j] = population[i].values[j] - 2 * initialNeighbourhood;
                            if (population[i].values[j] < population[i].getFunction().a()) {
                                population[i].values[j] = population[i].values[j] + population[i].getFunction().b();
                            }
                            if (population[i].fitness() < currentFitness) {
                                if (NFE > population[0].getFunction().maxNFE()) {
                                    break;
                                }
                                NFE++;

                                population[i].values[j] = population[i].values[j] + initialNeighbourhood;
                                if (population[i].values[j] > population[i].getFunction().b()) {
                                    population[i].values[j] = population[i].values[j] - population[i].getFunction().b();
                                }

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

            initialNeighbourhood = initialNeighbourhood /changeSpeed;
            //System.out.println(initialNeighbourhood);


        }
        // System.out.println("Number of fitness function evaluations, NFE = "+NFE);
        DoublePopulationAnalalizer analalizer = new DoublePopulationAnalalizer();
        SingleRunStatistics statistics = analalizer.analyzeFinalPopulation(population);
        statistics.NFE = NFE;
        //System.out.println(statistics);
        return statistics;
    }
}
