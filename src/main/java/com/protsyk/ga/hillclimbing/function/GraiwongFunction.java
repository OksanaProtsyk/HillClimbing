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
public class GraiwongFunction extends AbstractFunction {


    public GraiwongFunction(int spaceSize) {
        this.spaceSize = spaceSize;
        this.a = -10;
        this.b = 10;
    }

    public GraiwongFunction(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = 0;
        double multpl = 1;
        for (int i = 0; i < values.length; i++) {
            res += (values[i] * values[i] )/ 4000;
            multpl *= Math.cos(values[i] / Math.sqrt(i+1));
        }
        return values.length- (res - multpl + 1);
    }

    @Override
    public double fitONE(double data) {

        return 1- (data* data / 4000 -  Math.cos(data )+1);
    }

    @Override
    public double fitTwo(double x, double y) {
        return 2 -(( x* x) / 4000 +(y* y )/ 4000 - Math.cos(x/Math.sqrt(1))*Math.cos(y / Math.sqrt(2))+1);
    }

    @Override
    public Map<double[], Double> allMaximas() {
        Map<double[],Double> map = new HashMap<>();
        double[] arr = new double[spaceSize];
        for (int i=0;i<spaceSize;i++){
            arr[i]=0;
        }
         map.put(arr,fit(arr));
        return map;
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
        return (int)Double.NaN;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int numberOfGlobalMaxima() {
        return 1;
    }
}
