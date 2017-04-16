package com.protsyk.ga.hillclimbing.statistics;

import com.protsyk.ga.hillclimbing.model.Optima;
import com.protsyk.ga.hillclimbing.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * Created by okpr0814 on 4/7/2017.
 */
public class SingleRunStatistics {
    public int NFE;
    public int numberOfPeaks;
    public int numberolGlobalPeaks;
    public double numberOfPeaksToFound;
    public int numberolGlobalPeaksToFound;
    public double peakRatio;
    public double globalPeakRatio;
    public double peakAccurancy;
    public double globalPeakAccurancy;
    public double distanceAccurancy;
    public double globalDistanceAccurancy;
    public List<Optima> optimas;
    public Map<double[], Double> foundseeds;


    @Override
    public String toString() {
        return "SingleRunStatistics{" +
                "NFE=" + NFE +
                ", numberOfPeaks=" + numberOfPeaks +
                ", numberolGlobalPeaks=" + numberolGlobalPeaks +
                ", numberOfPeaksToFound=" + numberOfPeaksToFound +
                ", numberolGlobalPeaksToFound=" + numberolGlobalPeaksToFound +
                ", peakRatio=" + peakRatio +
                ", globalPeakRatio=" + globalPeakRatio +
                ", peakAccurancy=" + peakAccurancy +
                ", globalPeakAccurancy=" + globalPeakAccurancy +
                ", distanceAccurancy=" + distanceAccurancy +
                ", globalDistanceAccurancy=" + globalDistanceAccurancy +
                ", foundseedsKeys=" + foundseeds.keySet() +
                ", foundseedsValues=" + foundseeds.values() +
                '}';
    }

    public String listOfValuesForOptima(){
        String res = "";
        for (Optima o: optimas){
            res+=o.getPointsCoordinates();
            res+=";";
        }
        return res;
    }




    public String listOfExtremaForOptima(){
        String res = "";
        for (Optima o: optimas){
            res+=o.extremaFound;
            res+=";";
        }
        return res;
    }

}
