package com.protsyk.ga.hillclimbing.runs;

import com.protsyk.ga.hillclimbing.fenotype.DoubleAlgorithmRunner;
import com.protsyk.ga.hillclimbing.fenotype.DoubleChomosome;
import com.protsyk.ga.hillclimbing.fenotype.DoubleHillClimbing;
import com.protsyk.ga.hillclimbing.function.Deb1Function;
import com.protsyk.ga.hillclimbing.function.Deb2Function;
import com.protsyk.ga.hillclimbing.statistics.AllRunStatistics;
import com.protsyk.ga.hillclimbing.statistics.SingleRunStatistics;
import com.protsyk.ga.hillclimbing.utils.DoublePopulationGenerator;

import java.util.Random;

/**
 * Created by okpr0814 on 4/14/2017.
 */
public class SPORun {


    public static double oneFirstRule(double sigma, double a, double ps) {
        double newSigma = sigma;

        double one5 = 0.2;
        if (ps > one5) {
            newSigma *= a;
        } else if (ps < one5) {
            newSigma /= a;
        }

        return newSigma;
    }


    public static void main(String[] args) {
        int populationSize = 1000;
        double neighbourSize = 0.1;
        double eps = 0.0001;

        int populationSize1 = 1000;
        double changespeed = 2;
        double neighbourSize1 = 1;
        double eps1 = 0.001;
        double sigmaSPO = 0.3;

        double changespeed1 = 1;
        double success=0;

        double aSPO = 0.5;
        DoubleHillClimbing runner = null;
        SingleRunStatistics statistics = null;
        DoubleHillClimbing runnerNEw = null;
        SingleRunStatistics statisticsNew = null;
        DoublePopulationGenerator doublePopulationGenerator = new DoublePopulationGenerator();

        double initialPR=0.2;
        double sumPR =0;

        DoubleChomosome[] doublePopluation = doublePopulationGenerator.generatePopulation(1000, new Deb2Function(2));
        DoubleChomosome[] temp = new DoubleChomosome[doublePopluation.length];

        for (int j = 0; j < doublePopluation.length; j++) {
            double[] val = new double[doublePopluation[j].getValues().length];
            for (int k = 0; k < val.length; k++) {
                val[k] = doublePopluation[j].values[k];
            }
            temp[j] = new DoubleChomosome(doublePopluation[j].getFunction(), val);
        }

        runner = new  DoubleHillClimbing(temp,neighbourSize,eps,changespeed);
        statistics =runner.run();
        double numberOfRuns =50;

        for (int t = 0; t < numberOfRuns; t++) {
            Random random = new Random();


            //populationSize1 = (int) (populationSize + sigmaSPO * random.nextGaussian());
            neighbourSize1 = neighbourSize + sigmaSPO * random.nextGaussian();
            eps1 = eps + sigmaSPO * random.nextGaussian();
            changespeed1 = changespeed+sigmaSPO*random.nextGaussian();
            if (neighbourSize1<0){
                neighbourSize1 = 0.00001;
            }
            if ((eps1<0)){
                eps1 = 0.000001;
            }
          if (changespeed1<1){
               changespeed1 = changespeed;
          }

            DoubleChomosome[] temp2 = new DoubleChomosome[doublePopluation.length];
            for (int j = 0; j < doublePopluation.length; j++) {
                double[] val = new double[doublePopluation[j].getValues().length];
                for (int k = 0; k < val.length; k++) {
                    val[k] = doublePopluation[j].values[k];
                }
                temp2[j] = new DoubleChomosome(doublePopluation[j].getFunction(), val);
            }
            runnerNEw = new DoubleHillClimbing(temp2, neighbourSize1, eps1,changespeed1);

            statisticsNew = runnerNEw.run();

            if (statisticsNew.exactRatio>initialPR){
                success++;
            }

            sumPR+=statisticsNew.exactRatio;

            double avrPR=sumPR/numberOfRuns;

            if (statisticsNew.exactRatio>initialPR){
                initialPR=avrPR;
                System.out.println("N___1 = "+populationSize1+", neighbour1 = "+neighbourSize1+", eps = "+eps1+",speed = "+changespeed1);
                System.out.println(statisticsNew);
               // populationSize =populationSize1;
                neighbourSize = neighbourSize1;
                eps =eps1;
                changespeed =changespeed1;
                statistics =statisticsNew;
            }

            sigmaSPO = oneFirstRule(sigmaSPO,aSPO,(double)success/(double)numberOfRuns);
            System.out.println("N = "+populationSize+", neighbour = "+neighbourSize1+", eps = "+eps1+", speed = "+changespeed1);

        }

        System.out.println("N = "+populationSize+", neighbour = "+neighbourSize+", eps = "+eps);
        System.out.println(statisticsNew);

        System.out.println("END");

        /*For Deb 1 found =

         */
    }
}
