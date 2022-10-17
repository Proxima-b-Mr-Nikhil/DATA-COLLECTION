package com.vbgroups.vbtantrav1.Surveyor;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.vbgroups.vbtantrav1.MainActivity;
import com.vbgroups.vbtantrav1.Model.userAssets;
import com.vbgroups.vbtantrav1.R;
import com.vbgroups.vbtantrav1.Userpanel.UserPanel1;
import com.vbgroups.vbtantrav1.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewProject extends AppCompatActivity {
    EditText plantname,date,countrycode,refname,refphone,plantcity;
    TextView lt,lg;
    ImageView refresh;
    Button submit;
    Button plantidbtn;
    EditText plantid;
    CheckBox checkBox;

    private Location mLastKnownLocation;
    private LocationCallback locationCallback;
    FusedLocationProviderClient fusedLocationProviderClient;

    FirebaseUser fuser;
    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    LinearLayout linear;
    DatabaseReference refe;

    int mYear, mMonth, mDay;
    String mm,dd;
    String currentdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        plantname=findViewById(R.id.plantname);
        date=findViewById(R.id.visitdate);
        lt=findViewById(R.id.latitude);
        lg=findViewById(R.id.lontitude);
        refname=findViewById(R.id.refname);
        refphone=findViewById(R.id.refphone);
        countrycode=findViewById(R.id.countrycode);
        plantcity=findViewById(R.id.plantcity);
        refresh=findViewById(R.id.refresh);

        checkBox=findViewById(R.id.checkBox);
        linear=findViewById(R.id.l);
        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();


                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(NewProject.this,
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
                                currentdate = dd + "-" + mm + "-" + year;
                                date.setText(currentdate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        String[] arraySpinner = new String[] {
                "--select--","Power plant", "Chemical industry","Hydro industry","Mineral industry"
        };
        final Spinner s = (Spinner)findViewById(R.id.planttype);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewProject.this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);


        String[] arraySpinne = new String[] {
                "--select--","General survey", "Technical issues"
        };
        final Spinner x = (Spinner)findViewById(R.id.purposeofvisit);
        ArrayAdapter<String> adapte = new ArrayAdapter<String>(NewProject.this,
                android.R.layout.simple_spinner_item, arraySpinne);
        adapte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        x.setAdapter(adapte);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(NewProject.this);

                LocationRequest locationRequest = LocationRequest.create();
                locationRequest.setInterval(10000);
                locationRequest.setFastestInterval(5000);
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

                SettingsClient settingsClient = LocationServices.getSettingsClient(NewProject.this);
                Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

                task.addOnSuccessListener(NewProject.this, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                        getDeviceLocation();
                    }


                    private void getDeviceLocation() {
                        fusedLocationProviderClient.getLastLocation()
                                .addOnCompleteListener(new OnCompleteListener<Location>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Location> task) {
                                        if (task.isSuccessful()) {
                                            mLastKnownLocation = task.getResult();
                                            if (mLastKnownLocation != null) {
                                                lt.setText((String.valueOf(mLastKnownLocation.getLatitude())));
                                                lg.setText((String.valueOf(mLastKnownLocation.getLongitude())));

                                            } else {
                                                final LocationRequest locationRequest = LocationRequest.create();
                                                locationRequest.setInterval(10000);
                                                locationRequest.setFastestInterval(5000);
                                                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                                                locationCallback = new LocationCallback() {
                                                    @Override
                                                    public void onLocationResult(LocationResult locationResult) {
                                                        super.onLocationResult(locationResult);
                                                        if (locationResult == null) {
                                                            return;
                                                        }
                                                        mLastKnownLocation = locationResult.getLastLocation();
                                                        lt.setText((String.valueOf(mLastKnownLocation.getLatitude())));
                                                        lg.setText((String.valueOf(mLastKnownLocation.getLongitude())));

                                                        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                                                    }
                                                };
                                                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

                                            }
                                        } else {
                                            Toast.makeText(NewProject.this, "unable to get the location", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

                task.addOnFailureListener(NewProject.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ResolvableApiException) {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            try {
                                resolvable.startResolutionForResult(NewProject.this, 51);
                            } catch (IntentSender.SendIntentException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });


            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(NewProject.this);

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(NewProject.this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(NewProject.this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                getDeviceLocation();
            }


            private void getDeviceLocation() {
                fusedLocationProviderClient.getLastLocation()
                        .addOnCompleteListener(new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                if (task.isSuccessful()) {
                                    mLastKnownLocation = task.getResult();
                                    if (mLastKnownLocation != null) {
                                        lt.setText((String.valueOf(mLastKnownLocation.getLatitude())));
                                        lg.setText((String.valueOf(mLastKnownLocation.getLongitude())));

                                    } else {
                                        final LocationRequest locationRequest = LocationRequest.create();
                                        locationRequest.setInterval(10000);
                                        locationRequest.setFastestInterval(5000);
                                        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                                        locationCallback = new LocationCallback() {
                                            @Override
                                            public void onLocationResult(LocationResult locationResult) {
                                                super.onLocationResult(locationResult);
                                                if (locationResult == null) {
                                                    return;
                                                }
                                                mLastKnownLocation = locationResult.getLastLocation();
                                                lt.setText((String.valueOf(mLastKnownLocation.getLatitude())));
                                                lg.setText((String.valueOf(mLastKnownLocation.getLongitude())));

                                                fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                                            }
                                        };
                                        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "unable to get the location", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        task.addOnFailureListener(NewProject.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(NewProject.this, 51);
                    } catch (IntentSender.SendIntentException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){

                    String pname = plantname.getText().toString();
                    String pcity = plantcity.getText().toString();
                    String l = lt.getText().toString();
                    String g = lg.getText().toString();
                    String rfname = refname.getText().toString();
                    String code = countrycode.getText().toString();
                    String rfphone = refphone.getText().toString();
                    if (pname.isEmpty()) {
                        plantname.setError("Field cannot empty");
                        return;
                    }
                    if (pcity.isEmpty()) {
                        plantcity.setError("Field cannot empty");
                        return;
                    }
                    if (rfname.isEmpty()) {
                        refname.setError("Field cannot empty");
                        return;
                    }
                    if (currentdate.isEmpty()){
                        date.setError("Field cannot empty");
                        return;
                    }
                    if (countrycode.length()==0||countrycode.length()==1){
                        countrycode.setError("Invalid");
                        return;
                    }
                    if ((countrycode.length() >= 5)) {
                        countrycode.setError("Invalid");
                        return;
                    }
                    if ((rfphone.length() <=6)) {
                        refphone.setError("Invalid");
                        return;
                    }
                    if ((rfphone.length() >= 16)) {
                        refphone.setError("Invalid");
                        return;
                    }

                    String type = s.getSelectedItem().toString();
                    if (type.equals("--select--")) {
                        Toast.makeText(getApplicationContext(), "Please select any one of option", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String typ = x.getSelectedItem().toString();
                    if (typ.equals("--select--")) {
                        Toast.makeText(getApplicationContext(), "Please select any one option", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (l.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please load current location", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (g.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please load current location", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent i = new Intent(NewProject.this, NewProject1.class);
                    i.putExtra("Plant name", pname);
                    i.putExtra("Plant city", pcity);
                    i.putExtra("Plant visit date",currentdate);
                    i.putExtra("Plant type", type);
                    i.putExtra("latitude", l);
                    i.putExtra("longitude", g);
                    i.putExtra("Purpose of visit",typ);
                    i.putExtra("Ref name", rfname);
                    i.putExtra("Ref phone", code+""+rfphone);
                    startActivity(i);
                }else {
                    Snackbar.make(linear, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("page","");
        if (name.equals("Surveyor")){
            getMenuInflater().inflate(R.menu.add, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.add) {


            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewProject.this);

            //AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

            // Setting Dialog Title
            alertDialog.setTitle("PLANT ID");
            alertDialog.setMessage("Enter id");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            final EditText input = new EditText(this);
            input.setLayoutParams(lp);
            alertDialog.setView(input);
            alertDialog.setIcon(R.drawable.ic_edit_black_24dp);

            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            String a=input.getText().toString();
                            if (!a.isEmpty()){
                                detailsverify(a);
                                }else {
                                input.setError("invalid");
                            }
                        }

                        private void detailsverify(final String a) {

                            refe = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets");
                            refe.child(a).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        Intent intent=new Intent(NewProject.this, ProjectEdit.class);
                                        intent.putExtra("id",a);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Invalid Id",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    });
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

}
