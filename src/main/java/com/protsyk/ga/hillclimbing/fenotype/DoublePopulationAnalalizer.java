package com.protsyk.ga.hillclimbing.fenotype;

import com.protsyk.ga.hillclimbing.fenotype.DoubleChomosome;
import com.protsyk.ga.hillclimbing.model.Optima;
import com.protsyk.ga.hillclimbing.statistics.SingleRunStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 4/5/17
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoublePopulationAnalalizer {
    public final static double DELTA = 0.1;
    public final static double OMEGA = 1;

    public SingleRunStatistics analyzeFinalPopulation(DoubleChomosome[] population) {
        SingleRunStatistics singleRunStatistics = new SingleRunStatistics();
        //for function we now allexpremas. if not values is empty
        singleRunStatistics.numberOfPeaks = getSeeds(population, population[0].getFunction().OMEGA()).size();
        singleRunStatistics.numberolGlobalPeaks = getGlobalSeeds(population, population[0].getFunction().OMEGA()).size();
        singleRunStatistics.numberOfPeaksToFound = population[0].getFunction().numberOfExtremas();
        singleRunStatistics.numberolGlobalPeaksToFound = population[0].getFunction().numberOfGlobalMaxima();
        singleRunStatistics.peakRatio = singleRunStatistics.numberOfPeaks / singleRunStatistics.numberOfPeaksToFound;
        singleRunStatistics.globalPeakRatio = singleRunStatistics.numberolGlobalPeaks / singleRunStatistics.numberolGlobalPeaksToFound;
        singleRunStatistics.distanceAccurancy = distanceAccuracy(population);
        singleRunStatistics.globalDistanceAccurancy = globalDistanceAccuracy(population);

        singleRunStatistics.peakAccurancy = peakAccurancy(population);
        singleRunStatistics.globalPeakAccurancy = globalPeakAccurancy(population);


        singleRunStatistics.optimas = listOfOptimasforKnownValues(population, DELTA, population[0].getFunction().OMEGA());
        singleRunStatistics.foundseeds = getSeeds(population, population[0].getFunction().OMEGA());
        singleRunStatistics.globalSeeds = getGlobalSeeds(population,population[0].getFunction().OMEGA());
        return singleRunStatistics;

    }

    public int numberOfPeak(DoubleChomosome[] population, double delta, double omega) {
        int numOfPeaks = 0;
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        for (double[] data : extremas.keySet()) {
            boolean max = false;
            for (int i = 0; i < population.length; i++) {
                if ((Math.abs(extremas.get(data) - population[i].fitness())) < delta) {
                    boolean valuesAlso = true;
                    for (int j = 0; j < data.length; j++) {
                        if (Math.abs(data[j] - population[i].values[j]) > omega) {
                            valuesAlso = false;
                        }
                    }
                    if (valuesAlso) {
                        max = true;
                    }
                }

            }
            if (max) {
                numOfPeaks++;
            }
        }

        return numOfPeaks;
    }

    public List<Optima> listOfOptimasforKnownValues(DoubleChomosome[] population, double delta, double omega) {
        if (population[0].getFunction().allMaximas()==null){
            return null;
        }
        List<Optima> optimasFound = new ArrayList<Optima>();
        double fitness = 0;
        double[] values = null;
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        for (double[] data : extremas.keySet()) {
            boolean max = false;
            for (int i = 0; i < population.length; i++) {
                if ((Math.abs(extremas.get(data) - population[i].fitness())) < delta) {
                    boolean valuesAlso = true;
                    fitness = population[i].fitness();
                    values = population[i].values;
                    for (int j = 0; j < data.length; j++) {
                        if (Math.abs(data[j] - population[i].values[j]) > omega) {
                            valuesAlso = false;
                        }
                    }
                    if (valuesAlso) {
                        max = true;
                        break;
                    }
                }

            }
            if (max) {
                optimasFound.add(new Optima(values, fitness, extremas.get(data), data));
            }
        }

        return optimasFound;
    }

    public Map<double[], Double> getSeeds(DoubleChomosome[] population, double omega) {
        Map<double[], Double> seeds = new HashMap<>();
        List<DoubleChomosome> notanalyzed = new ArrayList<>();
        for (DoubleChomosome ch : population) {
            notanalyzed.add(ch);
        }
        while (!notanalyzed.isEmpty()) {
            DoubleChomosome x = getBest(notanalyzed);
            notanalyzed.remove(x);
            boolean found = false;
            for (double[] val : seeds.keySet()) {
                if (euclide(val, x.values) <= omega) {
                    found = true;
                }
            }
            if (!found) {
                seeds.put(x.values, x.fitness());

            }
        }

        return seeds;
    }


    public Map<double[], Double> getGlobalSeeds(DoubleChomosome[] population, double omega) {
        Map<double[], Double> seeds = new HashMap<>();
        List<DoubleChomosome> notanalyzed = new ArrayList<>();
        for (DoubleChomosome ch : population) {
            notanalyzed.add(ch);
        }
            DoubleChomosome x = getBest(notanalyzed);
            notanalyzed.remove(x);
            seeds.put(x.values, x.fitness());
            for (DoubleChomosome ch:notanalyzed) {
                boolean found = false;
                for (double[] val : seeds.keySet()) {
                    if (euclide(val, ch.values) <= omega) {
                        found = true;
                    }
                }
                if (!found&&(Math.abs(ch.fitness()-x.fitness())<0.0001)) {
                    seeds.put(ch.values, ch.fitness());

                }
            }


        return seeds;
    }
    public DoubleChomosome getBest(List<DoubleChomosome> doubleChomosomes) {
        double maxFitness = Double.NEGATIVE_INFINITY;
        DoubleChomosome maxChr = null;
        for (DoubleChomosome c : doubleChomosomes) {
            if (c.fitness() > maxFitness) {
                maxFitness = c.fitness();
                maxChr = c;
            }
        }
        return maxChr;
    }

    public double peakAccurancy(DoubleChomosome[] population) {
        if (population[0].getFunction().allMaximas() == null) {
            return Double.NaN;
        }
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        double accurancy = 0;
        for (double[] d : extremas.keySet()) {
            accurancy += Math.abs(extremas.get(d) - findClosestChomosome(d, population).fitness());
        }
        return accurancy;
    }

    public double globalPeakAccurancy(DoubleChomosome[] population) {
        if (population[0].getFunction().globalMaxima() == null) {
            return Double.NaN;
        }
        Map<double[], Double> extremas = population[0].getFunction().globalMaxima();
        double accurancy = 0;
        for (double[] d : extremas.keySet()) {
            accurancy += Math.abs(extremas.get(d) - findClosestChomosome(d, population).fitness());
        }
        return accurancy;
    }

    public double distanceAccuracy(DoubleChomosome[] population) {
        if (population[0].getFunction().allMaximas() == null) {
            return Double.NaN;
        }
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        double accurancy = 0;
        for (double[] d : extremas.keySet()) {
            accurancy += euclide(d, findClosestChomosome(d, population).values);
        }
        return accurancy;
    }

    public double globalDistanceAccuracy(DoubleChomosome[] population) {
        if (population[0].getFunction().globalMaxima() == null) {
            return Double.NaN;
        }
        Map<double[], Double> extremas = population[0].getFunction().globalMaxima();
        double accurancy = 0;
        for (double[] d : extremas.keySet()) {
            accurancy += euclide(d, findClosestChomosome(d, population).values);
        }
        return accurancy;
    }

    DoubleChomosome findClosestChomosome(double[] extremaValues, DoubleChomosome[] population) {
        double distance = 10000;
        DoubleChomosome closest = population[0];
        for (DoubleChomosome ch : population) {
            double newDist = euclide(extremaValues, ch.values);
            if (newDist < distance) {
                distance = newDist;
                closest = ch;
            }
        }
        return closest;
    }

    double euclide(double[] a, double[] b) {
        double newDist = 0;
        for (int i = 0; i < a.length; i++) {
            newDist += (a[i] - b[i]) * (a[i] - b[i]);
        }
        return Math.sqrt(newDist);
    }

}
