package qunincey.com.sectionwork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import qunincey.com.sectionwork.R;


public class TextEditActivity extends HomeAsUpBaseActivity {

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_edit);
        final TextView titleView = findViewById(R.id.title);
        titleView.setText("修改");
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String value = intent.getStringExtra("value");
        TextView textView = findViewById(R.id.textView);
        textView.setText(name);
        editText=findViewById(R.id.editText);
        editText.setText(value);

    }

    public void save(View view){
        String value = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("value",value);
        setResult(RESULT_OK,intent);
        finish();
    }
}
