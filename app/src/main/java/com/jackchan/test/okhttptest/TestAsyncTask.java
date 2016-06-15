package com.jackchan.test.okhttptest;

import android.util.Log;

import java.util.Random;

/**
 * Created by Administrator on 2015/7/22.
 */
public class TestAsyncTask extends AsyncTask<Void, Void, Void>{

    private String TAG = "TestAsyncTask";

    private String name = null;

    public TestAsyncTask(String name) {
        this.name = name;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Random random = new Random();
            int sleep = random.nextInt(10);
            Log.d(TAG, name + " sleep " + sleep + " seconds");
            Thread.sleep(sleep*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG, name + " onPostExecute");
    }
}
