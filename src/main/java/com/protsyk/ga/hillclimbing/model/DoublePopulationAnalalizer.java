package com.protsyk.ga.hillclimbing.model;

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
    public final static double DELTA =0.1;
    public final static double OMEGA =0.1;
    public SingleRunStatistics analyzeFinalPopulation(DoubleChomosome[] population) {
        SingleRunStatistics singleRunStatistics = new SingleRunStatistics();
        singleRunStatistics.numberOfPeaks = numberOfPeak(population, DELTA, OMEGA);
        singleRunStatistics.numberOfPeaksToFound = population[0].getFunction().allMaximas().size();
        singleRunStatistics.peakRatio=singleRunStatistics.numberOfPeaks/singleRunStatistics.numberOfPeaksToFound;
        singleRunStatistics.distanceAccurancy = distanceAccuracy(population);
        singleRunStatistics.peakAccurancy = peakAccurancy(population);
        singleRunStatistics.optimas = listOfOptimas(population, DELTA, OMEGA);
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
            if (max){
                numOfPeaks++;
            }
        }

        return numOfPeaks;
    }


    public List<Optima> listOfOptimas(DoubleChomosome[] population, double delta, double omega) {
        List<Optima> optimasFound = new ArrayList<Optima>();
        double fitness =0;
        double[] values =null;
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        for (double[] data : extremas.keySet()) {
            boolean max = false;
            for (int i = 0; i < population.length; i++) {
                if ((Math.abs(extremas.get(data) - population[i].fitness())) < delta) {
                    boolean valuesAlso = true;
                    fitness =  population[i].fitness();
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
            if (max){
                optimasFound.add(new Optima(values,fitness,extremas.get(data),data));
            }
        }

        return optimasFound;
    }

    public double peakAccurancy(DoubleChomosome[] population){
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        double accurancy =0;
        for (double[] d:extremas.keySet()){
            accurancy+=Math.abs(extremas.get(d)-findClosestChomosome(d,population).fitness());
        }
        return accurancy;
    }

    public double distanceAccuracy(DoubleChomosome[] population){
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        double accurancy =0;
        for (double[] d:extremas.keySet()){
            accurancy+=euclide(d,findClosestChomosome(d,population).values);
        }
        return accurancy;
    }


    DoubleChomosome findClosestChomosome(double[] extremaValues,DoubleChomosome[] population){
        double distance =10000;
        DoubleChomosome closest = population[0];
        for (DoubleChomosome ch: population){
            double newDist = euclide(extremaValues,ch.values);
            if (newDist<distance){
                distance = newDist;
                closest=ch;
            }
        }
        return closest;
    }

    double euclide(double[] a,double[] b){
        double newDist =0;
        for (int i=0;i<a.length;i++){
            newDist+=(a[i]-b[i])*(a[i]-b[i]);
        }
        return Math.sqrt(newDist);
    }

}
