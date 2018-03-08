package com.gitTrending.main.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by anjanik on 3/8/2018.
 */

public class MyApplication extends Application {


    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * initialize image loader
         */
        initializingImageLoader();

    }

    private void initializingImageLoader(){

        DisplayImageOptions opts = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(opts).build();
        ImageLoader.getInstance().init(config);

    }
}


