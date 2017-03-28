package com.protsyk.ga.hillclimbing.function;


/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShubertFunction extends AbstractFunction {

    public ShubertFunction(int spaceSize) {
        this.spaceSize = spaceSize;
    }

    public ShubertFunction(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = 0;
        for (int i = 0; i < values.length; i++) {
            double sum = 0;
            for (int j = 1; j < 5; j++) {
                sum += j * Math.cos((j + 1) * values[i] + j);
            }
            res *= sum;
        }
        return res;
    }

    @Override
    public double fitONE(double data) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
