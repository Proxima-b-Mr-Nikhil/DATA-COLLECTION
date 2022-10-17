package com.vbgroups.vbtantrav1.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vbgroups.vbtantrav1.Adapter.AllUsersAdapter;
import com.vbgroups.vbtantrav1.Adapter.ManageUsersAdapter;
import com.vbgroups.vbtantrav1.Model.User;
import com.vbgroups.vbtantrav1.R;
import com.vbgroups.vbtantrav1.Userpanel.UserPanel;

public class ManageProjects extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView recyclerView;
    ManageUsersAdapter useradapter;
    Button allprojects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        allprojects=findViewById(R.id.allproject);
        allprojects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent(ManageProjects.this, UserPanel.class));
                Animatoo.animateSlideLeft(ManageProjects.this);
            }
        });
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.keepSynced(true);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ManageProjects.this));
        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(reference, User.class)
                        .build();

        useradapter = new ManageUsersAdapter(options);
        recyclerView.setAdapter(useradapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        useradapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        useradapter.startListening();
    }

}
