package com.example.messagingapp;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;

import android.telephony.SmsManager;

import android.telephony.TelephonyManager;
import android.telephony.UiccCardInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    EditText etPhone, etMessage;
    Button btSend, btSend2;
    
    public static final int SIM_STATE_READY = 1;
    public static final int SIM_STATE_NOT_READY=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable
        etPhone = findViewById(R.id.et_phone);
        etMessage = findViewById(R.id.et_message);
        btSend = findViewById(R.id.bt_send);
        btSend2 = findViewById(R.id.bt_send2);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check condition
                if (ContextCompat.checkSelfPermission(MainActivity.this
                        , Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED) {
                    //When permission is granted
                    //create method
                    sendMessage();
                } else {
                    //when permission is not granted
                    //Request permission
                    ActivityCompat.requestPermissions(MainActivity.this
                            , new String[]{Manifest.permission.SEND_SMS}
                            , 100);
                }
            }
        });


        btSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check condition
                if (ContextCompat.checkSelfPermission(MainActivity.this
                        , Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED) {
                    //When permission is granted
                    //create method
                    sendMessage();
                } else {
                    //when permission is not granted
                    //Request permission
                    ActivityCompat.requestPermissions(MainActivity.this
                            , new String[]{Manifest.permission.SEND_SMS}
                            , 100);
                }
            }
        });


    }


    private void sendMessage() {
        //Get values from edit text
        String sPhone = etPhone.getText().toString().trim();
        String sMessage =etMessage.getText().toString().trim();
        //Check condition
        if (!sPhone.equals("") && !sMessage.equals("")) {
            //When both edit text value not equal to blank
            //Initialize sms manager
            SmsManager smsManager =SmsManager.getDefault();
            //send text message
            smsManager.sendTextMessage(sPhone,null,sMessage
                    ,null,null);
            //Display toast
            Toast.makeText(getApplicationContext()
                    ,"SMS sent successfully!",Toast.LENGTH_LONG).show();

        }else {
            //when edit text value is blank
            //Display toast
            Toast.makeText(getApplicationContext()
                    ,"Enter value first.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Check condition
        if(requestCode==100 &&  grantResults.length >0 && grantResults[0]
        ==PackageManager.PERMISSION_GRANTED){
            //when permission is granted
            //call method
            sendMessage();
        }else{

            //when permission is denied
            //display toast
            Toast.makeText(getApplicationContext()
                    ,"PermissionDenied!",Toast.LENGTH_SHORT).show();

        }


    }
   


}
