package qunincey.com.sectionwork.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import qunincey.com.sectionwork.R;

public class PHPBarChartActivity extends HomeAsUpBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_chart);
        final TextView titleView = findViewById(R.id.title);
        titleView.setText("PHP统计");

        BarChart chart = findViewById(R.id.chart);


        final String[] years = new String[]{"应届生","1-2年","2-3年","3-5年","5-8年","8-10年","10年"};
        int[] salaries ={6000,13000,20000,26000,35000,5000,100000};

//        YourData[] dataObjects = ...;
        List<BarEntry> entries = new ArrayList<BarEntry>();
        for (int i=0;i<salaries.length;i++) {
            // turn your data into Entry objects
            entries.add(new BarEntry(i,salaries[i]));
        }
        BarDataSet dataSet = new BarDataSet(entries, "工资"); // add entries to dataset
        BarData barData = new BarData(dataSet);
        int[] colors ={Color.RED,Color.BLUE,Color.GREEN,Color.BLACK,Color.LTGRAY,Color.YELLOW,Color.CYAN};
        dataSet.setColors(colors);
        List<Integer> color_list = new ArrayList<>();
        for(int color:colors){
            color_list.add(color);
        }
        dataSet.setValueTextColors(color_list); // styling, ...
        dataSet.setValueTextSize(14f);

        chart.setData(barData);
        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisLineWidth(2f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.enableGridDashedLine(10f,10f,0f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return years[((int)value)];
            }
        });
        chart.getAxisRight().setEnabled(false);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisLineWidth(2f);
        yAxis.enableGridDashedLine(10f,10f,0f);
        yAxis.setSpaceTop(38.2f);
        yAxis.setAxisMinimum(0);

        LimitLine limitLine = new LimitLine(13000,"平均工资");
        limitLine.setLineColor(Color.MAGENTA);
        limitLine.setLineWidth(4f);
        yAxis.addLimitLine(limitLine);
        Legend legend = chart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        Description description=new Description();
        description.setText("PHP工程师工作经验对应薪资");
        description.setTextSize(16f);
        description.setPosition(270f,50f);
        description.setTextAlign(Paint.Align.CENTER);
        chart.setDescription(description);
        chart.animateY(5000);
        chart.invalidate(); // refresh
    }
}
