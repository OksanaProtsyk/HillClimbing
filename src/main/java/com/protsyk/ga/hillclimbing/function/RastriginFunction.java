package com.protsyk.ga.hillclimbing.function;


import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RastriginFunction extends AbstractFunction {
    private static double A = 10;
    private static double w = 2 * Math.PI;

    public RastriginFunction(int spaceSize) {
        this.spaceSize = spaceSize;
        this.a = -5.12;
        this.b = 5.12;
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
        double res = (A * Math.cos(w * data) - data * data);

        return res - 1 * A;
    }

    @Override
    public double fitTwo(double x, double y) {
        return (A * Math.cos(w * x) - x * x)
                +(A * Math.cos(w * y) - y * y)
                -2*A;
    }


    @Override
    public Map<double[], Double> allMaximas() {
        return globalMaxima();

    }

    @Override
    public Map<double[], Double> globalMaxima() {
        Map<double[],Double> map = new HashMap<>();
        double[] arr = new double[spaceSize];
        for (int i=0;i<spaceSize;i++){
            arr[i]=0;
        }
        map.put(arr,fit(arr));
        return map;
    }

    @Override
    public int numberOfExtremas() {
        return (int)Math.pow(10,spaceSize);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
