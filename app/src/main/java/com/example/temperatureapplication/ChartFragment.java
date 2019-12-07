package com.example.temperatureapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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

    private BarChart mBarChart;
    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chart, container, false);

        mBarChart = view.findViewById(R.id.barChart);
        getGrowthChart();
        return view;
    }

    private void getGrowthChart() {
        Call<List<Growth>> call = ApiClient.getAPIClient().create(AppInterface.class).getGrowthInfo();
        call.enqueue(new Callback<List<Growth>>() {
            @Override
            public void onResponse(Call<List<Growth>> call, Response<List<Growth>> response) {
                if(response.body()!=null){

                        List<BarEntry> barEntries = new ArrayList<>();
                        for (Growth growth : response.body()) {
                            barEntries.add(new BarEntry(growth.getId(), growth.getTemperature()));
                        }

                        BarDataSet barDataSet = new BarDataSet(barEntries, "Growth");
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);
                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(5000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description = new Description();
                        description.setText("Temperature Chart");
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();

                }
            }

            @Override
            public void onFailure(Call<List<Growth>> call, Throwable t) {

            }
        });
    }

}
