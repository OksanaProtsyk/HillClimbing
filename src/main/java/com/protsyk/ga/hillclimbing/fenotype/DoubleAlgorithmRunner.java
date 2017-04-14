package com.protsyk.ga.hillclimbing.fenotype;

import com.protsyk.ga.hillclimbing.function.FitnessFunction;

import com.protsyk.ga.hillclimbing.statistics.AllRunStatistics;
import com.protsyk.ga.hillclimbing.statistics.SingleRunStatistics;
import com.protsyk.ga.hillclimbing.utils.DoublePopulationGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by okpr0814 on 4/7/2017.
 */
public class DoubleAlgorithmRunner {
    public static int NUMBER_OF_RUNS = 10;
    public FitnessFunction function;
    public DoubleChomosome[] population;
    public int populationSize;
    public double neighbourSize;
    public double eps;
    //DoubleHillClimbing hillClimbing;

    public DoubleAlgorithmRunner(FitnessFunction function, int populationSize, double neighbourSize, double eps) {
        this.function = function;
        this.populationSize = populationSize;
        this.neighbourSize = neighbourSize;
        this.eps = eps;
        DoublePopulationGenerator doublePopulationGenerator = new DoublePopulationGenerator();
        this.population = doublePopulationGenerator.generatePopulation(populationSize, function);
        //hillClimbing = new DoubleHillClimbing(this.population, neighbourSize, eps);

    }

    public AllRunStatistics run() {
        List<SingleRunStatistics> statisticsList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_RUNS; i++) {

            DoubleChomosome[] temp = new DoubleChomosome[population.length];
            for (int j = 0; j < population.length; j++) {
                double[] val = new double[population[j].getValues().length];
                for (int k = 0; k < val.length; k++) {
                    val[k] = population[j].values[k];
                }
                temp[j] = new DoubleChomosome(population[j].getFunction(), val);
            }
            DoubleHillClimbing hillClimbing = new DoubleHillClimbing(this.population, neighbourSize, eps);
            this.population = temp;
            //Utils.printArr(this.population);
            SingleRunStatistics st = hillClimbing.run();
            statisticsList.add(st);
           // System.out.println(st);
            // hillClimbing.population=this.population;
            // hillClimbing.initialNeighbourhood=this.neighbourSize;
        }
        AllRunStatistics statistics = new AllRunStatistics();
        statistics.listOFSinglerRuns = statisticsList;
        int successruns = 0;
        int peakSum = 0;
        double NFEsum = 0;
        double maxNFE = Double.NEGATIVE_INFINITY;
        double PRsum = 0;
        double maxPR = Double.NEGATIVE_INFINITY;
        double PAsum = 0;
        double maxPA = Double.NEGATIVE_INFINITY;

        double DAsum = 0;
        double maxDA = Double.NEGATIVE_INFINITY;


        for (SingleRunStatistics s : statisticsList) {
            if (s.peakRatio == 1) {
                ++successruns;
            }
            peakSum += s.numberOfPeaks;
            NFEsum += s.NFE;
            PRsum += s.peakRatio;
            PAsum += s.peakAccurancy;
            DAsum += s.distanceAccurancy;
            if (s.NFE > maxNFE) {
                maxNFE = s.NFE;
            }
            if (s.peakRatio > maxPR) {
                maxPR = s.peakRatio;
            }
            if (s.peakAccurancy > maxPA) {
                maxPA = s.peakAccurancy;
            }
            if (s.distanceAccurancy > maxDA) {
                maxDA = s.distanceAccurancy;
            }
        }
        statistics.percentOfAllPeakFound = successruns / statisticsList.size() * 100;
        statistics.avaragePeakFound = peakSum / statisticsList.size();
        statistics.avarageNFE = NFEsum / statisticsList.size();
        statistics.maxNFE = maxNFE;
        statistics.avaragePR = PRsum / statisticsList.size();
        statistics.maxPR = maxPR;
        statistics.avarageDA = DAsum / statisticsList.size();
        statistics.maxDA = maxDA;
        statistics.avaragePA = PAsum / statisticsList.size();
        statistics.maxPA = maxPA;


        return statistics;
    }


}
