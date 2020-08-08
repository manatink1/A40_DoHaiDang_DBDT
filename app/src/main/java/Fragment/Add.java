package Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.a40_day09_dohaidang.MainActivity;
import com.example.a40_day09_dohaidang.R;
import com.example.a40_day09_dohaidang.SQL;
import com.example.a40_day09_dohaidang.databinding.AddContactBinding;

import java.util.ArrayList;

import Contact.ContactPhone;

public class Add extends Fragment {
    AddContactBinding binding;
    SQL sql;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_contact, container, false);
        sql = new SQL(getContext());
        clickButton();
        return binding.getRoot();
    }

    private void clickButton() {
        binding.btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] str = {"Family ", "Company ", "Friend ", "Lover ", "Neighbor"};
                final boolean[] check = {false, false, true, false, true};
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("Select Group")
                        .setMultiChoiceItems(str, check, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            }
                        }).setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("Are you sure?")
                                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                String string = "";
                                                for (i = 0; i < str.length; i++) {
                                                    if (check[i] == true)
                                                        string += str[i];
                                                }
                                                Toast.makeText(getContext(), "Your choices: " + string, Toast.LENGTH_SHORT).show();
                                            }
                                        }).setPositiveButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        }).show();
                            }
                        }).show();
            }
        });

        binding.btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] str = {"Its you","Dusk till dawn","Take it off","Walk thru fire","Love is gone","Waiting","Here with you"};
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("Select Tune")
                        .setSingleChoiceItems(str, 2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
        binding.btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edName.getText().toString();
                String phone = binding.edPhoneNumber.getText().toString();
                String email = binding.edEmail.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(getContext(), "The Name is not null", Toast.LENGTH_SHORT).show();
                }else if(phone.isEmpty()){
                    Toast.makeText(getContext(), "Phone Number is not null", Toast.LENGTH_SHORT).show();
                }else if(email.isEmpty()){
                    Toast.makeText(getContext(), "Email is not null", Toast.LENGTH_SHORT).show();
                }else{
                    sql.insertData(new ContactPhone(name,phone));
                    Toast.makeText(getContext(), "Add new contact succeed", Toast.LENGTH_SHORT).show();
                    binding.edName.setText("");
                    binding.edPhoneNumber.setText("");
                    binding.edEmail.setText("");
                }
            }
        });
    }
}
