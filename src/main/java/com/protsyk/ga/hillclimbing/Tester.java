package com.protsyk.ga.hillclimbing;

import com.protsyk.ga.hillclimbing.statistics.XLSGeneartorForAFunction;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 4/12/17
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tester {
    public static void main(String[] arg){
        XLSGeneartorForAFunction gen = new XLSGeneartorForAFunction();
        gen.generateXLSX();
    }
}
