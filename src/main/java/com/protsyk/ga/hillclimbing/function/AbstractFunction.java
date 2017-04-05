package com.protsyk.ga.hillclimbing.function;

import com.protsyk.ga.hillclimbing.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFunction implements FitnessFunction {
    public  double a = 0;
    public  double b = 1;
    public  int maxNFE= 2000000;

    public static int AFTER_COMMA = 3;
    public int spaceSize = 1;


    @Override
    public double[] decode(int[] chomosome) {
        double[] resVector = new double[spaceSize];
        int[][] chromPerSpace = getBitsForSpace(chomosome);
        for (int i = 0; i < spaceSize; i++) {
            resVector[i] = Utils.decodeToDouble(a, b, AFTER_COMMA, chromPerSpace[i]);
        }

        return resVector;
    }

    @Override
    public int[] code(double[] x) {
        int[][] matt = new int[x.length][];
        for (int i = 0; i < x.length; i++) {
            matt[i] = Utils.codeToBinary(a, b, AFTER_COMMA, x[i]);
            System.out.println("fg");
            Utils.printArr(matt[i]);
        }
        return makeChromosome(matt);
    }

    public int[][] getBitsForSpace(int[] chromosome) {
        int[][] chromosomePerVar = new int[spaceSize][Utils.getM(a, b, AFTER_COMMA)];
        for (int i = 0; i < spaceSize; i++) {
            for (int j = 0; j < Utils.getM(a, b, AFTER_COMMA); j++) {
                chromosomePerVar[i][j] = chromosome[i * Utils.getM(a, b, AFTER_COMMA) + j];
            }

        }

        return chromosomePerVar;
    }

    public int[] makeChromosome(int[][] space) {
        int[] bits = new int[space.length * space[0].length];
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[i].length; j++) {
                bits[i * space[i].length + j] = space[i][j];
            }
        }
        return bits;
    }

    @Override
    public double a() {
        return a;
    }

    @Override
    public double b() {
        return b;
    }

    @Override
    public int after_comma() {
        return AFTER_COMMA;
    }

    @Override
    public int spaceSize() {
        return spaceSize;
    }
    @Override
    public int maxNFE() {
        return maxNFE;
    }

    List<Double> findAllMaxima(){
        ArrayList<Double> maximas = new ArrayList<Double>();
        return maximas;
    }
}