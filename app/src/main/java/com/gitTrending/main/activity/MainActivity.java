package com.gitTrending.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gitTrending.main.R;
import com.gitTrending.main.adapter.DataRecyclerAdapter;
import com.gitTrending.main.modal.GitInfoModal;
import com.gitTrending.main.view.CustomProgressDialog;
import com.gitTrending.main.webApi.ApiClient;
import com.gitTrending.main.webApi.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by anjanik on 3/8/2018.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * declare references used in this class
     */
    private DataRecyclerAdapter mDataRecyclerAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<GitInfoModal.Item> mGitInfoDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * get view from xml
         * and inflate in java objects
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        /**
         * get comics list from marvel api
         * call the web api
         */
        getTrendingData();

    }

    /**
     * get git data from retrofit client
     */
    public void getTrendingData() {

        /**
         * show if project is showing
         */
        CustomProgressDialog.getProgressDialog(this).showDialog1();
        try {


            ApiInterface mApiInterface = ApiClient.getClient().getRetrofitInstance().create(ApiInterface.class);
            Call<GitInfoModal> call = mApiInterface.getTopRatedMovies1("java", "Android");


            call.enqueue(new retrofit2.Callback<GitInfoModal>() {
                @Override
                public void onResponse(Call<GitInfoModal> call, retrofit2.Response<GitInfoModal> response) {

                    CustomProgressDialog.getProgressDialog(MainActivity.this).dismissDialog1();

                    try {

                        /**
                         * get data from web server
                         */
                        GitInfoModal comicsDataModel = response.body();

                        mGitInfoDataList = (ArrayList<GitInfoModal.Item>) comicsDataModel.getItems();

                        /**
                         * set data into list
                         */
                        settingUpDataAdapter();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<GitInfoModal> call, Throwable t) {

                    CustomProgressDialog.getProgressDialog(MainActivity.this).dismissDialog1();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setting up comic adapter list
     */
    private void settingUpDataAdapter() {

        /**
         * create a custom comics adapter which extends the DataRecyclerAdapter
         * which is provided into the project
         */
        mDataRecyclerAdapter = new DataRecyclerAdapter(this, mGitInfoDataList);

        /**
         * create layout manager, of which type you want to set layout in recycler view
         * and ser layout manager into
         */
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        /**
         * set adapter into recycler view
         */
        mRecyclerView.setAdapter(mDataRecyclerAdapter);
        mDataRecyclerAdapter.notifyDataSetChanged();
    }


    private void showToast(String msg) {

        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
