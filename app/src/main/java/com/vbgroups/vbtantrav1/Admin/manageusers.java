package com.vbgroups.vbtantrav1.Admin;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vbgroups.vbtantrav1.R;

import java.util.Calendar;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class manageusers extends AppCompatActivity {
    TextView name, email, id, status, duration, job;
    ProgressBar progressBar;
    String sid, semail, spassword, sname, sstatus="", sduration, sjob="",dur;
    FirebaseAuth auth;
    ProgressBar pro;
    Spinner ejob, estatus;
    LinearLayout linearLayout, smsg,fmsg,lnr;
    CheckBox check;
    Button submit,edit;
    DatabaseReference a,b;
    ProgressBar mpro;
    int mYear, mMonth, mDay;
    String mm,dd;
    String x,y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(manageusers.this);
         x = preferences.getString("email", "");
        y = preferences.getString("password", "");


        name = (TextView) findViewById(R.id.mname);
        email = (TextView) findViewById(R.id.memail);
        id = (TextView) findViewById(R.id.mid);
        status = (TextView) findViewById(R.id.mstatus);
        estatus = findViewById(R.id.emstatus);
        ejob = findViewById(R.id.emjob);
        check = findViewById(R.id.echeckBox);
        submit = findViewById(R.id.esubmit);
        edit=findViewById(R.id.eedit);
        smsg=findViewById(R.id.successmsg);
        fmsg=findViewById(R.id.failmsg);

        duration = (TextView) findViewById(R.id.mduration);
        job = (TextView) findViewById(R.id.mjob);
        progressBar = (ProgressBar) findViewById(R.id.mprogressBar3);
        mpro=findViewById(R.id.mpro);
        linearLayout = findViewById(R.id.linermuser);
        check.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        sname = bundle.getString("name");
        semail = bundle.getString("email");
        spassword = bundle.getString("password");
        sid = bundle.getString("id");
        sstatus = bundle.getString("status");
        sduration = bundle.getString("duration");
        sjob = bundle.getString("job");


        name.setText(sname);
        email.setText(semail);
        id.setText(sid);
        status.setText(sstatus);
        duration.setText(sduration);
        job.setText(sjob);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setVisibility(View.GONE);
                openbottensheet();
            }
        });

    }
    private void accdelet() {
        if (!semail.isEmpty() && !spassword.isEmpty()) {
            pro.setVisibility(View.VISIBLE);
            final String e = semail.trim();
            final String pp = spassword.trim();
            auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(semail, pp)
                    .addOnCompleteListener(manageusers.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                AuthCredential credential = EmailAuthProvider
                                        .getCredential(e, pp);
                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                assert user != null;

                                user.reauthenticate(credential)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                user.delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    if (!user.getUid().isEmpty()){
                                                                        a = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                                                                        b = FirebaseDatabase.getInstance().getReference("DeletedUsers").child(user.getUid());
                                                                        moveRecord(a,b);

                                                                    }
                                                                } else {
                                                                    pro.setVisibility(View.GONE);
                                                                    Toast.makeText(manageusers.this, "failed", Toast.LENGTH_LONG).show();
                                                                }
                                                            }
                                                        });
                                            }
                                        });

                            } else {
                                pro.setVisibility(View.GONE);
                                Toast.makeText(manageusers.this, "login failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
    private void openbottensheet() {
        final BottomSheetDialog dialog = new BottomSheetDialog(manageusers.this);
        dialog.setContentView(R.layout.bottommanagesheet);
        LinearLayout btnedit = (LinearLayout) dialog.findViewById(R.id.edit);
        LinearLayout btndelet = (LinearLayout) dialog.findViewById(R.id.delet);
        LinearLayout btndisable = (LinearLayout) dialog.findViewById(R.id.disable);
        lnr = (LinearLayout) dialog.findViewById(R.id.lnr);
        final TextView txt = (TextView) dialog.findViewById(R.id.txt);
        final Button ok = (Button) dialog.findViewById(R.id.ok);
        pro = (ProgressBar) dialog.findViewById(R.id.pro);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                edit.setVisibility(View.VISIBLE);
            }
        });
        assert btnedit != null;
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String name = preferences.getString("page","");
                if (!name.isEmpty()) {
                    if (name.equals("SuperAdmin")){
                        assert txt != null;
                        txt.setVisibility(View.VISIBLE);
                        txt.setText("Are you sure you want to edit the profile ?");
                        assert ok != null;
                        ok.setVisibility(View.VISIBLE);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                edit.setVisibility(View.GONE);
                                edit();

                            }
                        });
                    }
                    else {
                        if (sjob.equals("Admin")){
                            assert txt != null;
                            txt.setVisibility(View.VISIBLE);
                            txt.setText("Only super admin can edit the admin profile.");
                        }else {
                            assert txt != null;
                            txt.setVisibility(View.VISIBLE);
                            txt.setText("Are you sure you want to edit the profile ?");
                            assert ok != null;
                            ok.setVisibility(View.VISIBLE);
                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    edit.setVisibility(View.GONE);
                                    edit();

                                }
                            });
                        }
                    }
                }

            }
        });
        assert btndelet != null;
        btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert txt != null;
                txt.setVisibility(View.VISIBLE);
                txt.setText("Are you sure you want to delete the user ?");
                assert ok != null;
                ok.setVisibility(View.VISIBLE);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        accdelet();
                    }
                });
            }


        });
        assert btndisable != null;
        btndisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert txt != null;
                txt.setVisibility(View.VISIBLE);
                txt.setText("Are you sure you want to reset password ?");
                assert ok != null;
                ok.setVisibility(View.VISIBLE);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        pro.setVisibility(View.VISIBLE);
                        accreset();
                    }
                });
            }
        });

        dialog.show();
    }
    private void moveRecord(DatabaseReference fromPath, final DatabaseReference toPath) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            pro.setVisibility(View.GONE);
                            Toast.makeText(manageusers.this, "deleted", Toast.LENGTH_LONG).show();
                            a.removeValue();

                            auth.signInWithEmailAndPassword(x,y).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        auth = FirebaseAuth.getInstance();
                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        assert user != null;
                                    }
                                }
                            });
                        } else {

                            auth.signInWithEmailAndPassword(x,y).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        auth = FirebaseAuth.getInstance();
                                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        assert user != null;
                                    }
                                }
                            });
                            pro.setVisibility(View.GONE);
                            Toast.makeText(manageusers.this, "try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        };
        fromPath.addListenerForSingleValueEvent(valueEventListener);
    }
    private void accreset() {
        if (!semail.isEmpty()) {
            auth = FirebaseAuth.getInstance();
            auth.sendPasswordResetEmail(semail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                pro.setVisibility(View.GONE);
                                Toast.makeText(manageusers.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                pro.setVisibility(View.GONE);
                                Toast.makeText(manageusers.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }



    private void edit() {
        status.setVisibility(View.GONE);
        job.setVisibility(View.GONE);
        estatus.setVisibility(View.VISIBLE);
        ejob.setVisibility(View.VISIBLE);
        check.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);

        String[] arraySpinne = new String[]{
                "--select--", "Surveyor", "User"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(manageusers.this,
                android.R.layout.simple_spinner_item, arraySpinne);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ejob.setAdapter(adapter);
        ejob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position>0){
                    sjob = ejob.getSelectedItem().toString();
                }else {
                    sjob="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                sjob="";
            }
        });

        String[] arraySpinn = new String[]{
                "--select--", "Active", "Inactive"
        };
        ArrayAdapter<String> adapte = new ArrayAdapter<String>(manageusers.this,
                android.R.layout.simple_spinner_item, arraySpinn);
        adapte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estatus.setAdapter(adapte);

        estatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position>0){
                    sstatus = estatus.getSelectedItem().toString();
                }else {
                    sstatus="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                sstatus="";
            }
        });


        duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(manageusers.this,
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
                                dur= year+""+mm+""+dd;
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(manageusers.this);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("duration",dur);
                                editor.apply();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {

                    if (!sid.isEmpty()) {
                        mpro.setVisibility(View.VISIBLE);

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(sid);
                        final HashMap<String, Object> updates = new HashMap<>();
                        if (dur!=null){
                            if (!dur.isEmpty()){
                            updates.put("duration", dur);
                            }
                        }

                        if (sjob!=null) {
                            if (!sjob.equals("")){
                            updates.put("job", sjob);
                            }
                        }
                        if (sstatus!=null){
                        if (!sstatus.equals("")){
                            updates.put("status", sstatus);
                        }}
                        if (updates.isEmpty()){
                            fmsg.setVisibility(View.VISIBLE);
                            fmsg.postDelayed(new Runnable() {
                                public void run() {fmsg.setVisibility(View.GONE);
                                }
                            }, 3000);
                        }
                        ref.updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    if (!updates.isEmpty()){
                                    smsg.setVisibility(View.VISIBLE);
                                    smsg.postDelayed(new Runnable() {
                                        public void run() {
                                            smsg.setVisibility(View.GONE);
                                        }
                                    }, 3000);}
                                    dur="";
                                    sstatus="";
                                    sjob="";

                                }else {
                                    fmsg.setVisibility(View.VISIBLE);
                                    fmsg.postDelayed(new Runnable() {
                                        public void run() {
                                            smsg.setVisibility(View.GONE);
                                        }
                                    }, 3000);
                                }
                            }
                        });

                    check.setChecked(false);
                    mpro.setVisibility(View.GONE);
                } else {
                    Toast.makeText(manageusers.this, "try again", Toast.LENGTH_LONG).show();
                }
                }else {
                    Toast.makeText(manageusers.this, "Please allow check box to proceed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}