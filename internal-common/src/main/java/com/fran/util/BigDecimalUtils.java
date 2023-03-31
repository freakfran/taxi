package com.fran.util;

import java.math.BigDecimal;

public class BigDecimalUtils {
    public static double add(double a,double b){
        BigDecimal a1 = BigDecimal.valueOf(a);
        BigDecimal b1 = BigDecimal.valueOf(b);
        return a1.add(b1).doubleValue();
    }
}
