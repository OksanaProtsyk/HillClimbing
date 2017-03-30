package com.protsyk.ga.hillclimbing.function;


/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Deb2Function extends AbstractFunction {

    public Deb2Function(int spaceSize) {
        this.spaceSize = spaceSize;

    }

    public Deb2Function(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = 0;
        for (int i = 0; i < values.length; i++) {
            res += Math.pow(Math.E, -2 * (Math.log(2) * Math.pow((values[i] - 0.1) / 0.8, 2))) * Math.pow(Math.sin(5 * values[i] * Math.PI), 6);
        }
        return res;
    }

    @Override
    public double fitONE(double data) {
        return Math.pow(Math.E, -2 * (Math.log(2) * Math.pow((data - 0.1) / 0.8, 2))) * Math.pow(Math.sin(5 * data * Math.PI), 6);
    }

    @Override
    public double fitTwo(double x, double y) {
        return 0;
    }


}
