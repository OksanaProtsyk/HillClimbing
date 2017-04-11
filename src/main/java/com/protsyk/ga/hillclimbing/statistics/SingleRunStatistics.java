package com.protsyk.ga.hillclimbing.statistics;

import com.protsyk.ga.hillclimbing.model.Optima;
import com.protsyk.ga.hillclimbing.utils.Utils;

import java.util.List;

/**
 * Created by okpr0814 on 4/7/2017.
 */
public class SingleRunStatistics {
    public int NFE;
    public int numberOfPeaks;
    public double peakRatio;
    public double peakAccurancy;
    public double distanceAccurancy;
    public double numberOfPeaksToFound;
    public List<Optima> optimas;


    @Override
    public String toString() {
        Utils.printArr(optimas);
        return "SingleRunStatistics{" +
                "NFE=" + NFE +
                ", numberOfPeaks=" + numberOfPeaks +
                ", peakRatio=" + peakRatio +
                ", peakAccurancy=" + peakAccurancy +
                ", distanceAccurancy=" + distanceAccurancy +
                '}';
    }
}
