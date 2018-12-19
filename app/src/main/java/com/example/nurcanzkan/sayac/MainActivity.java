package com.example.nurcanzkan.sayac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int count=0;
    Button btnTikla;
    SharedPreferences preferences;

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
        preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        count=preferences.getInt("count_anahtarı",0);
        btnTikla.setText(""+count);

        btnTikla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                btnTikla.setText(""+count);

            }
        });


    }
}



