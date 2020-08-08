package com.example.a40_day09_dohaidang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.example.a40_day09_dohaidang.databinding.ActivitySendMessageBinding;

public class SendMessage extends AppCompatActivity {
    ActivitySendMessageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_send_message);
        phoneCall();
        ActivityCompat.requestPermissions(SendMessage.this,new String[] {Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        sendMessage();
        back();
        find();
        resolveInformation();
    }
    private void find(){
        binding.Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SendMessage.this, "Chức năng này không có gì cả, chức năng Add ở cạnh chỗ tin nhắn cũng thế", Toast.LENGTH_SHORT).show();
            }
        });
        binding.addMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SendMessage.this, "Chức năng này không có gì cả, chức năng Find ở cạnh chỗ Call cũng thế", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void phoneCall(){
        binding.PhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SendMessage.this,new String[] {Manifest.permission.CALL_PHONE},1);
                }else{
                    String s = "tel:" +binding.PhoneNumber.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });
    }
    private void sendMessage(){
        binding.Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = binding.PhoneNumber.getText().toString();
                String message = binding.Message.getText().toString();
                binding.ShowMessage.setText(binding.ShowMessage.getText().toString() + "\n" +message);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, message, null, null);
                binding.Message.setText("");

            }
        });
    }
    private void back(){
        binding.Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
    private void resolveInformation(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phonenumber = intent.getStringExtra("phone");
        if(binding.Name.getText().toString().isEmpty()){
            binding.PhoneNumber.setVisibility(View.VISIBLE);
            binding.PhoneNumber.setText(phonenumber);
        }else
            binding.PhoneNumber.setVisibility(View.GONE);
            binding.Name.setText(name);
    }
}