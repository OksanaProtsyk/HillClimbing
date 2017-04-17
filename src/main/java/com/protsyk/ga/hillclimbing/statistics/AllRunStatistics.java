package com.protsyk.ga.hillclimbing.statistics;

import java.util.List;

/**
 * Created by okpr0814 on 4/7/2017.
 */
public class AllRunStatistics {
    public double percentOfAllPeakFound;
    public double avaragePeakFound;
    public double avarageNFE;
    public double maxNFE;
    public double avaragePR;
    public double maxPR;
    public double avaragePA;
    public double maxPA;
    public double avarageDA;
    public double maxDA;



    public double globalPercentOfAllPeakFound;
    public double globalAvaragePeakFound;
    public double globalAvarageNFE;
    public double globalMaxNFE;
    public double globalAvaragePR;
    public double globalMaxPR;
    public double globalAvaragePA;
    public double globalMaxPA;
    public double globalAvarageDA;
    public double globalMaxDA;

    public List<SingleRunStatistics> listOFSinglerRuns;

    @Override
    public String toString() {
        return "AllRunStatistics{" +
                "percentOfAllPeakFound=" + percentOfAllPeakFound +
                ", avaragePeakFound=" + avaragePeakFound +
                ", avarageNFE=" + avarageNFE +
                ", maxNFE=" + maxNFE +
                ", avaragePR=" + avaragePR +
                ", maxPR=" + maxPR +
                ", avaragePA=" + avaragePA +
                ", maxPA=" + maxPA +
                ", avarageDA=" + avarageDA +
                ", maxDA=" + maxDA +
                '}';
    }
}
