package com.jackchan.test.okhttptest;

import android.os.Looper;
import android.util.Log;

/**
 * Created by Administrator on 2015/7/22.
 */
public class TestRunnable implements Runnable{
    private final String TAG = "TestRunnable";
    private String name = null;

    public TestRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Log.d(TAG, name + " run");
        Looper.prepare();
        new TestAsyncTask(name).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        Looper.loop();
    }
}
