package com.protsyk.ga.hillclimbing.function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 3/24/17
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class CamelFunction extends AbstractFunction {
    public CamelFunction(){
        this.spaceSize = 2;
        this.a = -10;
        this.b = 10;
    }

    public CamelFunction(int spaceSize, double a, double b) {
        this.spaceSize = spaceSize;
        this.a = a;
        this.b = b;
    }

    @Override
    public double fit(double[] values) {
        double res = ((4 - 2.1 * values[0] * values[0] + Math.pow(values[0], 4) / 3) * values[0] * values[0] + values[0] * values[1] +
                4 * (values[1] * values[1] - 1) * values[1] * values[1]);
        return res;
    }

    @Override
    public double fitONE(double data) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double fitTwo(double x, double y) {
        return ((4 - 2.1 * x * x + Math.pow(x, 4) / 3) * x * x + x * y+
                4 * (y *y - 1) * y * y);
    }


    @Override
    public Map<double[], Double> allMaximas() {
        Map<double[],Double> map = new HashMap<>();
        double[] a1 = {-0.0898,0.7126};
        map.put(a1,fit(a1));
        double[] a2 = {0.0898,-0.7126};
        map.put(a2,fit(a2));
        double[] a3 = {-1.7036,0.7961};
        map.put(a3,fit(a3));

        double[] a4 = {1.7036,-0.7961};
        map.put(a4,fit(a4));

        double[] a5 = {-1.6071,-0.5687};
        map.put(a5,fit(a5));
        double[] a6 = {1.6071,0.5687};
        map.put(a6,fit(a6));
        return map;
    }

    @Override
    public int numberOfExtremas() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
