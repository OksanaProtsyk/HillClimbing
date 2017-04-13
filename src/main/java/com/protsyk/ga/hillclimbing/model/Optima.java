package com.protsyk.ga.hillclimbing.model;

import com.protsyk.ga.hillclimbing.utils.Utils;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 4/11/17
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Optima {
    public double[] pointcordinatesFound;
    public double extremaFound;
    public double actualExtrama;
    public double[] actualExtremaValues;


    public Optima(double[] pointcordinatesFound, double extremaFound, double actualExtrama, double[] actualExtremaValues) {
        this.pointcordinatesFound = pointcordinatesFound;
        this.extremaFound = extremaFound;
        this.actualExtrama = actualExtrama;
        this.actualExtremaValues = actualExtremaValues;
    }

    @Override
    public String toString() {
        return "Optima{" +
                "pointcordinatesFound=" + Arrays.toString(pointcordinatesFound) +
                ", valueFound=" + extremaFound +
                ", actualExtrama=" + actualExtrama +
                '}';
    }

    public String getPointsCoordinates(){
        String res = "";
        for (double d:pointcordinatesFound){
            res+=d;
            res+=",";
        }
        return res;
    }


}
