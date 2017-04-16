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
        double neighbourSize1 = 1;
        double eps1 = 0.001;
        double sigmaSPO = 0.4;
        double aSPO = 1.5;
        DoubleAlgorithmRunner runner = null;
        AllRunStatistics statistics = null;
        DoubleAlgorithmRunner runnerNEw = null;
        AllRunStatistics statisticsNew = null;
        runner = new DoubleAlgorithmRunner(new Deb1Function(2), populationSize, neighbourSize, eps);

        statistics = runner.run();
        for (int t = 0; t < 3; t++) {
            Random random = new Random();


            populationSize1 = (int) (populationSize + sigmaSPO * random.nextGaussian());
            neighbourSize1 = neighbourSize + sigmaSPO * random.nextGaussian();
            eps1 = eps + sigmaSPO * random.nextGaussian();

            runnerNEw = new DoubleAlgorithmRunner(new Deb1Function(2), populationSize, neighbourSize, eps);

            statisticsNew = runnerNEw.run();

            if ((statisticsNew.avaragePeakFound>statistics.avaragePeakFound)
                    ||(statisticsNew.percentOfAllPeakFound>statistics.percentOfAllPeakFound)){

                populationSize =populationSize1;
                neighbourSize = neighbourSize1;
                eps =eps1;
                statistics =statisticsNew;
            }
            sigmaSPO = oneFirstRule(sigmaSPO,aSPO,statistics.percentOfAllPeakFound/100);

        }

        System.out.println("N = "+populationSize+", neighbour = "+neighbourSize+", eps = "+eps);
        System.out.println(statistics);


        /*For Deb 1 found =

         */
    }
}
