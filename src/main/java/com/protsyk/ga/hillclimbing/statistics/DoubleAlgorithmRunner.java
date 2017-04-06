package com.protsyk.ga.hillclimbing.statistics;

import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.model.DoubleChomosome;
import com.protsyk.ga.hillclimbing.model.DoubleHillClimbing;
import com.protsyk.ga.hillclimbing.utils.DoublePopulationGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by okpr0814 on 4/7/2017.
 */
public class DoubleAlgorithmRunner {
    public FitnessFunction function;
    public DoubleChomosome[] population;
    public int populationSize;
    public double neighbourSize;
    public double eps;
    public static int NUMBER_OF_RUNS = 10;
    DoubleHillClimbing hillClimbing;

    public DoubleAlgorithmRunner(FitnessFunction function, int populationSize, double neighbourSize, double eps) {
        this.function = function;
        this.populationSize = populationSize;
        this.neighbourSize = neighbourSize;
        this.eps = eps;
        DoublePopulationGenerator doublePopulationGenerator = new DoublePopulationGenerator();
        this.population = doublePopulationGenerator.generatePopulation(populationSize,function);
         hillClimbing = new DoubleHillClimbing(this.population, neighbourSize, eps);

    }

    public void run (){
        List<SingleRunStatistics> statisticsList = new ArrayList<>();
        for (int i=0;i<NUMBER_OF_RUNS;i++){
            SingleRunStatistics st = hillClimbing.run();
            statisticsList.add(st);
            System.out.print(st);
            hillClimbing.population=this.population;
            hillClimbing.initialNeighbourhood=neighbourSize;
        }
    }
}
