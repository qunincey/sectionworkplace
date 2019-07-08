package qunincey.com.sectionwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.activity.AndroidPieChartActivity;
import qunincey.com.sectionwork.activity.JavaLineChartActivity;
import qunincey.com.sectionwork.activity.PHPBarChartActivity;

public class ChartFragment extends BaseFragment {

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView title = view.findViewById(R.id.title);
        title.setText("图表");
        BoomMenuButton bmb = view.findViewById(R.id.bmb);

        int[] images_id = {R.drawable.bat,R.drawable.bear,R.drawable.bee,R.drawable.butterfly,
        R.drawable.cat,R.drawable.dolphin,R.drawable.eagle,R.drawable.horse,
        R.drawable.elephant};

        String[] text = {
                "Android","Java","PHP","黑马程序员,Python","黑马程序员.C/C++","黑马程序员.ios",
                "黑马程序员.前端与移动开发","黑马程序员.UI设计","黑马程序员.网络营销"
        };

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalImageRes(images_id[i])
                    .normalText(text[i])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index){
                                case 0:
                                    Intent intent0 = new Intent(activity, AndroidPieChartActivity.class);
                                    startActivity(intent0);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(activity, JavaLineChartActivity.class);
                                    startActivity(intent1);
                                    break;
                                case 2:
                                    Intent intent2 = new Intent(activity, PHPBarChartActivity.class);
                                    startActivity(intent2);
                                    break;
                                case 3:
//                                    Intent intent3 = new Intent(activity,AndroidPieChartActivty.class);
//                                    startActivity(intent3);
                                    break;
                                case 4:
//                                    Intent intent4 = new Intent(activity,AndroidPieChartActivty.class);
//                                    startActivity(intent4);
                                    break;
                            }

                        }
                    });
            bmb.addBuilder(builder);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
