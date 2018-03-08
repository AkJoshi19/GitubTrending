package com.gitTrending.main.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;

import com.gitTrending.main.R;


/**
 * Created by anjanik on 3/8/2018.
 */

public class CustomProgressDialog extends ProgressDialog {

    /**
     * declare java objects
     */
    public static CustomProgressDialog msCustomProgressDialog;

    public CustomProgressDialog(Context context) {
        super(context);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_custom_progreebar);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setDimAmount(0.20f);

    }


    /**
     * generte singletone for custom progress dialog
     *
     * @param mContext
     * @return
     */
    public static CustomProgressDialog getProgressDialog(Context mContext) {

        if (msCustomProgressDialog == null) {
            msCustomProgressDialog = new CustomProgressDialog(mContext, R.style.AppCompatAlertDialogStyle);
        }

        return msCustomProgressDialog;
    }

    /**
     * show dialog if not showing
     */
    public void showDialog1() {

        try {
            if (msCustomProgressDialog.isShowing()) {
                msCustomProgressDialog.dismiss();
            }
            msCustomProgressDialog.show();
            msCustomProgressDialog.setCancelable(false);
        }catch (Exception ex){
            msCustomProgressDialog = null;
            ex.printStackTrace();
        }
    }

    /**
     * hide dialog if not showing
     */
    public void dismissDialog1() {
        try {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    if (msCustomProgressDialog != null && msCustomProgressDialog.isShowing()) {
                        msCustomProgressDialog.dismiss();
                        msCustomProgressDialog = null;
                    }
                }catch (Exception ex){
                    msCustomProgressDialog = null;
                    ex.printStackTrace();
                }
            }
        },500);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
