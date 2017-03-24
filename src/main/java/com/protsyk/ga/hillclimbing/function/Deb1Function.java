package com.protsyk.ga.hillclimbing.function;

import com.protsyk.ga.BitString;
import com.protsyk.ga.hillclimbing.model.Chromosome;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Deb1Function extends AbstractFunction {

    public Deb1Function(int spaceSize) {
        this.spaceSize = spaceSize;
    }

    public Deb1Function(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = 0;
        for (int i = 0; i < values.length; i++) {
            res += Math.pow(Math.sin(5 * values[i] * Math.PI), 6);
        }
        return res / values.length;
    }
}
