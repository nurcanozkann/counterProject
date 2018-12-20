package com.example.nurcanzkan.sayac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    int count=0;
    Button btnTikla;
    SharedPreferences preferences;
    SharedPreferences ayarlar;
    LinearLayout linearL;
    Boolean ses_durumu,titresim_durumu;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.sifirla) {
        count=0;
        btnTikla.setText(""+count);
        }
        if(id == R.id.action_setting){
            Intent intent =new Intent(getApplicationContext(),Ayarlar.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
        }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("count_anahtarı",count);
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTikla =findViewById(R.id.btnTikla);
        linearL=findViewById(R.id.linearL);
        preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ayarlar = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ayarlariGoster();
        final MediaPlayer ses = MediaPlayer.create(getApplicationContext(),R.raw.tussesi);
        final Vibrator titresim= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //final AudioManager titresim= (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        count=preferences.getInt("count_anahtarı",0);
        btnTikla.setText(""+count);

        btnTikla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ses_durumu){ses.start();}
                if(titresim_durumu){titresim.vibrate(500);}
                count++;
                btnTikla.setText(""+count);
            }
        });

    }
    private void ayarlariGoster() {
        String colors=ayarlar.getString("arkaplan","3");
        switch (Integer.valueOf(colors)){
            case 0:
                linearL.setBackgroundColor(Color.RED);
                break;
            case 1:
                linearL.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                linearL.setBackgroundColor(Color.BLUE);
                break;
            case 3:
                linearL.setBackgroundColor(Color.DKGRAY);
                break;
            case 4:
                linearL.setBackgroundColor(Color.LTGRAY);
                break;
        }
        ses_durumu=ayarlar.getBoolean("ses",false);
        titresim_durumu=ayarlar.getBoolean("titresim",false);
        ayarlar.registerOnSharedPreferenceChangeListener(MainActivity.this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        ayarlariGoster();
    }
}



