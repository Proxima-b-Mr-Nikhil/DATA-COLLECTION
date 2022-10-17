package com.vbgroups.vbtantrav1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vbgroups.vbtantrav1.Fragments.Main2Activity;
import com.vbgroups.vbtantrav1.Model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    EditText email,password;
    Button loginbtn;
    RelativeLayout Relativelayout;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("page","");
        if (!name.isEmpty()) {
            if (name.equals("Admin")) {
                if (auth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Animatoo.animateSlideLeft(LoginActivity.this);
                    finish();
                }
            }
            if (name.equals("SuperAdmin")) {
                if (auth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    Animatoo.animateSlideLeft(LoginActivity.this);
                    finish();
                }
            }

            if (name.equals("User")) {
                if (auth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Animatoo.animateSlideLeft(LoginActivity.this);
                    finish();
                }
            }
            if (name.equals("Surveyor")) {
                if (auth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Animatoo.animateSlideLeft(LoginActivity.this);
                    finish();
                }
            }
            if (name.equals("Helpdesk")) {
                if (auth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, Main2Activity.class));
                    Animatoo.animateSlideLeft(LoginActivity.this);
                    finish();
                }
            }

        }
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        changeStatusBarColor();
        email=findViewById(R.id.email);
        password=findViewById(R.id.Password);
        loginbtn=findViewById(R.id.loginbut);
        progressBar=findViewById(R.id.progressBar);
        Relativelayout=findViewById(R.id.relativelayout);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                }
                return false;
            }});

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        final String e=email.getText().toString();
        final String p=password.getText().toString();

        if (e.isEmpty()){
            email.setError("Enter Email");
            return;
        }
        if (p.isEmpty()){
            password.setError("Enter Password");
            return;
        }
        if (password.length()<6){
            Snackbar.make(Relativelayout, "Invalid Password", Snackbar.LENGTH_LONG).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Snackbar.make(Relativelayout, "Invalid credentials", Snackbar.LENGTH_LONG).show();

                        } else {

                            final FirebaseUser firebaseUser = auth.getCurrentUser();
                            if(firebaseUser != null) {
                                final String userid = firebaseUser.getUid();
                                reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            User user = snapshot.getValue(User.class);
                                            assert user != null;
                                            String s = user.getStatus();
                                            int duration = Integer.parseInt(user.getDuration());
                                            Date cDate = new Date();
                                            String pos = user.getJob();
                                            if (!pos.isEmpty()){
                                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                            SharedPreferences.Editor editor = preferences.edit();
                                            @SuppressLint("SimpleDateFormat") int currentdate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(cDate));
                                                if (s.equals("Active") && duration > currentdate) {
                                                    if (pos.equals("Surveyor")) {
                                                        progressBar.setVisibility(View.GONE);
                                                        editor.putString("page", "Surveyor");
                                                        editor.apply();
                                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                        Animatoo.animateSlideLeft(LoginActivity.this);
                                                    }
                                                    if (pos.equals("Admin")) {
                                                        progressBar.setVisibility(View.GONE);
                                                        editor.putString("page", "Admin");
                                                        editor.putString("email", e);
                                                        editor.putString("password", p);
                                                        editor.apply();


                                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                                        Animatoo.animateSlideLeft(LoginActivity.this);
                                                    }
                                                    if (pos.equals("User")) {
                                                        progressBar.setVisibility(View.GONE);
                                                        editor.putString("page", "User");
                                                        editor.putString("email", e);
                                                        editor.putString("password", p);
                                                        editor.apply();
                                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                        Animatoo.animateSlideLeft(LoginActivity.this);
                                                    }
                                            } else {

                                                FirebaseAuth.getInstance().signOut();
                                                auth.signOut();
                                                progressBar.setVisibility(View.GONE);
                                                if (!s.equals("active")) {
                                                    Snackbar.make(Relativelayout, "Your profile is inactive", Snackbar.LENGTH_LONG).show();
                                                }
                                                    currentdate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(cDate));
                                                if (duration < currentdate) {
                                                    Snackbar.make(Relativelayout, "Your duration of work has been completed", Snackbar.LENGTH_LONG).show();
                                                }
                                            }

                                        }

                                    }else {
                                        String userid = firebaseUser.getUid();
                                        reference = FirebaseDatabase.getInstance().getReference("SuperAdmin").child(userid);
                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()){
                                                    User user = snapshot.getValue(User.class);
                                                    assert user != null;
                                                    String pos = user.getJob();

                                                    if (pos.equals("SuperAdmin")) {
                                                        progressBar.setVisibility(View.GONE);
                                                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                                        SharedPreferences.Editor editor = preferences.edit();
                                                        editor.putString("page", "SuperAdmin");
                                                        editor.putString("email", e);
                                                        editor.putString("password", p);
                                                        editor.apply();

                                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                        Animatoo.animateSlideLeft(LoginActivity.this);
                                                    }else {
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(Relativelayout, "Invalid", Snackbar.LENGTH_LONG).show();
                                                    }
                                                }else {

                                                String userid = firebaseUser.getUid();
                                                reference = FirebaseDatabase.getInstance().getReference("Helpdesk").child(userid);
                                                reference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (snapshot.exists()){
                                                            User user = snapshot.getValue(User.class);
                                                            assert user != null;
                                                            String pos = user.getJob();

                                                            if (pos.equals("Helpdesk")) {
                                                                progressBar.setVisibility(View.GONE);
                                                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                                                SharedPreferences.Editor editor = preferences.edit();
                                                                editor.putString("page", "Helpdesk");
                                                                editor.apply();

                                                                startActivity(new Intent(LoginActivity.this, Main2Activity.class));
                                                                Animatoo.animateSlideLeft(LoginActivity.this);
                                                            }else {
                                                                progressBar.setVisibility(View.GONE);
                                                                Snackbar.make(Relativelayout, "Invalid", Snackbar.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(Relativelayout, "Try again", Snackbar.LENGTH_LONG).show();
                                                    }
                                                });}

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                progressBar.setVisibility(View.GONE);
                                                Snackbar.make(Relativelayout, "Try again", Snackbar.LENGTH_LONG).show();
                                            }
                                        });

                                        }
                                    }
                                    @Override
                                    public void onCancelled (@NonNull DatabaseError error){
                                        progressBar.setVisibility(View.GONE);
                                        Snackbar.make(Relativelayout, "Try again", Snackbar.LENGTH_LONG).show();
                                    }
                                });

                            }

                        }
                    }
                });
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.white));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        }
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }

}
