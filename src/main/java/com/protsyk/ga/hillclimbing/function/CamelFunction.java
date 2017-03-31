package com.protsyk.ga.hillclimbing.function;

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


}
