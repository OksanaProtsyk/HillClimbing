package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.utils.Utils;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 1:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryChromosome implements Chromosome {
    public int[] bits;
    FitnessFunction function;

    public BinaryChromosome(FitnessFunction f) {
        int paramCodeSize = f.spaceSize() * Utils.getM(f.a(), f.b(), f.after_comma());
        bits = new int[paramCodeSize];
        bits = new int[paramCodeSize];
        for (int i = 0; i < paramCodeSize; i++) {
            bits[i] = Calc.random() > 0.5 ? 1 : 0;
        }
        this.function = f;
    }

    public BinaryChromosome(FitnessFunction f,int[] bits) {
        int paramCodeSize = f.spaceSize() * Utils.getM(f.a(), f.b(), f.after_comma());
        this.bits = bits;
        this.function = f;
    }

    @Override
    public double fitness() {
        double[] values = function.decode(bits);
        return function.fit(values);
    }

    @Override
    public double[] decode() {
        return function.decode(bits);
    }

    @Override
    public void replace(int nuber, double value) {
            bits[nuber]=(int)value;
    }

    @Override
    public int compareTo(Chromosome o) {
        return (int) Math.signum(this.fitness() - o.fitness());
    }

    public FitnessFunction getFunction() {
        return function;
    }

    public void setFunction(FitnessFunction function) {
        this.function = function;
    }

    @Override
    public void setValues(double[] values) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < bits.length; i++) {
            res.append(bits[i]);
        }
        return res.toString();
    }
}
