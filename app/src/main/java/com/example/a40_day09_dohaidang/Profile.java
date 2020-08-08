package com.example.a40_day09_dohaidang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a40_day09_dohaidang.databinding.ActivityProfileBinding;

import java.util.ArrayList;

import Contact.ContactPhone;

public class Profile extends AppCompatActivity {
    ActivityProfileBinding binding;
    SQL sql;
    String name;
    String phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);

        sql = new SQL(getBaseContext());
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        phonenumber = intent.getStringExtra("phone");
        binding.edName.setText(name);
        binding.edPhoneNumber.setText(phonenumber);
        back();
        updateAndDelete();
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
    private void updateAndDelete(){
        binding.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edName.getText().toString() != null && binding.edPhoneNumber.getText().toString() != null && binding.edPhoneNumber.getText().toString().equalsIgnoreCase(phonenumber)){
                    String Name = binding.edName.getText().toString();
                    String Phone = binding.edPhoneNumber.getText().toString();
                    sql.updateData(new ContactPhone(Name,Phone),phonenumber);
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Profile.this, "Name and PhoneNumber are not null", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edPhoneNumber.getText().toString().equalsIgnoreCase(phonenumber)){
                    sql.deleteData(phonenumber);
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Profile.this, "Can't be deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}