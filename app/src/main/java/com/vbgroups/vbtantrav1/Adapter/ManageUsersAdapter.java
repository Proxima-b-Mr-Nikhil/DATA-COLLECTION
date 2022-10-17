package com.vbgroups.vbtantrav1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.vbgroups.vbtantrav1.Admin.ManageProjects1;
import com.vbgroups.vbtantrav1.Admin.manageusers;
import com.vbgroups.vbtantrav1.Model.User;
import com.vbgroups.vbtantrav1.R;

import java.util.ArrayList;

public class ManageUsersAdapter extends FirebaseRecyclerAdapter<User, ManageUsersAdapter.myviewholder> {


    Context context;
    public ManageUsersAdapter(FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull final User User) {
        myviewholder.name.setText(User.getName());
        myviewholder.id.setText(User.getId());
        myviewholder.duration.setText(User.getDuration());
        myviewholder.email.setText(User.getEmail());
        myviewholder.status.setText(User.getStatus());
        myviewholder.job.setText(User.getJob());
        myviewholder.progressBar.setVisibility(View.GONE);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String name = preferences.getString("page","");
        if (!name.isEmpty()) {
            if (name.equals("SuperAdmin")||name.equals("Admin")) {
                myviewholder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(context, ManageProjects1.class);
                        intent.putExtra("id",User.getId());
                        intent.putExtra("name",User.getName());
                        intent.putExtra("password",User.getPassword());
                        intent.putExtra("duration",User.getDuration());
                        intent.putExtra("email",User.getEmail());
                        intent.putExtra("status",User.getStatus());
                        intent.putExtra("job",User.getJob());
                        context.startActivity(intent);
                    }
                });

            }
        }


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleuser,parent,false);
        context = parent.getContext();
        return new myviewholder(view);

    }


    class myviewholder extends RecyclerView.ViewHolder{
        TextView name,email,id,status,duration,job;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.uname);
            email=(TextView)itemView.findViewById(R.id.uemail);
            id=(TextView)itemView.findViewById(R.id.uid);
            status=(TextView)itemView.findViewById(R.id.ustatus);
            duration=(TextView) itemView.findViewById(R.id.uduration);
            job=(TextView)itemView.findViewById(R.id.ujob);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linerauser);
            progressBar=(ProgressBar)itemView.findViewById(R.id.progressBar3);

        }
    }
}
