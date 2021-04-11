package nmk.com.tr.dinle;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class WebActivity extends AppCompatActivity {

    private static final String TAG ="WebActivity";
    TextView tvResult;
    WebView wvSearch;
    ImageView speak;
    Button ivGec;
    Button telefon;

    Boolean girdi = false;

    private WebViewClient mViewClient = new WebViewClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        tvResult = (TextView)findViewById(R.id.tvResult);
        wvSearch = (WebView)findViewById(R.id.wvsearch);
        speak = (ImageView) findViewById(R.id.btnSpeak);
    }


    public void getSpeechInputt(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
            Log.w(TAG,"1. if e girdi");
        }else{
            Toast.makeText(this, "olmadi..." , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        WebView webview = (WebView) findViewById(R.id.wvsearch);
        switch (requestCode){
            case 10:
                Log.w(TAG,"case e girdi");
                if (resultCode == RESULT_OK && data != null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvResult.setText(result.get(0)); //text degisti
                    Log.w(TAG,"text degisti");


                     // eger telefondan bir sey actiysa netten aramasin diye..
                        webview.getSettings().setJavaScriptEnabled(true);
                        webview.setWebViewClient(mViewClient); // ayni activtyde acilmasi icin ekledim.
                        webview.loadUrl("https://www.google.com/#q=" + result.get(0));


                    /*tvResult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //https://www.google.com/#q=
                            //Toast.makeText(MainActivity.this, tvResult.getText(), Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=" + tvResult.getText())));
                            String url = "https://www.google.com/#q=" + tvResult.getText();
                            wvSearch.getSettings().setBuiltInZoomControls(true);
                            wvSearch.loadUrl(url);
                        }
                    });*/


                } else Log.w(TAG,"hataaaa");
                break;


        }
    }
}
