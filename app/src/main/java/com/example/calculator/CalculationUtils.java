package com.example.calculator;

/* Vasudevan - Phase 1: Adding Utils class */
public class CalculationUtils {

    /* Vasudevan - Phase 1: Adding functions for sqaure and sqaure rool calculation */
    public double findSquare(double d1, double d2) {
        return Math.pow(d1, d2);
    }

    public double findSquareRoot(double d1) {
        return Math.sqrt(d1);
    }

    /* Monisha - Phase 1 : Adding functions for sin, cos and tan calculation */
    public double findSin(double d1) {
        return Math.sin(d1);
    }

    public double findCos(double d1) {
        return Math.cos(d1);
    }

    public double findTan(double d1) {
        return Math.tan(d1);
    }

    /* Nicki - Phase 1 : Adding function for finding percentage and modulus calculation*/
    public double findPercentage(double d1, double d2) {
        double result = (d1 * 100) / d2;
        return result;
    }

    public double findModulus(double d1, double d2) {
        return d1 % d2;
    }

    /* Vasudevan - Phase 2: Adding function for factorial calculation */
    public double findFact(double n) {
        double fact = 1;
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    /*Monisha  - phase 2:Adding Log functionality for base 10 */
   public double findLog(double e1) {

       return Math.log10(e1);
    }

    /*Monisha - phase 3 :Adding cube root functionality */
    public double findcbrt(double e1) {
        return Math.cbrt(e1);
    }

}

