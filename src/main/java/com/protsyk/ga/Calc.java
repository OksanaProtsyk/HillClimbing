package com.protsyk.ga;


/**
 * Created by okpr0814 on 3/23/2017.
 */
public class Calc {
    private static ThreadLocal<Random> random = new ThreadLocal<Random>() {
        protected synchronized Random initialValue() {
            if (Thread.currentThread().getName().equals("run-main-0")) {

                return new Random();
            } else {

                java.security.SecureRandom sr = new java.security.SecureRandom();
                byte[] bytes = sr.generateSeed(Long.BYTES);
                long seed = 0;
                for (int i = 0; i < Long.BYTES; i++) {
                    seed <<= 8;
                    seed |= (bytes[i] & 0xFF);
                }

                return new Random(seed);
            }
        }
    };
    public static int random(double[] prob) {
        int[] ans = random(prob, 1);
        return ans[0];
    }


    public static int[] random(double[] prob, int n) {
        // set up alias table
        double[] q = new double[prob.length];
        for (int i = 0; i < prob.length; i++) {
            q[i] = prob[i] * prob.length;
        }

        // initialize a with indices
        int[] a = new int[prob.length];
        for (int i = 0; i < prob.length; i++) {
            a[i] = i;
        }

        // set up H and L
        int[] HL = new int[prob.length];
        int head = 0;
        int tail = prob.length - 1;
        for (int i = 0; i < prob.length; i++) {
            if (q[i] >= 1.0) {
                HL[head++] = i;
            } else {
                HL[tail--] = i;
            }
        }

        while (head != 0 && tail != prob.length - 1) {
            int j = HL[tail + 1];
            int k = HL[head - 1];
            a[j] = k;
            q[k] += q[j] - 1;
            tail++;                                  // remove j from L
            if (q[k] < 1.0) {
                HL[tail--] = k;                      // add k to L
                head--;                              // remove k
            }
        }

        // generate sample
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            double rU = random() * prob.length;

            int k = (int) (rU);
            rU -= k;  /* rU becomes rU-[rU] */

            if (rU < q[k]) {
                ans[i] = k;
            } else {
                ans[i] = a[k];
            }
        }

        return ans;
    }

    public static double random() {
        return random.get().nextDouble();
    }

    public static double[] random(int n) {
        double[] x = new double[n];
        random.get().nextDoubles(x);
        return x;
    }


    public static double random(double lo, double hi) {
        return random.get().nextDouble(lo, hi);
    }


    public static double[] random(double lo, double hi, int n) {
        double[] x = new double[n];
        random.get().nextDoubles(x, lo, hi);
        return x;
    }


    public static int randomInt(int n) {
        return random.get().nextInt(n);
    }


    public static int randomInt(int lo, int hi) {
        int w = hi - lo;
        return lo + random.get().nextInt(w);
    }


    public static void unitize(double[] x) {

        unitize2(x);
    }


    public static void unitize1(double[] x) {
        double n = norm1(x);

        for (int i = 0; i < x.length; i++) {
            x[i] /= n;
        }
    }
    public static double norm(double[] x) {
        return norm2(x);
    }

    public static void unitize2(double[] x) {
        double n = norm(x);

        for (int i = 0; i < x.length; i++) {
            x[i] /= n;
        }
    }


    public static double norm1(double[] x) {
        double norm = 0.0;

        for (double n : x) {
            norm += Math.abs(n);
        }

        return norm;
    }

    public static double norm2(double[] x) {
        double norm = 0.0;

        for (double n : x) {
            norm += n * n;
        }

        norm = Math.sqrt(norm);

        return norm;
    }

    public static void swap(int[] x, int i, int j) {
        int s = x[i];
        x[i] = x[j];
        x[j] = s;
    }


    public static void swap(float[] x, int i, int j) {
        float s = x[i];
        x[i] = x[j];
        x[j] = s;
    }


    public static void swap(double[] x, int i, int j) {
        double s = x[i];
        x[i] = x[j];
        x[j] = s;
    }


    public static void swap(Object[] x, int i, int j) {
        Object s = x[i];
        x[i] = x[j];
        x[j] = s;
    }


    public static void swap(int[] x, int[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException(String.format("Arrays have different length: x[%d], y[%d]", x.length, y.length));
        }

        for (int i = 0; i < x.length; i++) {
            int s = x[i];
            x[i] = y[i];
            y[i] = s;
        }
    }


    public static void swap(float[] x, float[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException(String.format("Arrays have different length: x[%d], y[%d]", x.length, y.length));
        }

        for (int i = 0; i < x.length; i++) {
            float s = x[i];
            x[i] = y[i];
            y[i] = s;
        }
    }


    public static void swap(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException(String.format("Arrays have different length: x[%d], y[%d]", x.length, y.length));
        }

        for (int i = 0; i < x.length; i++) {
            double s = x[i];
            x[i] = y[i];
            y[i] = s;
        }
    }


    public static <E> void swap(E[] x, E[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException(String.format("Arrays have different length: x[%d], y[%d]", x.length, y.length));
        }

        for (int i = 0; i < x.length; i++) {
            E s = x[i];
            x[i] = y[i];
            y[i] = s;
        }
    }


}
