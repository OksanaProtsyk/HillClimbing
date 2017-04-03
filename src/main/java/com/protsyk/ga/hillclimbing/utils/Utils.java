package com.protsyk.ga.hillclimbing.utils;


/**
 * Created by okpr0814 on 3/23/2017.
 */
public class Utils {
    public static int[] getBinaryCode(double x) {
        return null;
    }

    public static int[] codeToBinary(double a, double b, int afterComa, double value) {
        int m = getM(a, b, afterComa);
        int[] res = intToBinary((int) ((value - a) / (b - a) * (Math.pow(2, m) - 1)));
        int[] bits = new int[m];
        if (bits.length > res.length) {
            for (int i = 0; i < bits.length - res.length; i++) {
                bits[i] = 0;
            }
            for (int i = bits.length - res.length; i < bits.length; i++) {
                bits[i] = res[i - (bits.length - res.length)];
            }
        }
        return bits;
    }

    public static double decodeToDouble(double a, double b, int afterComa, int[] bits) {
        return a + binaryToInt(bits) * (b - a) / (Math.pow(2, getM(a, b, afterComa)) - 1);
    }

    public static int[] intToBinary(int n) {
        String res = Integer.toBinaryString(n);
        int[] array = new int[res.length()];
        for (int i = 0; i < res.length(); i++) {
            array[i] = Character.getNumericValue(res.charAt(i));
        }
        return array;
    }

    public static int binaryToInt(int[] bits) {
        int result = 0;
        for (int i = bits.length - 1; i >= 0; i--)
            if (bits[i] == 1)
                result += Math.pow(2, (bits.length - i - 1));
        return result;
    }

    public static int getM(double a, double b, double aftercoma) {
        return Math.round((float) log((b - a) * Math.pow(10, aftercoma) + 1, 2));
    }

    static double log(double x, int base) {
        return (Math.log(x) / Math.log(base));
    }

    public static void printArr(double[] r) {
        for (int i = 0; i < r.length; i++) {
            System.out.print(r[i]);
            System.out.println();
        }
    }

    public static void printArr(int[] r) {
        for (int i = 0; i < r.length; i++) {
            System.out.print(r[i]);
        }
        System.out.println();

    }

    public static void printArr(Object[] r) {
        for (int i = 0; i < r.length; i++) {
            System.out.println(r[i]);
        }

    }

    public static int[] grayToBinary(int[] grayArray) {
         int[] binary = new int[grayArray.length];
        binary[0] = grayArray[0];


        // Compute remaining bits
        for (int i = 1; i < grayArray.length; i++)
        {
            // If current bit is 0, concatenate previous bit
            if (grayArray[i] == 0)
                binary[i] += binary[i - 1];

                // Else, concatenate invert of previous bit
            else
                binary[i]= flip(binary[i - 1]);
        }


        return binary;

    }

    public static int xor(int i, int j) {
        if ((i == j) && ((i == 1) || i == 0)) {
            return 0;
        }
        //else if ((i != j) && ((i == 1) || i == 0)) {
        return 1;

    }

    public static int flip(int c){
        return (c == 0)? 1: 0;
    }
}
