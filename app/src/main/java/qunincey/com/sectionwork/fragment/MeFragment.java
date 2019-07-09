package qunincey.com.sectionwork.fragment;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.activity.LoginActivity;

public class MeFragment extends BaseFragment {

    private boolean isLogin;
    private int REQUEST_CODE1;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbarLayout);
        CircleImageView circleImageView = view.findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin){

                }else{
                    Intent intent =  new Intent(activity, LoginActivity.class);
                    startActivityForResult(intent,REQUEST_CODE1);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE1)
            if(data!=null){
                boolean isLogin = data.getBooleanExtra("isLogin",false);
                MeFragment.this.isLogin=isLogin;
                String userName = data.getStringExtra("userName");
                collapsingToolbarLayout.setTitle(userName);
            }
    }
}
