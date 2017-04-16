package com.protsyk.ga.hillclimbing.function;


import java.util.Map;

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
        this.a = -10;
        this.b = 10;
    }

    public ShubertFunction(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = 1;
        for (int i = 0; i < values.length; i++) {
            double sum = 0;
            for (int j = 1; j < 5; j++) {
                sum += j * Math.cos((j + 1) * values[i] + j);
            }
            res *= sum;
        }
        return -res;
    }

    @Override
    public double fitONE(double data) {
            double sum = 0;
            for (int j = 1; j < 5; j++) {
                sum += j * Math.cos((j + 1) * data + j);
            }
        return -sum;    }

    @Override
    public double fitTwo(double x, double y) {

        double sumx = 0;
        for (int j = 1; j < 5; j++) {
            sumx += j * Math.cos((j + 1) * x + j);
        };
        double sumy = 0;
        for (int j = 1; j < 5; j++) {
            sumy += j * Math.cos((j + 1) * y + j);
        };
        return  -sumx*sumy;
    }

    @Override
    public Map<double[], Double> allMaximas() {
        return null;
    }

    @Override
    public Map<double[], Double> globalMaxima() {
        return null;
    }

    @Override
    public int numberOfExtremas() {
        return (int)(spaceSize*Math.pow(3,spaceSize));  //To change body of implemented methods use File | Settings | File Templates.
    }
}
