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
   public double[] values;

    public FitnessFunction getFunction() {
        return function;
    }

    public void setFunction(FitnessFunction function) {
        this.function = function;
    }

    FitnessFunction function;

    public DoubleChomosome(FitnessFunction f) {
        this.function = f;
        values = new double[f.spaceSize()];
        for (int i = 0; i < f.spaceSize(); i++) {
            values[i] = Calc.random(f.a(), f.b());
        }
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public DoubleChomosome(FitnessFunction f, double[] values) {
        this.function = f;

        this.values = values;
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
    @Override
    public  DoubleChomosome clone(){
        DoubleChomosome ch = new DoubleChomosome(this.getFunction(),values.clone());
        return ch;

    }
}
