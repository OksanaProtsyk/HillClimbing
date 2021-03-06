package com.protsyk.ga.hillclimbing;

import com.protsyk.ga.hillclimbing.fenotype.DoubleAlgorithmRunner;
import com.protsyk.ga.hillclimbing.function.Deb1Function;
import com.protsyk.ga.hillclimbing.statistics.AllRunStatistics;

import java.util.Random;

/**
 * Created by okpr0814 on 4/14/2017.
 */
public class SPO {


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
        double neighbourSize = 1;
        double eps = 0.001;

        int populationSize1 = 1000;
        double changespeed = 2;
        double neighbourSize1 = 1;
        double eps1 = 0.001;
        double sigmaSPO = 0.1;

        double changespeed1 = 1;

        double aSPO = 1.3;
        DoubleAlgorithmRunner runner = null;
        AllRunStatistics statistics = null;
        DoubleAlgorithmRunner runnerNEw = null;
        AllRunStatistics statisticsNew = null;

        //statistics = runner.run();
        for (int t = 0; t < 3; t++) {
            Random random = new Random();

            runner = new DoubleAlgorithmRunner(new Deb1Function(2), populationSize, neighbourSize, eps,changespeed);
            statistics =runner.run();
            populationSize1 = (int) (populationSize + sigmaSPO * random.nextGaussian());
            neighbourSize1 = neighbourSize + sigmaSPO * random.nextGaussian();
            eps1 = eps + sigmaSPO * random.nextGaussian();
            changespeed1 = changespeed1+sigmaSPO*random.nextGaussian();

            runnerNEw = new DoubleAlgorithmRunner(new Deb1Function(2), populationSize1, neighbourSize1, eps1,changespeed1);

            statisticsNew = runnerNEw.run();

            if ((statisticsNew.percentOfAllPeakFound>=statistics.percentOfAllPeakFound)){
                System.out.println("N = "+populationSize+", neighbour = "+neighbourSize+", eps = "+eps);
                System.out.println(statisticsNew);
                populationSize =populationSize1;
                neighbourSize = neighbourSize1;
                eps =eps1;
                changespeed =changespeed1;
                //statistics =statisticsNew;
            }
            sigmaSPO = oneFirstRule(sigmaSPO,aSPO,statistics.percentOfAllPeakFound/100);

        }

        System.out.println("N = "+populationSize+", neighbour = "+neighbourSize+", eps = "+eps);
        System.out.println(statisticsNew);


        /*For Deb 1 found =

         */
    }
}
