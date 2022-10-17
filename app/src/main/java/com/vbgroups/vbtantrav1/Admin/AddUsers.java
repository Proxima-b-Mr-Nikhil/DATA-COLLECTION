package com.vbgroups.vbtantrav1.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vbgroups.vbtantrav1.Fragments.Main2Activity;
import com.vbgroups.vbtantrav1.MainActivity;
import com.vbgroups.vbtantrav1.R;

import java.util.Calendar;
import java.util.HashMap;

public class AddUsers extends AppCompatActivity {

    EditText email,password,name,duration,phone;
    CheckBox check;
    Button projects;
    LinearLayout prolyt;
    Button submit;
    LinearLayout linear;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    DatabaseReference reference;
    int mYear, mMonth, mDay;
    FirebaseUser fuser;
    String userid;
    private FirebaseAuth.AuthStateListener authListener;
    String[] arraySpinne;
    String mm,dd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        email=findViewById(R.id.aemail);
        password=findViewById(R.id.apassword);
        duration=findViewById(R.id.aduration);
        name=findViewById(R.id.aname);
        check=findViewById(R.id.check);
        submit=findViewById(R.id.asubmit);
        linear=findViewById(R.id.alinear);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        phone=findViewById(R.id.aphone);
        auth = FirebaseAuth.getInstance();
        duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddUsers.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                if((monthOfYear+1)< 10){

                                    mm = "0" + (monthOfYear+1);
                                }else {
                                    mm=""+(monthOfYear+1);
                                }
                                if(dayOfMonth < 10){
                                    dd = "0" + dayOfMonth ;
                                }else {
                                    dd=""+dayOfMonth;
                                }
                                String a = year + "/" + mm + "/" + dd;
                                duration.setText(a);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        auth = FirebaseAuth.getInstance();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nam = preferences.getString("page","");
        if (!nam.isEmpty()) {
            if (nam.equals("SuperAdmin")) {
                arraySpinne = new String[]{
                        "--select--", "Surveyor", "Admin", "User"
                };
            }else {
                arraySpinne = new String[]{
                        "--select--", "Surveyor", "User"
                };
            }
        }
        final Spinner s = (Spinner)findViewById(R.id.aposition);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddUsers.this,
                android.R.layout.simple_spinner_item, arraySpinne);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);


        String[] arraySpinn = new String[] {
                "--select--","Active", "Inactive"
        };
        final Spinner a = (Spinner)findViewById(R.id.ajobstatus);
        ArrayAdapter<String> adapte = new ArrayAdapter<String>(AddUsers.this,
                android.R.layout.simple_spinner_item, arraySpinn);
        adapte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        a.setAdapter(adapte);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()){
                    final String semail=email.getText().toString();
                    final String spassword=password.getText().toString();
                    final String sduration=duration.getText().toString();
                    final String  d =sduration.replace("/","");
                    final String type = s.getSelectedItem().toString();
                  final   String ph=phone.getText().toString();

                    if (semail.isEmpty()) {
                        email.setError("Invalid");
                        return;
                    }
                    if (spassword.length()<6) {
                        password.setError("Minimum 6 digits or characters");
                        return;
                    }
                    final String sname=name.getText().toString();
                    if (type.equals("--select--")) {
                        s.requestFocus();
                        Toast.makeText(getApplicationContext(), "Please choose one option", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    final String typ = a.getSelectedItem().toString();
                    if (typ.equals("--select--")) {
                        a.requestFocus();
                        Toast.makeText(getApplicationContext(), "Please choose one option", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (sname.isEmpty()){
                        name.setError("Invalid");
                        return;
                    }
                    if (d.isEmpty()){
                        duration.setError("Invalid");
                        return;
                    }

                    if (!ph.isEmpty()){
                        if (ph.length()>6&&ph.length()<16){

                        }else {
                            phone.setError("Invalid");
                            return;
                        }
                    }

                    progressBar.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(semail, spassword)
                            .addOnCompleteListener(AddUsers.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(AddUsers.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {

                                        fuser = FirebaseAuth.getInstance().getCurrentUser();
                                        if (fuser!=null){
                                            FirebaseUser firebaseUser = auth.getCurrentUser();
                                            assert firebaseUser != null;
                                            userid = firebaseUser.getUid();

                                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                                            HashMap<String, String> hashMap = new HashMap<>();
                                            hashMap.put("id", userid);
                                            hashMap.put("duration",d);
                                            hashMap.put("job", type);
                                            hashMap.put("name",sname);
                                            hashMap.put("phone",ph);
                                            hashMap.put("password",spassword);
                                            hashMap.put("email", semail);
                                            hashMap.put("status", typ);
                                            hashMap.put("imageurl","default");
                                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                            Toast.makeText(AddUsers.this, "Authentication completed", Toast.LENGTH_SHORT).show();
                                                            progressBar.setVisibility(View.GONE);
                                                            FirebaseAuth.getInstance().signOut();
                                                            auth.signOut();
                                                    }
                                                }
                                            });
                                        }
                                    }}
                            });

                }else{
                    Snackbar.make(linear, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(AddUsers.this, MainActivity.class);
        startActivity(intent);
    }

}
