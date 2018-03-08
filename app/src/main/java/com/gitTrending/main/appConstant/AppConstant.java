package com.gitTrending.main.appConstant;

/**
 * Created by anjanik on 3/8/2018.
 */

public class AppConstant {


    /**
     * declare reference of this class
     */
    public  static AppConstant mAppConstant;



    /**
     * create a singleton method for getting Util class object once
     * @return
     */
    public static AppConstant getObj(){

        if(mAppConstant == null){

            mAppConstant = new AppConstant();
        }

        return mAppConstant;
    }


    public String EMPTY_STRING = "";
    public String BASE_URL = "https://api.github.com/";

}
