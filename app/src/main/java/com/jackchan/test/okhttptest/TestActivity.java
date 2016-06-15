package com.jackchan.test.okhttptest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TestActivity extends Activity {

    private final static String TAG = "TestActivity";

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        DownloadStrategyPool pool = new DownloadStrategyPool();
        for(int i = 0; i < 9; i++){
            pool.execute(new TestRunnable(i+""));
        }
    }

    class DownloadStrategyPool extends ThreadPoolExecutor {

        public DownloadStrategyPool() {
            super(3, 3, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }
    }


    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "onFailure");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                int code = response.code();
                String msg = response.message();
                String body = response.body().string();
                Response cacheResponse = response.cacheResponse();
                Response networkResponse = response.networkResponse();
                Log.d(TAG, "onResponse");
            }
        });
//        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//        Headers responseHeaders = response.headers();
//        for (int i = 0; i < responseHeaders.size(); i++) {
//            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//        }
//
//        System.out.println(response.body().string());
    }
}
