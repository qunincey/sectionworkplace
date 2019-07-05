package qunincey.com.sectionwork.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import qunincey.com.sectionwork.R;

public class JavaLineChartActivity extends HomeAsUpBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_line);

        final TextView textView = findViewById(R.id.title);
        textView.setText("java统计");

        LineChart chart = (LineChart) findViewById(R.id.chart);

        final String[] years = {"应届生","1-2年","2-3年","3-5年","5-8年","8-10年","10年"};
        int[] salaries = {6000,13000,20000,26000,35000,50000,100000};

//        YourData[] dataObjects = ...;
        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i< salaries.length;i++) {
            // turn your data into Entry objects
            entries.add(new Entry(i,salaries[i]));
        }

        LineDataSet dataSet = new LineDataSet(entries,"工资");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED); // styling, ...
        dataSet.setValueTextSize(14f);
        dataSet.setLineWidth(6f);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineWidth(2f);
        xAxis.enableGridDashedLine(10f,10f,0f);

        xAxis.setValueFormatter(new ValueFormatter() {

            @Override
            public String getFormattedValue(float value) {
                return years[(int)value];
            }
        });
        chart.getAxisRight().setEnabled(false);



        YAxis yAxis=chart.getAxisLeft();
        yAxis.setAxisLineWidth(2f);
        yAxis.enableGridDashedLine(10f,10f,0f);
        yAxis.setSpaceTop(20f);

        LimitLine limitLine = new LimitLine(15000f,"平均工资");
        limitLine.setLineColor(Color.MAGENTA);
        limitLine.setLineWidth(2f);
        yAxis.addLimitLine(limitLine);

        Description descriptio = chart.getDescription();
        descriptio.setText("JAVA工程师薪资情况");
        descriptio.setTextSize(16f);
        descriptio.setPosition(540,100);
        descriptio.setTextAlign(Paint.Align.CENTER);

        Legend legend=chart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);



        chart.setDescription(descriptio);
        chart.animateX(5000);
        chart.invalidate(); // refresh


    }
}
