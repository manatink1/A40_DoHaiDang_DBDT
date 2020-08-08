package com.example.a40_day09_dohaidang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.a40_day09_dohaidang.databinding.ActivityMainBinding;

import Fragment.Add;
import Fragment.Call;
import Fragment.PhoneBook;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.Container,new PhoneBook()).commit();
        binding.Group.setBackgroundColor(Color.parseColor("#1B1A1A"));
        click();
    }

    private void click(){
        binding.Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear2.setBackgroundColor(Color.parseColor("#1B1A1A"));
                binding.Add.setBackgroundColor(Color.parseColor("#99302F2F"));
                binding.Group.setBackgroundColor(Color.parseColor("#99302F2F"));
                binding.Call.setBackgroundColor(Color.parseColor("#1B1A1A"));
                getSupportFragmentManager().beginTransaction().replace(R.id.Container,new Call()).commit();
            }
        });
        binding.Group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear2.setBackgroundColor(Color.parseColor("#1B1A1A"));
                binding.Call.setBackgroundColor(Color.parseColor("#99302F2F"));
                binding.Add.setBackgroundColor(Color.parseColor("#99302F2F"));
                binding.Group.setBackgroundColor(Color.parseColor("#1B1A1A"));
                getSupportFragmentManager().beginTransaction().replace(R.id.Container,new PhoneBook()).commit();
            }
        });
        binding.Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear2.setBackgroundColor(Color.parseColor("#1B1A1A"));
                binding.Call.setBackgroundColor(Color.parseColor("#99302F2F"));
                binding.Group.setBackgroundColor(Color.parseColor("#99302F2F"));
                binding.Add.setBackgroundColor(Color.parseColor("#1B1A1A"));
                getSupportFragmentManager().beginTransaction().replace(R.id.Container,new Add()).commit();
            }
        });
    }
}