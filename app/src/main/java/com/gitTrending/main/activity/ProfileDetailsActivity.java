package com.gitTrending.main.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gitTrending.main.R;
import com.gitTrending.main.appConstant.Utils;
import com.gitTrending.main.modal.GitInfoModal;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by anjanik on 3/8/2018.
 */
public class ProfileDetailsActivity extends AppCompatActivity {


    private static final String TAG = ProfileDetailsActivity.class.getSimpleName();
    private GitInfoModal.Item mComicsData;
    public static final String KEY_COMICS_DATA = "KEY_COMICS_DATA";

    /**
     * declare views of xml layout
     */
    private TextView textViewWatcher, textViewDescription,
            textViewSvnUrl, textViewTitle,
            textViewScore;
     private ImageView imageViewProfile;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        //

        /**
         * initialize views of xml
         */

        textViewTitle = (TextView) findViewById(R.id.textView_title);
        textViewDescription = (TextView) findViewById(R.id.textView_description);
        textViewWatcher = (TextView) findViewById(R.id.textView_watcher);
        textViewScore = (TextView) findViewById(R.id.textView_score);
        textViewSvnUrl = (TextView) findViewById(R.id.textView_svnURL);
        imageViewProfile = (ImageView) findViewById(R.id.imageView_profile);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        /**
         * set insect data in views
         */
        setUpDataIntoViews();

    }


    /**
     * set up details screen data
     */
    private void setUpDataIntoViews() {

        Intent dataIntent = getIntent();
        if (dataIntent != null && dataIntent.hasExtra(KEY_COMICS_DATA)) {

            /**
             * get insect data from intent
             */
            mComicsData = (GitInfoModal.Item) dataIntent.getSerializableExtra(KEY_COMICS_DATA);

            if (mComicsData != null) {

                if (!TextUtils.isEmpty(mComicsData.getFullName())){
                    textViewTitle.setText(mComicsData.getFullName());
                }

                if (mComicsData.getDescription()!=null)
                    textViewDescription.setText(mComicsData.getDescription().toString());




                if (!TextUtils.isEmpty(""+mComicsData.getWatchers()))
                    textViewWatcher.setText(""+mComicsData.getWatchers());


                if (mComicsData.getScore()!=null ){
                    textViewScore.setText(
                            Utils.doubleToStr(mComicsData.getScore()));
                }

                if (!TextUtils.isEmpty(""+mComicsData.getSvnUrl())){
                    textViewSvnUrl.setText(""+mComicsData.getSvnUrl());
                    textViewSvnUrl.setMovementMethod(LinkMovementMethod.getInstance());
                }

                textViewSvnUrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String url = mComicsData.getSvnUrl();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });


                ImageLoader.getInstance().displayImage(mComicsData.getOwner().getAvatarUrl(), imageViewProfile,
                        new ImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(String imageUri, View view) {
                                progressBar.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                                progressBar.setVisibility(View.GONE);

                            }

                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                progressBar.setVisibility(View.GONE);

                            }

                            @Override
                            public void onLoadingCancelled(String imageUri, View view) {
                                progressBar.setVisibility(View.GONE);

                            }
                        });
            }
        }

    }
}
