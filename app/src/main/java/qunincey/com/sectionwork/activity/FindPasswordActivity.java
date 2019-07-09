package qunincey.com.sectionwork.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import qunincey.com.sectionwork.R;

public class FindPasswordActivity  extends HomeAsUpBaseActivity{

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_find_password);

        TextView textView = findViewById(R.id.title);
        textView.setText("找回密码");
        editText = findViewById(R.id.editText);
    }

    public void register(final View view){

        final String email = editText.getText().toString();
        if (TextUtils.isEmpty(email)){
            editText.setError("email不能为null");
            return;
        }
        BmobUser.resetPasswordByEmail(email, new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if (e==null){
                    Snackbar.make(view, "重置密码请求成功，请到："+email + "邮箱进行密码重置操作" , Snackbar.LENGTH_LONG).show();
                }else {
                    Log.e("BMOB",e.toString());
                    Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_LONG).show();
                }

            }
        });

    }

}
