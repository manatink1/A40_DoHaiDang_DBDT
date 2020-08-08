package Fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a40_day09_dohaidang.R;
import com.example.a40_day09_dohaidang.SQL;
import com.example.a40_day09_dohaidang.databinding.PhoneBookBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Adapter.AdapterPB;
import Contact.ContactPhone;

public class PhoneBook extends Fragment {
    PhoneBookBinding binding;
    AdapterPB adapter;
    ArrayList<ContactPhone> list;
    SQL sql;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.phone_book, container, false);
        if(list == null && sql == null){
            getAllContact();
        }
        adapter.notifyDataSetChanged();
        return binding.getRoot();
    }

    private void getAllContact() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && getContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            getContact();
        }
    }

    private void getContact() {
        list = new ArrayList<>();
        sql = new SQL(getContext());
        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            sql.insertData(new ContactPhone(name, phone));
        }
        list = sql.getData();
        Collections.sort(list, new Comparator<ContactPhone>() {
            @Override
            public int compare(ContactPhone contactPhone1, ContactPhone contactPhone2) {
                return contactPhone1.getName().compareToIgnoreCase(contactPhone2.getName());
            }
        });
        adapter = new AdapterPB(list, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.Phonebook.setAdapter(adapter);
        binding.Phonebook.setLayoutManager(layoutManager);
        cursor.close();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContact();
            }
        }
    }
}
