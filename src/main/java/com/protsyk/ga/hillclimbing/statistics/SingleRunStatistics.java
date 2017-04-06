package com.protsyk.ga.hillclimbing.statistics;

/**
 * Created by okpr0814 on 4/7/2017.
 */
public class SingleRunStatistics {
    public int NFE;
    public int numberOfPeaks;
    public double peakRatio;
    public double peakAccurancy;
    public double distanceAccurancy;


    @Override
    public String toString() {
        return "SingleRunStatistics{" +
                "NFE=" + NFE +
                ", numberOfPeaks=" + numberOfPeaks +
                ", peakRatio=" + peakRatio +
                ", peakAccurancy=" + peakAccurancy +
                ", distanceAccurancy=" + distanceAccurancy +
                '}';
    }
}
