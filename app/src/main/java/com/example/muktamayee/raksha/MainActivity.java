package com.example.muktamayee.raksha;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer mediaPlayer;
    GPSTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show sign up activity
            startActivity(new Intent(MainActivity.this, signup.class));
            //Toast.makeText(MainActivity.this, "Run only once", Toast.LENGTH_LONG)
                //    .show();
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();
        Button help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsTracker = new GPSTracker(getApplicationContext());
                if(gpsTracker.canGetLocation()){
                    double lat = gpsTracker.getLatitude();
                    double lon = gpsTracker.getLongitude();
                    String url =  "https://www.google.com/maps/search/?api=1&query="+lat +","+lon;
                    //Toast.makeText(getApplicationContext(),"lcation is " + lat + lon,Toast.LENGTH_LONG).show();
                    send(url);

                }

                else {
//                    gpsTracker.showSettingsAlert();
                }
                /*
                if(mediaPlayer==null)
                {
                    mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.police);
                }
                //  mediaPlayer.start();
                mediaPlayer.setVolume(1,1);
                AudioManager a=(AudioManager)getSystemService(AUDIO_SERVICE);
                //int i=a.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                a.setStreamVolume(AudioManager.STREAM_MUSIC,90,0);
                //send();*/
            }
        });

    }


    public void send(String s) {
        data d = new data(getApplicationContext());
        List<String> contactList1 = new ArrayList<String>();
        contactList1 = d.getAllContacts();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            for (int i = 0; i < contactList1.size(); i++) {
                smsManager.sendTextMessage(contactList1.get(i), null, "HELP ME/n" +s , null, null);
            }
        }catch(Exception e)
        { e.printStackTrace();}
       // Toast toast=Toast.makeText(getApplicationContext(),"message sent",Toast.LENGTH_LONG);
       // toast.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.Emergency_PhoneNumbers) {
            Intent i = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.Nearby_police) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=police station");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
            return true;
        }
        if (id == R.id.Nearby_hos) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospital");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
