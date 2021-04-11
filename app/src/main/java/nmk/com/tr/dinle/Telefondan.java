package nmk.com.tr.dinle;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Telefondan extends AppCompatActivity {

    private static final String TAG = "TelefondanActivity";
    ImageView telefondanAra;
    TextView tvResult;
    Boolean girdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefondan);
        telefondanAra = (ImageView) findViewById(R.id.imageView);
        tvResult = (TextView) findViewById(R.id.textView);
    }

    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
            Log.w(TAG, "1. if e girdi");
        } else {
            Toast.makeText(this, "olmadi...", Toast.LENGTH_SHORT).show();
        }
    }

    public void rehbereGit() {
        //String veriAl = tvResult.getText().toString();
        //Log.w(TAG, "verial" + veriAl); // veri alabiliyor
        //if (veriAl.equals("rehber") || veriAl.equals("Rehbere git") || veriAl.equals("Rehberi aç")) {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType(ContactsContract.Contacts.CONTENT_TYPE);
            startActivity(i);
       // }
    }

    public void galeriyeGit() {
        String veriAl = tvResult.getText().toString();
        Log.w(TAG, "verial" + veriAl); // veri alabiliyor
        if (veriAl.equals("galeri") || veriAl.equals("Galeriye git") || veriAl.equals("Galeriyi aç")
                || veriAl.equals("galeriye git") || veriAl.equals("galeriyi aç")) {
            girdi = true;
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            startActivity(i);
        }
        //girdi = false;
    }

    public void kamerayaGit() {
        //String veriAl = tvResult.getText().toString();
        //Log.w(TAG, "verial" + veriAl); // veri alabiliyor
        //if (veriAl.equals("kamera") || veriAl.equals("Kameraya git") || veriAl.equals("Kamerayı aç")) {
            girdi = true;
            Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
            i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(i);
        //}
        //girdi = false;
    }

    public void ayarlaraGit() {
        String veriAl = tvResult.getText().toString();
        Log.w(TAG, "verial" + veriAl); // veri alabiliyor
        if (veriAl.equals("ayarlar") || veriAl.equals("Ayarlara git") || veriAl.equals("Ayarları aç")) {
            girdi = true;
            Intent i = new Intent(android.provider.Settings.ACTION_SETTINGS);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        //girdi = false;


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                Log.w(TAG, "case e girdi");
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvResult.setText(result.get(0)); //text degisti
                    Log.w(TAG, "text degisti");

                    if (result.get(0).equals("kamera") || result.get(0).equals("kameraya git") || result.get(0).equals("Kamerayı aç")
                            || result.get(0).equals("Kameraya git") || result.get(0).equals("kamerayı aç") ) {
                        kamerayaGit();
                    }
                    if (result.get(0).equals("galeri") || result.get(0).equals("Galeriye git") || result.get(0).equals("galeriye git")
                            || result.get(0).equals("galeriyi aç") || result.get(0).equals("Galeriyi aç") ) {
                        galeriyeGit();
                    }
                    if (result.get(0).equals("rehber") || result.get(0).equals("rehberi aç") || result.get(0).equals("rehbere git")
                            || result.get(0).equals("Rehberi aç") || result.get(0).equals("Rehbere git") ) {
                        rehbereGit();
                    }
                    if (result.get(0).equals("ayarlar") || result.get(0).equals("ayarları aç") ||  result.get(0).equals("ayarlara git") ||
                            result.get(0).equals("Ayarları aç") || result.get(0).equals("Ayarlara git") ) {
                        ayarlaraGit();
                    }
                    if(result.get(0).equals("Arama yapmak istiyorum") || result.get(0).equals("arama yapmak istiyorum") ||
                            result.get(0).equals("ara") || result.get(0).equals("Ara") ){
                        Intent iArama = new Intent(Telefondan.this,AramaYap.class);
                        startActivity(iArama);
                    }
                    if(result.get(0).equals("Mesaj göndermek istiyorum") || result.get(0).equals("mesaj göndermek istiyorum") ||
                            result.get(0).equals("mesaj") || result.get(0).equals("Mesaj")){
                        Intent iMesaj = new Intent(Telefondan.this,MesajGonder.class);
                        startActivity(iMesaj);
                    }



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


                } else Log.w(TAG, "hataaaa");
                break;

        }
    }
}