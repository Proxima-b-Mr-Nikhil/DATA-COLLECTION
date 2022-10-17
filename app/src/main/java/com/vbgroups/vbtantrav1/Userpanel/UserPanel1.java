package com.vbgroups.vbtantrav1.Userpanel;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vbgroups.vbtantrav1.Adapter.AssetsAdapter;
import com.vbgroups.vbtantrav1.LoginActivity;
import com.vbgroups.vbtantrav1.MainActivity;
import com.vbgroups.vbtantrav1.Model.Plantdetails;
import com.vbgroups.vbtantrav1.Model.User;
import com.vbgroups.vbtantrav1.Model.userAssets;

import com.vbgroups.vbtantrav1.R;
import com.vbgroups.vbtantrav1.Surveyor.NewProject1;
import com.vbgroups.vbtantrav1.Surveyor.ProjectEdit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class UserPanel1 extends AppCompatActivity {
    TextView txt,a,b,c,d,e,f,g,h,i,j,k,trans,m,n,o;
    DatabaseReference reference,ref,refassets;
    FirebaseUser fuser;
    FirebaseAuth auth;
    ImageView img;
    Button editproject,print;
    String upid,createrid;
    ArrayList<userAssets> arrayList;

    RecyclerView recyclerView;
    AssetsAdapter adapter;
    EditText editTextSearch;
    String id;
    LinearLayout prnt;
    Bitmap bmp,sbmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel1);
        auth = FirebaseAuth.getInstance();

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        assert firebaseUser != null;
        final String userid = firebaseUser.getUid();
        editproject=findViewById(R.id.editproject);
        prnt=findViewById(R.id.lnrprt);
        txt=findViewById(R.id.textView);
        a=findViewById(R.id.textView2);
        b=findViewById(R.id.textView3);
        c=findViewById(R.id.textView4);
        d=findViewById(R.id.textView5);
        e=findViewById(R.id.textView6);
        f=findViewById(R.id.textView7);
        g=findViewById(R.id.textView8);
        h=findViewById(R.id.textView9);
        i=findViewById(R.id.textView10);
        j=findViewById(R.id.textView11);
        k=findViewById(R.id.textView12);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        txt.setText(id);
        final String z=txt.getText().toString().trim();

        if (!z.isEmpty()) {

            reference = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets").child(z);
            reference.keepSynced(true);
            recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(UserPanel1.this));
            FirebaseRecyclerOptions<userAssets> options =
                    new FirebaseRecyclerOptions.Builder<userAssets>()
                            .setQuery(reference.child("Assets"), userAssets.class)
                            .build();

            adapter = new AssetsAdapter(options);
            recyclerView.setAdapter(adapter);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Plantdetails user = snapshot.getValue(Plantdetails.class);
                    assert user != null;
                    a.setText(user.getPlantname());
                    b.setText(user.getPlanttype());
                    c.setText(user.getPlantvisitdate());
                    d.setText(user.getPlantcity());
                    e.setText(user.getPurposeofvisit());
                    f.setText(user.getRefname());
                    g.setText(user.getRefphone());
                    i.setText(user.getId());
                    j.setText(user.getLatitude());
                    k.setText(user.getLongitude());
                    createrid = i.getText().toString().trim();
                    if (!createrid.isEmpty()) {
                        ref = FirebaseDatabase.getInstance().getReference("Users").child(createrid);
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    User user1 = snapshot.getValue(User.class);
                                    assert user1 != null;
                                    h.setText(user1.getName());

                                }else {
                                    ref = FirebaseDatabase.getInstance().getReference("SuperAdmin").child(createrid);
                                    ref.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()){
                                                User user1 = snapshot.getValue(User.class);
                                                assert user1 != null;
                                                h.setText(user1.getName());

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("page","");
        if (!name.isEmpty()) {
            if (name.equals("Surveyor")) {
                editproject.setVisibility(View.VISIBLE);
               }else {
                editproject.setVisibility(View.GONE);
            }
            }
      editproject.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(UserPanel1.this, ProjectEdit.class);
              intent.putExtra("id",id);
              startActivity(intent);
          }
      });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menusearch, menu);
        MenuItem item=menu.findItem(R.id.mysearch);
        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ProcessSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ProcessSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void ProcessSearch(String s) {

        FirebaseRecyclerOptions<userAssets> options =
                new FirebaseRecyclerOptions.Builder<userAssets>()
                        .setQuery(reference.child("Assets").orderByChild("Asset_name").startAt(s).endAt(s+"/uf8ff`"), userAssets.class)
                        .build();

        adapter=new AssetsAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mysearch) {


        }
        return super.onOptionsItemSelected(item);
    }
}
