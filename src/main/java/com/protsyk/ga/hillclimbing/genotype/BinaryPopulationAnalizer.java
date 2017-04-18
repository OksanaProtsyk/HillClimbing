package com.protsyk.ga.hillclimbing.genotype;

/**
 * Created by okpr0814 on 4/14/2017.
 */

import com.protsyk.ga.hillclimbing.model.Optima;
import com.protsyk.ga.hillclimbing.statistics.SingleRunStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class BinaryPopulationAnalizer {
    public final static double DELTA =0.1;
    public final static double OMEGA =0.1;
    public SingleRunStatistics analyzeFinalPopulation(BinaryChromosome[] population) {
        SingleRunStatistics singleRunStatistics = new SingleRunStatistics();
        singleRunStatistics.numberOfPeaks = numberOfPeak(population, DELTA, OMEGA);
        singleRunStatistics.numberOfPeaksToFound = population[0].getFunction().allMaximas().size();
        singleRunStatistics.peakRatio=singleRunStatistics.numberOfPeaks/singleRunStatistics.numberOfPeaksToFound;
        singleRunStatistics.distanceAccurancy = distanceAccuracy(population);
        singleRunStatistics.peakAccurancy = peakAccurancy(population);
        singleRunStatistics.optimas = listOfOptimas(population, DELTA, OMEGA);
        return singleRunStatistics;

    }

    public int numberOfPeak(BinaryChromosome[] population, double delta, double omega) {
        int numOfPeaks = 0;
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        for (double[] data : extremas.keySet()) {
            boolean max = false;
            for (int i = 0; i < population.length; i++) {
                if ((Math.abs(extremas.get(data) - population[i].fitness())) < delta) {
                    boolean valuesAlso = true;
                    for (int j = 0; j < data.length; j++) {
                        if (Math.abs(data[j] - population[i].decode()[j]) > omega) {
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


    public List<Optima> listOfOptimas(BinaryChromosome[] population, double delta, double omega) {
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
                    values = population[i].decode();
                    for (int j = 0; j < data.length; j++) {
                        if (Math.abs(data[j] - population[i].decode()[j]) > omega) {
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

    public double peakAccurancy(BinaryChromosome[] population){
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        double accurancy =0;
        for (double[] d:extremas.keySet()){
            accurancy+=Math.abs(extremas.get(d)-findClosestChomosome(d,population).fitness());
        }
        return accurancy;
    }

    public double distanceAccuracy(BinaryChromosome[] population){
        Map<double[], Double> extremas = population[0].getFunction().allMaximas();
        double accurancy =0;
        for (double[] d:extremas.keySet()){
            accurancy+=euclide(d,findClosestChomosome(d,population).decode());
        }
        return accurancy;
    }


    BinaryChromosome findClosestChomosome(double[] extremaValues,BinaryChromosome[] population){
        double distance =10000;
        BinaryChromosome closest = population[0];
        for (BinaryChromosome ch: population){
            double newDist = euclide(extremaValues,ch.decode());
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

    int heming(int[] a,int[] b){
        int newDist =0;
        for (int i=0;i<a.length;i++){
            if(a[i]!=b[i]){
                newDist++;
            }
        }
        return newDist;
    }

}

