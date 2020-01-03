package com.example.temperatureapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

    /**
     * Async task class to get json by making HTTP call
     */
    public class GetAlarmTemperature extends AsyncTask<Void, Void, Void> {

        private String TAG = GetAlarmTemperature.class.getSimpleName();
        public static String alarmTemp;
        private ProgressDialog pDialog;
        private ListView lv;

        private static String url = "http://www.student.agh.edu.pl/~rojowska/get-sound.php";

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                  //  JSONArray temperatures = jsonObj.getJSONArray("alarm_temperature");


                    alarmTemp = jsonObj.getString("alarm_temperature");

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }

