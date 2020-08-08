package Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a40_day09_dohaidang.Profile;
import com.example.a40_day09_dohaidang.R;
import com.example.a40_day09_dohaidang.SendMessage;

import java.util.ArrayList;

import Contact.ContactPhone;

public class AdapterPB extends RecyclerView.Adapter<AdapterPB.ViewHolder> {
    ArrayList<ContactPhone> list;
    Context context;

    public AdapterPB(ArrayList<ContactPhone> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPB.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterPB.ViewHolder holder, final int position) {
        final ContactPhone cp = list.get(position);
        holder.Name.setText(cp.getName());
        holder.Phone.setText(cp.getPhoneNumber());
        holder.photo.setVisibility(View.VISIBLE);
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.linear2.GONE == holder.linear2.getVisibility()) {
                    holder.linear2.setVisibility(View.VISIBLE);
                } else {
                    holder.linear2.setVisibility(View.GONE);
                }
            }
        });
        holder.PhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "tel:" + holder.Phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(s));
                context.startActivity(intent);
            }
        });
        holder.Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SendMessage.class);
                intent.putExtra("name",holder.Name.getText().toString());
                intent.putExtra("phone",holder.Phone.getText().toString());
                context.startActivity(intent);
            }
        });
        holder.Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Profile.class);
                intent.putExtra("name",holder.Name.getText().toString());
                intent.putExtra("phone",holder.Phone.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Phone, PhoneCall, Chat, Profile;
        ImageView photo, call;
        LinearLayout linear, linear2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Phone = itemView.findViewById(R.id.Phonenumber);
            photo = itemView.findViewById(R.id.photo);
            call = itemView.findViewById(R.id.Call);
            linear = itemView.findViewById(R.id.Linear1);
            linear2 = itemView.findViewById(R.id.Linear2);
            PhoneCall = itemView.findViewById(R.id.PhoneCall);
            Chat = itemView.findViewById(R.id.Chat);
            Profile = itemView.findViewById(R.id.Profile);

        }
    }
}
