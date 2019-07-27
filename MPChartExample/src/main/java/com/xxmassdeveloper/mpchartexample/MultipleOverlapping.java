package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Random;

public class MultipleOverlapping extends AppCompatActivity {

    private static final Random RAMDOM = new Random();

    private BarChart chart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_overlapping);
        chart = findViewById(R.id.chart);
        initChart();
    }

    private void initChart() {

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        chart.getDescription().setEnabled(false);
        chart.setMinOffset(0);
        chart.disableScroll();

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setGranularity(5);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setYOffset(-10);
        leftAxis.setTextSize(10);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        float groupSpace = 0.5f;
        float barSpace = -0.25f;
        float barWidth = 0.5f;

        int days = 7;
        int startDate = 21; // 21/07/2019
        int endYear = startDate + days;

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();

        for (int i = startDate; i < endYear; i++) {
            values1.add(new BarEntry(i, RAMDOM.nextInt(100)));
            values2.add(new BarEntry(i, RAMDOM.nextInt(100)));
        }

        BarDataSet set1 = new BarDataSet(values1, "Seats");
        set1.setColor(Color.rgb(104, 241, 175));
        BarDataSet set2 = new BarDataSet(values2, "Closed seats");
        set2.setColor(Color.rgb(164, 228, 251));

        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);

        chart.getBarData().setHighlightEnabled(false);

        // specify the width each bar should have
        chart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        chart.getXAxis().setAxisMinimum(startDate);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        chart.getXAxis().setAxisMaximum(startDate + chart.getBarData().getGroupWidth(groupSpace, barSpace) * days);
        chart.groupBars(startDate, groupSpace, barSpace);

        chart.invalidate();
    }
}
