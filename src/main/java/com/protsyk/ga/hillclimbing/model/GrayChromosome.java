package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.Calc;
import com.protsyk.ga.hillclimbing.function.FitnessFunction;
import com.protsyk.ga.hillclimbing.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 4/3/17
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrayChromosome extends BinaryChromosome {

    public GrayChromosome(FitnessFunction f) {
       super(f);
    }



    @Override
    public double[] decode() {
        return function.decode(Utils.grayToBinary(bits));
    }

}
