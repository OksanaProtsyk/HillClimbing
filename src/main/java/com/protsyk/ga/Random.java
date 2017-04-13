/*******************************************************************************
 * Copyright (c) 2010 Haifeng Li
 *   
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.protsyk.ga;


import com.protsyk.ga.random.RandomNumberGenerator;
import com.protsyk.ga.random.UniversalGenerator;

public class Random {

    private RandomNumberGenerator rng;


    public Random() {
        this(new UniversalGenerator());
    }


    public Random(long seed) {
        this(new UniversalGenerator(seed));
    }


    public Random(RandomNumberGenerator rng) {
        this.rng = rng;
    }


    public double nextDouble() {
        return rng.nextDouble();
    }


    public void nextDoubles(double[] d) {
        rng.nextDoubles(d);
    }


    public double nextDouble(double lo, double hi) {
        return (lo + (hi - lo) * nextDouble());
    }


    public void nextDoubles(double[] d, double lo, double hi) {
        rng.nextDoubles(d);

        double l = hi - lo;        
        int n = d.length;
        for (int i = 0; i < n; i++) {
            d[i] = lo + l * d[i];
        }
    }


    public void setSeed(long seed) {
        rng.setSeed(seed);
    }


    public int nextInt() {
        return rng.nextInt();
    }
    

    public int nextInt(int n) {
        return rng.nextInt(n);
    }

    public long nextLong() {
        return rng.nextLong();
    }


    public int[] permutate(int n) {
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = i;
        }

        permutate(x);

        return x;
    }


    public void permutate(int[] x) {
        for (int i = 0; i < x.length; i++) {
            int j = i + nextInt(x.length - i);
            Calc.swap(x, i, j);
        }
    }


    public void permutate(float[] x) {
        for (int i = 0; i < x.length; i++) {
            int j = i + nextInt(x.length - i);
            Calc.swap(x, i, j);
        }
    }


    public void permutate(double[] x) {
        for (int i = 0; i < x.length; i++) {
            int j = i + nextInt(x.length - i);
            Calc.swap(x, i, j);
        }
    }


    public void permutate(Object[] x) {
        for (int i = 0; i < x.length; i++) {
            int j = i + nextInt(x.length - i);
            Calc.swap(x, i, j);
        }
    }
}