package nmk.com.tr.dinle;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
    TextView tvResult;
    Button web;
    Button telefon;
    LinearLayout linearLayoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView)findViewById(R.id.tvResult);
        telefon = (Button) findViewById(R.id.btnTel);
        web = (Button) findViewById(R.id.btnweb);
        linearLayoutMain = (LinearLayout) findViewById(R.id.lineBGColor) ;
        linearLayoutMain.setBackgroundColor(Color.green(200));

        telefon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Telefondan.class);
                startActivity(i);
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });
    }


}
