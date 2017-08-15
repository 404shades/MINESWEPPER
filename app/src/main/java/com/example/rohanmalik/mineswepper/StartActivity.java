package com.example.rohanmalik.mineswepper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        super.setTitle("COoLL");
        et=(EditText)findViewById(R.id.User);
        tv=(TextView)findViewById(R.id.textView);

        Button btn = (Button)findViewById(R.id.launch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(StartActivity.this,"Enter Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i =new Intent(StartActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
