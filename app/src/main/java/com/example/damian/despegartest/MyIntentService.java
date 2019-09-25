package com.example.damian.despegartest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyIntentService extends IntentService {

    public static final int DOWNLOAD_SUCCESS = 2;
    public static final int DOWNLOAD_ERROR = 3;

    public MyIntentService() {
        super(MyIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra(Constantes.URL);
        final ResultReceiver receiver = intent.getParcelableExtra(Constantes.RECEIVER);
        Bundle bundle = new Bundle();

        try {
            URL downloadURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) downloadURL
                    .openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            String data = sb.toString();
            br.close();

            is.close();
            conn.disconnect();

            bundle.putString(Constantes.JSONDATA, data);
            receiver.send(DOWNLOAD_SUCCESS, bundle);
        } catch (Exception e) {
            receiver.send(DOWNLOAD_ERROR, Bundle.EMPTY);
            e.printStackTrace();
        }
    }
}
