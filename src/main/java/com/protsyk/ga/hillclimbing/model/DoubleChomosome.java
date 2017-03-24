package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoubleChomosome implements Chromosome {
    double[] values;
    FitnessFunction function;

    public DoubleChomosome(FitnessFunction f) {
        this.function = f;
        values = new double[f.spaceSize()];
        for (int i = 0; i < f.spaceSize(); i++) {
            values[i] = Calc.random(f.a(), f.b());
        }
    }

    @Override
    public double fitness() {
        return function.fit(values);
    }

    @Override
    public double[] decode() {
        return values;
    }

    @Override
    public void replace(int nuber, double value) {
             values[nuber]=value;
    }

    @Override
    public int compareTo(Chromosome o) {
        return (int) Math.signum(this.fitness() - o.fitness());
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            res.append(values[i] + ",");
        }
        return res.toString();
    }
}
