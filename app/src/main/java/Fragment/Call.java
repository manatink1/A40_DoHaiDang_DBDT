package Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.a40_day09_dohaidang.R;
import com.example.a40_day09_dohaidang.databinding.CallBinding;

public class Call extends Fragment {
    CallBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.call,container,false);
        inputNumber();
        deleteNumber();
        call();
        return binding.getRoot();
    }
    public void call(){
        binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.Phonenumber.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "You must enter a number", Toast.LENGTH_SHORT).show();
                }else{
                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.CALL_PHONE},1);
                    }else{
                        String s = "tel:" +binding.Phonenumber.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(s));
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private void inputNumber(){
        binding.btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "0");
            }
        });
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "1");
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "2");
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "3");
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "4");
            }
        });
        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "5");
            }
        });
        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "6");
            }
        });
        binding.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "7");
            }
        });
        binding.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "8");
            }
        });
        binding.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "9");
            }
        });
        binding.btnSpecial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "*");
            }
        });
        binding.btnSpecial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.Phonenumber.setText(binding.Phonenumber.getText().toString() + "#");
            }
        });
    }
    private void deleteNumber(){
        if(binding.Phonenumber.getText().toString() != null){
            binding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.Phonenumber.setText(deleteOneNum(binding.Phonenumber.getText().toString()));

                }
            });
        }else{
            binding.Phonenumber.setHint("Enter a number");
        }

    }
    private String deleteOneNum(String number){
        int length = number.length();
        String del = null;
        if(length > 0){
            del =number.substring(0,length-1);
        }
        return del;
    }
}
