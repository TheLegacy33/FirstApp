package net.devatom.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        String message = getIntent().getStringExtra("msg");
        TextView label = (TextView) findViewById(R.id.lblMessage);
        label.setText(message);

        ((Button) findViewById(R.id.btClose)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
