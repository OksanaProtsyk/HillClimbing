package com.protsyk.ga.hillclimbing.function;


/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RastriginFunction extends AbstractFunction {
    public static double a = -5.12;
    public static double b = 5.12;
    private static double A = 10;
    private static double w = 2 * Math.PI;

    public RastriginFunction(int spaceSize) {
        this.spaceSize = spaceSize;
    }

    public RastriginFunction(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = 0;
        for (int i = 0; i < values.length; i++) {
            res += (A * Math.cos(w * values[i]) - values[i] * values[i]);
        }
        return res - values.length * A;
    }

    @Override
    public double fitONE(double data) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
