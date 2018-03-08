package com.gitTrending.main.appConstant;

import java.text.DecimalFormat;

/**
 * Created by anjanik on 3/8/2018.
 */

public class Utils {


    public static String doubleToStr(double value){

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.valueOf(df.format(value));


        return ""+value;
    }
}
