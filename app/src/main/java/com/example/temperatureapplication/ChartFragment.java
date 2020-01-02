package com.example.temperatureapplication;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private LineChart lineChart;
    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chart, container, false);

        lineChart = view.findViewById(R.id.lineChart);
        getGrowthChart();
        return view;
    }

    private void getGrowthChart() {
        Call<List<Growth>> call = ApiClient.getAPIClient().create(AppInterface.class).getGrowthInfo();
        call.enqueue(new Callback<List<Growth>>() {
            @Override
            public void onResponse(Call<List<Growth>> call, Response<List<Growth>> response) {
                if(response.body()!=null){

                        List<Entry> barEntries = new ArrayList<>();
                        for (Growth growth : response.body()) {
                            barEntries.add(new Entry(growth.getId(), growth.getTemperature()));
                        }

                        LineDataSet lineDataSet = new LineDataSet(barEntries, "Temperature");
                         lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    LineData  barData = new LineData(lineDataSet);
                       // barData.setBarWidth(0.9f);
                    lineChart.setVisibility(View.VISIBLE);
                    //lineChart.animateY(2500);
                    barData.setValueTextColor(Color.GREEN);
                    barData.setValueTextSize(10f);
                    lineChart.setData(barData);
                    lineChart.setNoDataTextColor(Color.WHITE);
                    lineChart.getXAxis().setTextColor(Color.WHITE);
                    lineChart.getAxisLeft().setTextColor(Color.WHITE);
                    lineChart.getAxisRight().setTextColor(Color.WHITE);
                    lineChart.getLegend().setTextColor(Color.WHITE);
                    //lineChart.setFitBars(true);
                    lineChart.getDescription().setEnabled(false);
                    lineChart.invalidate();

                }
            }

            @Override
            public void onFailure(Call<List<Growth>> call, Throwable t) {

            }
        });
    }

}
