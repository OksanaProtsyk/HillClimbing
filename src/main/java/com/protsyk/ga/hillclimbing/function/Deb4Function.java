package com.protsyk.ga.hillclimbing.function;


/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Deb4Function extends AbstractFunction {

    public Deb4Function(int spaceSize) {
        this.spaceSize = spaceSize;
    }

    public Deb4Function(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = 0;
        for (int i = 0; i < values.length; i++) {
            res += Math.pow(Math.E, -2 * (Math.log(2) * Math.pow((values[i] - 0.08) / 0.854, 2))) * Math.pow(Math.sin(5 * Math.PI * (Math.pow(values[i], 0.75) - 0.05)), 6);
        }
        return res;
    }

    @Override
    public double fitONE(double data) {
        return Math.pow(Math.E, -2 * (Math.log(2) * Math.pow((data - 0.08) / 0.854, 2))) * Math.pow(Math.sin(5 * Math.PI * (Math.pow(data, 0.75) - 0.05)), 6);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
