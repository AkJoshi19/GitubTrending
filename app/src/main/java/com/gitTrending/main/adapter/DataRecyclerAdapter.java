package com.gitTrending.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gitTrending.main.R;
import com.gitTrending.main.activity.ProfileDetailsActivity;
import com.gitTrending.main.appConstant.Utils;
import com.gitTrending.main.modal.GitInfoModal;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by anjanik on 3/8/2018.
 */
public class DataRecyclerAdapter extends ComicsBaseRecyclerAdapter {


    private Activity mActivity;
    private ArrayList<GitInfoModal.Item> mComicsArrayList;

    private LayoutInflater mLayoutInflater;


    public DataRecyclerAdapter(Activity activity, ArrayList<GitInfoModal.Item> comicsList) {

        this.mActivity = activity;
        this.mComicsArrayList = comicsList;

        mLayoutInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public InsectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mLayoutInflater.inflate(R.layout.comics_item, parent, false);

        return new InsectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        /**
         * get data from comics array list and set into comics item views
         */
        final GitInfoModal.Item comics = mComicsArrayList.get(position);

        final InsectViewHolder comicsViewHolder = (InsectViewHolder) holder;

        // update the data into views
        if (!TextUtils.isEmpty(comics.getFullName()))
            comicsViewHolder.txtViewComicsName.setText(comics.getFullName());

        if (comics.getDescription()!=null)
            comicsViewHolder.txtViewComicsDescr.setText(comics.getDescription().toString());

        if (comics.getScore()!=null)
            comicsViewHolder.txtViewComicsPrice.setText("Score : "+ Utils.doubleToStr(comics.getScore()));

        if (comics.getWatchers()!=null)
            comicsViewHolder.txtViewComicsPage.setText("Watchers : "+comics.getWatchers());

        comicsViewHolder.txtViewComicsCounter.setText("Count : "+ (position+1));

        ImageLoader.getInstance().displayImage(comics.getOwner().getAvatarUrl(), comicsViewHolder.comicsImageView,
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        comicsViewHolder.progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        comicsViewHolder.progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        comicsViewHolder.progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        comicsViewHolder.progressBar.setVisibility(View.GONE);

                    }
                });


        // holder.setIsRecyclable(false);

        holder.itemView.setTag(comics);

        /**
         * click event on recycler item
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Open the detail screen
                 */
                Intent intentDetailsScreen = new Intent(mActivity, ProfileDetailsActivity.class);
                intentDetailsScreen.putExtra(ProfileDetailsActivity.KEY_COMICS_DATA, comics);
                mActivity.startActivity(intentDetailsScreen);

            }
        });

        holder.setIsRecyclable(false);


    }


    /**
     * create a view holder which will be used for item view of adapter
     */
    class InsectViewHolder extends RecyclerView.ViewHolder {

        private ImageView comicsImageView;
        private TextView txtViewComicsDescr;
        private TextView txtViewComicsName;

        private TextView txtViewComicsPrice;
        private TextView txtViewComicsPage;
        private TextView txtViewComicsCounter;


        private ProgressBar progressBar;


        public InsectViewHolder(View view) {

            super(view);

            comicsImageView = (ImageView) view.findViewById(R.id.row_comics_icon);
            txtViewComicsName = (TextView) view.findViewById(R.id.row_comics_title);
            txtViewComicsDescr = (TextView) view.findViewById(R.id.comics_description);

            txtViewComicsPrice = (TextView) view.findViewById(R.id.row_comics_cost);
            txtViewComicsPage = (TextView) view.findViewById(R.id.row_comics_page);
            txtViewComicsCounter = (TextView) view.findViewById(R.id.row_comics_counter);


            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        }
    }


    @Override
    public int getItemCount() {
        return mComicsArrayList.size();
    }

    @Override
    public GitInfoModal.Item getItem(int position) {
        return mComicsArrayList.get(position);
    }
}
