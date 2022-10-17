package com.vbgroups.vbtantrav1.Userpanel;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.vbgroups.vbtantrav1.LoginActivity;
import com.vbgroups.vbtantrav1.MainActivity;
import com.vbgroups.vbtantrav1.ProfileActivity;
import com.vbgroups.vbtantrav1.R;

import java.util.ArrayList;

public class UserPanel extends AppCompatActivity {

    ListView myListView;
    FirebaseUser fuser;
    private MaterialSearchBar materialSearchBar;
    ProgressBar progressBar;
    CardView cardView;
    EditText search;
    Query query;
    TextView nodata;
    DatabaseReference reference;
    ArrayList<String>myArrayList=new ArrayList<>();
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        nodata=findViewById(R.id.nodata);
        nodata.setVisibility(View.GONE);
        materialSearchBar = findViewById(R.id.searchBar);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(UserPanel.this, LoginActivity.class));
                    finish();
                }
            }
        };
        myListView=(ListView)findViewById(R.id.listView);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView,myArrayList);
        myListView.setAdapter(myArrayAdapter);
        myListView.setTextFilterEnabled(true);

        myListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        cardView=(CardView) findViewById(R.id.card);
        cardView.setVisibility(View.GONE);

        progressBar=(ProgressBar)findViewById(R.id.progressBar) ;
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if(firebaseUser != null) {
            String userid = firebaseUser.getUid();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String name = preferences.getString("page","");
            if (name.equals("SuperAdmin")||name.equals("Admin")){
                query = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets");
            }else {
                query = FirebaseDatabase.getInstance().getReference("Users").child(userid).child("projects");
            }
            query.keepSynced(true);
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    if (dataSnapshot.exists()) {
                        String key = dataSnapshot.getKey();

                        myArrayList.add(key);
                        myArrayAdapter.notifyDataSetChanged();
                        cardView.setVisibility(View.VISIBLE);
                        materialSearchBar.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }else {
                        nodata.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    myArrayAdapter.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString(), true, null, true);
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION) {
                    //opening or closing a navigation drawer
                } else if (buttonCode == MaterialSearchBar.BUTTON_BACK) {
                    materialSearchBar.disableSearch();
                }
            }
        });
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                myArrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.textView);
                String text = textView.getText().toString();
                Intent intent=new Intent(UserPanel.this,UserPanel1.class);
                intent.putExtra("id",text);
                startActivity(intent);
            }
        });

        int Permission_All = 1;
        String[] Permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,};
        if (!hasPermissions(UserPanel.this, Permissions)) {
            ActivityCompat.requestPermissions(UserPanel.this, Permissions, Permission_All);


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("page","");
        if (name.equals("User")){
            getMenuInflater().inflate(R.menu.mymenu, menu);
        }

        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public void onBackPressed() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("page","");
        if (name.equals("SuperAdmin")){
            startActivity ( new Intent(UserPanel.this, MainActivity.class));
            Animatoo.animateSlideLeft(UserPanel.this);
        }
        if (name.equals("Surveyor")){
            startActivity ( new Intent(UserPanel.this, MainActivity.class));
            Animatoo.animateSlideLeft(UserPanel.this);
        }
        if (name.equals("Admin")){
            startActivity ( new Intent(UserPanel.this, MainActivity.class));
            Animatoo.animateSlideLeft(UserPanel.this);
        }
        if (name.equals("User")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage("Are you sure you want to exit ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                            System.exit(0);
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage("Are you sure you want to log out ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            signOut();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        if (id == R.id.profile) {
            startActivity ( new Intent(UserPanel.this, ProfileActivity.class));
            Animatoo.animateSlideLeft(UserPanel.this);
        }

        if (id == R.id.superadmin) {
            startActivity ( new Intent(UserPanel.this, MainActivity.class));
            Animatoo.animateSlideLeft(UserPanel.this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }



    public static boolean hasPermissions(FragmentActivity context, String... permissions){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED){
                    return  false;
                }
            }
        }
        return true;
    }

}


