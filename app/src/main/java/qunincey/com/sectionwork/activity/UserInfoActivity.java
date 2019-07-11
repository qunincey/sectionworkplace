package qunincey.com.sectionwork.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.PickerManager;
import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.bean.User;
import qunincey.com.sectionwork.utils.ImageUtils;

public class UserInfoActivity extends HomeAsUpBaseActivity {

    private int REQUEST_CODE_WRITE_EXTERNAL_STORAGE  = 0x3001;
    private int REQUEST_CODE_CAMERA = 0x3002;
    private int REQUEST_CODE_EDIT_NICKNAME = 0x3003;
    private int REQUEST_CODE_EDIT_SEX = 0x3004;
    private int REQUEST_CODE_EDIT_EMAIL = 0x3005;
    private int REQUEST_CODE_EDIT_INFO = 0x3006;
    private int REQUEST_CODE_TAKEPHOTO= 0x3007;
    private CircleImageView circleImageView;
    private TextView textView_userName;
    private TextView textView_nickname;
    private TextView textView_sex;
    private TextView textView_email;
    private TextView textView_info;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        final TextView titleView = findViewById(R.id.title);
        titleView.setText("个人信息");
        textView_userName = findViewById(R.id.textView_userName);
        textView_nickname = findViewById(R.id.textView_nickname);
        textView_sex = findViewById(R.id.textView_sex);
        textView_email = findViewById(R.id.textView_email);
        textView_info = findViewById(R.id.textView_info);
        circleImageView = findViewById(R.id.circleImageView);

        if (BmobUser.isLogin()) {
            User user = BmobUser.getCurrentUser(User.class);
            textView_userName.setText(user.getUsername());
            textView_nickname.setText(user.getNickName());
            textView_sex.setText(user.isSex()?"男":"女");
            textView_email.setText(user.getEmail());
            textView_info.setText(user.getInfo());
        }

    }

    public void myClick(View view) {
        switch (view.getId()){
            case R.id.linearLayout_head:
                if(checkSelfPermission(Manifest.permission.CAMERA )
                        != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{Manifest.permission.CAMERA}
                            ,REQUEST_CODE_CAMERA);
                }
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE )
                        != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                            ,REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
                }else{
                    takePhoto();
                }
                break;

            case R.id.linearLayout_nickname:
                textEdit("昵称",textView_nickname.getText().toString(),REQUEST_CODE_EDIT_NICKNAME);
                break;
            case R.id.linearLayout_sex:
                textEdit("性别",textView_sex.getText().toString(),REQUEST_CODE_EDIT_SEX);
                break;
            case R.id.linearLayout_email:
                textEdit("邮箱",textView_email.getText().toString(),REQUEST_CODE_EDIT_EMAIL);
                break;
            case R.id.linearLayout_info:
                textEdit("签名",textView_info.getText().toString(),REQUEST_CODE_EDIT_INFO);
                break;
        }
    }

    private void textEdit(String name, String value, int requestCode) {
        Intent intent = new Intent(this,TextEditActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("value",value);
        startActivityForResult(intent,requestCode);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto();
            }
        }
    }


    private void takePhoto() {
        ArrayList<String> filePaths = new ArrayList<>();
        filePaths.add(FilePickerConst.KEY_SELECTED_MEDIA);
        FilePickerBuilder.getInstance().setMaxCount(1)
                .setSelectedFiles(filePaths)
                .setActivityTheme(R.style.LibAppTheme)
                .pickPhoto(this,REQUEST_CODE_TAKEPHOTO);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if(requestCode==REQUEST_CODE_TAKEPHOTO){
                String s = PickerManager.INSTANCE.getSelectedPhotos().get(0);
                ImageUtils.setImage(this,s,circleImageView);

                final BmobFile bmobFile = new BmobFile(new File(s));
                bmobFile.uploadblock(new UploadFileListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            final User user = BmobUser.getCurrentUser(User.class);
                            user.setHeadImag(bmobFile);
                            user.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Snackbar.make(textView_nickname, "更新用户信息成功：" , Snackbar.LENGTH_LONG).show();
                                    } else {
                                        Snackbar.make(textView_nickname, "更新用户信息失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                                        Log.e("error", e.getMessage());
                                    }
                                }
                            });
                        }else{
                        }

                    }

                    @Override
                    public void onProgress(Integer value) {
                        // 返回的上传进度（百分比）
                    }
                });
            }else {
                String value = data.getStringExtra("value");
                final User user = BmobUser.getCurrentUser(User.class);
                if (requestCode == REQUEST_CODE_EDIT_NICKNAME) {
                    textView_nickname.setText(value);
                    user.setNickName(value);
                }
                if (requestCode == REQUEST_CODE_EDIT_SEX) {
                    if(value.equals("男")){
                        textView_sex.setText(value);
                        user.setSex(true);
                    }else {
                        textView_sex.setText("女");
                        user.setSex(false);
                    }
                }
                if (requestCode == REQUEST_CODE_EDIT_EMAIL) {
                    textView_email.setText(value);
                    user.setEmail(value);
                }
                if (requestCode == REQUEST_CODE_EDIT_INFO) {
                    textView_info.setText(value);
                    user.setInfo(value);
                }

                user.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Snackbar.make(textView_nickname, "更新用户信息成功：" , Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(textView_nickname, "更新用户信息失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                            Log.e("error", e.getMessage());
                        }
                    }
                });
            }
        }
    }
}
