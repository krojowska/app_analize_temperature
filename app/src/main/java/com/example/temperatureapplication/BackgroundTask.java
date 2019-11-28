package com.example.temperatureapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask <String, Void, String> {
    Context ctx;
    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        String med_url = "http://www.student.agh.edu.pl/~rojowska/insert-medicines.php";

        String method = params[0];
        if(method.equals("saveMedicine")){
            String medicine = params[1];
            String dose = params[2];
            String time_of_day = params[3];
            try {
                URL url =  new URL(med_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("medicine", "UTF-8") + "=" + URLEncoder.encode(medicine, "UTF-8") + "&" +
                        URLEncoder.encode("dose", "UTF-8") + "=" + URLEncoder.encode(dose, "UTF-8") + "&" +
                        URLEncoder.encode("time_of_day", "UTF-8") + "=" + URLEncoder.encode(time_of_day, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Medicine saved successfully";
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Medicine saved successfully"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}
