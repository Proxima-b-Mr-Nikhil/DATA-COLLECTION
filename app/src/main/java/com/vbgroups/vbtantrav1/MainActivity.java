package com.vbgroups.vbtantrav1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vbgroups.vbtantrav1.Helpdesk.UserMessagesActivity;
import com.vbgroups.vbtantrav1.Model.User;
import com.vbgroups.vbtantrav1.Userpanel.UserPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth auth;
    FirebaseUser fuser;
    DatabaseReference reference;
    ImageView dp,mark;
    TextView name,phone,email,status,job;
    private FirebaseAuth.AuthStateListener authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseApp.initializeApp(MainActivity.this);

        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity ( new Intent(MainActivity.this, LoginActivity.class));
                    Animatoo.animateSlideLeft(MainActivity.this);
                    finish();
                }
            }
        };

        fuser = auth.getCurrentUser();
        if(fuser != null) {
            fuser = FirebaseAuth.getInstance().getCurrentUser();
            assert fuser != null;

            reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){

                        final User user = dataSnapshot.getValue(User.class);
                        assert user != null;

                        name.setText(Objects.requireNonNull(user).getName());
                        phone.setText(Objects.requireNonNull(user).getPhone());
                        email.setText(Objects.requireNonNull(user).getEmail());
                        job.setText(Objects.requireNonNull(user).getJob());
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        String nam = preferences.getString("page","");
                        if (!nam.isEmpty()) {
                            if (!nam.equals("SuperAdmin")) {

                                SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preference.edit();
                                editor.putString("Name", user.getName());
                                editor.putString("Phone", user.getPhone());
                                editor.putString("Email", user.getEmail());
                                editor.putString("Job", user.getJob());
                                editor.putString("userid", fuser.getUid());
                                editor.apply();
                            }
                        }
                        if (user.getImageurl().equals("default")) {
                            dp.setImageResource(R.drawable.defaultpic);
                        } else {
                            if (!MainActivity.this.isFinishing()) {
                                Glide.with(dp.getContext()).load(user.getImageurl()).into(dp);

                            }
                        }

                    }else {
                        reference = FirebaseDatabase.getInstance().getReference("SuperAdmin").child(fuser.getUid());
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    User user = snapshot.getValue(User.class);
                                    assert user != null;

                                    name.setText(Objects.requireNonNull(user).getName());
                                    phone.setText(Objects.requireNonNull(user).getPhone());
                                    email.setText(Objects.requireNonNull(user).getEmail());
                                    job.setText(Objects.requireNonNull(user).getJob());

                                    if (user.getImageurl().equals("default")) {
                                        dp.setImageResource(R.drawable.defaultpic);
                                    } else {
                                        if (!MainActivity.this.isFinishing()) {
                                            Glide.with(dp.getContext()).load(user.getImageurl()).into(dp);

                                        }
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nam = preferences.getString("page","");
        if (!nam.isEmpty()) {
            if (nam.equals("User")) {

                Intent intent=new Intent(MainActivity.this, UserPanel.class);
                startActivity(intent);
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_logout,R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                signOut();
                return false;
            }
        });
        navigationView.getMenu().findItem(R.id.nav_profile).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
               Intent intent=new Intent(MainActivity.this, ProfileActivity.class);
               startActivity(intent);
                return false;
            }
        });

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        dp= headerView.findViewById(R.id.imageView);
        mark= headerView.findViewById(R.id.statuscolor);
        name= headerView.findViewById(R.id.nametext);
        email=  headerView.findViewById(R.id.emailtext);
        job=headerView.findViewById(R.id.job);
        status=  headerView.findViewById(R.id.status);
        phone=headerView.findViewById(R.id.phonetext);

        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String na = preference.getString("page","");
        if (!na.isEmpty()) {
            if (!na.equals("SuperAdmin")) {

                SharedPreferences preferenc = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                name.setText(preferenc.getString("Name", ""));
                phone.setText(preferenc.getString("Phone", ""));
                email.setText(preferenc.getString("Email", ""));
                job.setText(preferenc.getString("Job", ""));
            }
        }
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        int Permission_All = 1;
        String[] Permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,};
        if (!hasPermissions(MainActivity.this, Permissions)) {
            ActivityCompat.requestPermissions(MainActivity.this, Permissions, Permission_All);
        }

    }
    private void connectivity() {
        if (InternetConnection.checkConnection(getApplicationContext())) {
            status.setText("Online");
            mark.setImageResource(R.drawable.online);

        } else {
            status.setText("Offline");
            mark.setImageResource(R.drawable.offline);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("page","");
        if (name.equals("SuperAdmin")){
            getMenuInflater().inflate(R.menu.superadminmenu, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.superadmin) {
            startActivity ( new Intent(MainActivity.this, MainActivity.class));
            Animatoo.animateSlideLeft(MainActivity.this);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        connectivity();

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Are you sure you want to exit ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override
    public void onStart() {
        connectivity();
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        connectivity();

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
    private void status(String status){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String na = preference.getString("page","");
        if (!na.isEmpty()) {
            if (!na.equals("SuperAdmin")&&!na.equals("Helpdesk")) {
                fuser = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("chatstatus", status);
                reference.updateChildren(hashMap);
            }
        }
    }

}
