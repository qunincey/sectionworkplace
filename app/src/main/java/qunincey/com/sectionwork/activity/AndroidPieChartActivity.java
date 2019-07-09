package qunincey.com.sectionwork.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import qunincey.com.sectionwork.R;

public class AndroidPieChartActivity extends HomeAsUpBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_pie);
        final TextView titleView = findViewById(R.id.title);
        titleView.setText("柱状图");

        final PieChart chart = findViewById(R.id.chart);


        final String[] years = new String[]{"应届生","1-2年","2-3年","3-5年","5-8年","8-10年","10年"};
        int[] salaries ={6000,13000,20000,26000,35000,5000,100000};

//        YourData[] dataObjects = ...;
        List<PieEntry> entries = new ArrayList<PieEntry>();
        for (int i=0;i<salaries.length;i++) {
            // turn your data into Entry objects
            entries.add(new PieEntry(salaries[i]));
        }
        PieDataSet dataSet = new PieDataSet(entries, "占比情况"); // add entries to dataset
        int[] colors ={Color.RED,Color.BLUE,Color.GREEN,Color.BLACK,Color.LTGRAY,Color.YELLOW,Color.CYAN};
        dataSet.setColors(colors);

        dataSet.setValueTextColor(Color.WHITE); // styling, ...
        dataSet.setValueTextSize(14f);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value+"%";
            }
        });
        PieData piedata = new PieData(dataSet);
        chart.setData(piedata);

        Legend legend = chart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        Description description=new Description();
        description.setText("PHP工程师工作经验对应薪资");
        description.setTextSize(16f);
        description.setPosition(270f,50f);
        description.setTextAlign(Paint.Align.CENTER);

        chart.setExtraTopOffset(10f);
        chart.setDescription(description);
        chart.animateY(5000);
        chart.animateX(5000);
        chart.setCenterTextColor(Color.BLACK);
        chart.setCenterText("点击圆环\n显示数据");
        chart.setCenterTextSize(16f);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry pieEntry = (PieEntry)e;
                chart.setCenterText(pieEntry.getLabel()+"\n"+pieEntry.getValue()+"%");
            }

            @Override
            public void onNothingSelected() {
                //todo
                chart.setCenterText("点击圆环\n显示数据");
            }
        });
        chart.invalidate(); // refresh
    }
}
