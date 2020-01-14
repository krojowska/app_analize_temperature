package com.example.temperatureapplication;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = "http://www.student.agh.edu.pl/~rojowska/test.php";

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray temperatures = jsonObj.getJSONArray("SensorData");

                    for (int i = 0; i < temperatures.length(); i++) {
                        JSONObject c = temperatures.getJSONObject(i);

                        String id = c.getString("chipId");
                        String temperature = c.getString("sensorsTemperature");
                        String time = c.getString("reading_time");


                        HashMap<String, String> temp = new HashMap<>();

                        temp.put("chipId", id);
                        temp.put("sensorsTemperature", temperature);
                        temp.put("reading_time", time);

                        contactList.add(temp);
                        String alarmTempStr = GetAlarmTemperature.alarmTemp;
                        float alarmTemp = Float.parseFloat(alarmTempStr);
                        float tempFromDB = Float.parseFloat(temperature);
                        if (alarmTemp <= tempFromDB) {
//                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//                            r.play();
                            final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.song);
                            mp.start();
                        }
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
                ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.sensor_item, new String[]{"sensorsTemperature", "reading_time",
                    "chipId"}, new int[]{R.id.sensorsTemperature,
                    R.id.reading_time, R.id.chipId});

            lv.setAdapter(adapter);
        }

    }
}