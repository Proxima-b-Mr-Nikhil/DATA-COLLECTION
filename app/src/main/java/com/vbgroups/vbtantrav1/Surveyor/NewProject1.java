package com.vbgroups.vbtantrav1.Surveyor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.vbgroups.vbtantrav1.InternetConnection;
import com.vbgroups.vbtantrav1.LoginActivity;
import com.vbgroups.vbtantrav1.MainActivity;
import com.vbgroups.vbtantrav1.R;
import com.vbgroups.vbtantrav1.ui.home.HomeFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static android.graphics.Bitmap.CompressFormat.JPEG;


public class NewProject1 extends AppCompatActivity {

    int count=0;int c=0;
    Button Submit, Add;
    CheckBox Checkbox;
    String STransformer_Name, SSource_name, SRated_voltage, SShort_circuit, SXR, STXR, SGenerator_name, SFeeder_name, SMake, SModel, SRating, SPrimary_voltage, SSV1, SSV2, SZ, SXD, SVoltage,
            SFeeder_no, SMax_voltage, SPrimaryCT, SSecondaryCT, SL1, ST1, SL2, ST2, SL3, SFrame_rating, STrip, SPlug, SLtpu, SLtd, SStpu, SStd, SInstantaneous, SConnected_from,
            SConnected_to, SCable_size, SNo_runs, SCable_length, SKW_rating, SFLA_rating, SEPN, SEPL, SEPC, SPTGround, SPTGrid, SEquipment_type, SEquipment_location, SEquipment_number, SAdd_source,
            SComing_from, SCapacitor_ID, SRated_KV, SKvar, SNo_Banks, SLoading, SEquipment_Cable, SInsulation_type, SRemark, SSoureceType, SGXR, SGXD, SGMake, SGModel, SGRating, SGPrimary_voltage, SGFeeder_name, SGGenerator_name, SMFeeder_name, SMMake, SMModel, SMVoltage, SMFLA_Rating, SMKW_Rating, SLFeeder_name, SLVoltage, SLRating, SLKWRating, SCFeeder_name, SRFeeder_no, SRFeeder_name, SRMake, SRModel, SRMax_Voltage, SCBFeeder_no,
            SCBFeeder_name, SCBMake, SCBModel, SCBMax_Voltage, SCBFrame_Rating, SBFeeder_name, SBVoltage, SBRating, SBShort_circuit, SCablecore, SCableMaterial, SLoadtype, SMotorType, SEpitMaintance, SphysicalStructure, SPitSurroundings, SEquipmain, SEquipSurroundings, SQualityIndex, STypeofHazard, SRecommendedAction, SBConductor_type, SBConfiguration, SBType;
    LinearLayout Source_layout;
    LinearLayout Add_source_layout;
    LinearLayout Add_image_layout;
    LinearLayout Add_layout;
    LinearLayout Checkbox_layout;
    LinearLayout AddImage;
    LinearLayout Remarks_layout;
    Spinner comp, sr, Earthpit_maintanance, Physical_structure, Equipment_maintanance, Pit_surroundings, Equipment_surroundings, Quality_index, Hazad, Action,
            Motor_type, Load_type, Cable_Material, Cable_Core, Conductor_type, Bus_configuration, Bus_type;
    EditText Transfromer_Name, Source_name, Rated_voltage, Short_circuit, XR, TXR, Generator_name, Feeder_name, Make, Model, Rating, Primary_voltage, SV1, SV2, Z, XD, Voltage,
            Feeder_no, Max_voltage, PrimaryCT, SecondaryCT, L1, T1, L2, T2, L3, Frame_rating, Trip, Plug, Ltpu, Ltd, Stpu, Std, Instantaneous, Connected_from,
            Connected_to, Cable_size, No_runs, Cable_length, KW_rating, FLA_rating, EPN, EPL, EPC, PTGround, PTGrid, Equipment_type, Equipment_location, Equipment_number, Add_source,
            Coming_from, Capacitor_ID, Rated_KV, Kvar, No_Banks, Loading, Equipment_Cable, Insulation_type, Remark, GXR, GXD, GMake, GModel, GRating, GPrimary_voltage, GFeeder_name, GGenerator_name, MFeeder_name, MMake, MModel, MVoltage, MFLA_Rating, MKW_Rating, LFeeder_name, LVoltage, LRating, LKWRating, CFeeder_name, RFeeder_no, RFeeder_name, RMake, RModel, RMax_Voltage, CBFeeder_no,
            CBFeeder_name, CBMake, CBModel, CBMax_Voltage, CBFrame_Rating, BFeeder_name, BVoltage, BRating, BShort_circuit;
    Uri imageUri;
    ScrollView linearLayout;
    TextView txtimg;
    ProgressDialog pd;
    DatabaseReference reference, ref, re;
    FirebaseAuth auth;
    FirebaseUser fuser;
    int pos;
    StorageReference storageReference;
    private StorageTask<UploadTask.TaskSnapshot> uploadTask;


    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView ivImage;
    private String userChoosenTask;
    Uri a, b;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        storageReference = FirebaseStorage.getInstance().getReference("Assets");
        final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(NewProject1.this);
        int c= Integer.parseInt(preference.getString("c",""));
        if (c!=0){

        }
        Coming_from = findViewById(R.id.coming_From);
        Capacitor_ID = findViewById(R.id.capacitor_ID);
        Rated_KV = findViewById(R.id.rated_KV);
        Kvar = findViewById(R.id.kvar);
        No_Banks = findViewById(R.id.no_Banks);
        GFeeder_name = findViewById(R.id.gfeeder_Name);
        GGenerator_name = findViewById(R.id.ggenerator_Name);
        GMake = findViewById(R.id.gmake);
        GModel = findViewById(R.id.gmodel);
        GRating = findViewById(R.id.grating);
        GPrimary_voltage = findViewById(R.id.gprimary_Voltage);
        GXD = findViewById(R.id.gxd);
        GXR = findViewById(R.id.gxr);
        Loading = findViewById(R.id.loading);
        Equipment_Cable = findViewById(R.id.equipment_Cable);
        Insulation_type = findViewById(R.id.insulation_Type);
        Motor_type = findViewById(R.id.motor_Type);
        Load_type = findViewById(R.id.load_Type);
        Cable_Core = findViewById(R.id.cable_Core);
        Cable_Material = findViewById(R.id.cable_Material);
        Conductor_type = findViewById(R.id.conductor_Type);
        Bus_configuration = findViewById(R.id.bus_Configuration);
        Bus_type = findViewById(R.id.bus_Type);
        MFeeder_name = findViewById(R.id.mfeeder_Name);
        MMake = findViewById(R.id.mmake);
        MModel = findViewById(R.id.mmodel);
        MVoltage = findViewById(R.id.mvoltage);
        MFLA_Rating = findViewById(R.id.mfla_Rating);
        MKW_Rating = findViewById(R.id.mkw_Rating);
        LFeeder_name = findViewById(R.id.lfeeder_Name);
        LVoltage = findViewById(R.id.lvoltage);
        LRating = findViewById(R.id.lrating);
        LKWRating = findViewById(R.id.lkw_Rating);
        CFeeder_name = findViewById(R.id.cfeeder_Name);
        RFeeder_name = findViewById(R.id.rfeeder_Name);
        RFeeder_no = findViewById(R.id.rfeeder_No);
        RMake = findViewById(R.id.rmake);
        RModel = findViewById(R.id.rmodel);
        RMax_Voltage = findViewById(R.id.rmax_Voltage);
        CBFeeder_name = findViewById(R.id.cbfeeder_Name);
        CBFeeder_no = findViewById(R.id.cbfeeder_No);
        CBMake = findViewById(R.id.cbmake);
        CBModel = findViewById(R.id.cbmodel);
        CBMax_Voltage = findViewById(R.id.cbmax_Voltage);
        CBFrame_Rating = findViewById(R.id.cbframe_Rating);
        BFeeder_name = findViewById(R.id.bfeeder_Name);
        BVoltage = findViewById(R.id.bvoltage);
        BRating = findViewById(R.id.brating);
        BShort_circuit = findViewById(R.id.bshort_Circuit);
        comp = findViewById(R.id.components);
        sr = findViewById(R.id.sourcetype);
        Transfromer_Name = findViewById(R.id.transformer_Name);
        Source_name = findViewById(R.id.source_Name);
        Rated_voltage = findViewById(R.id.rated_Voltage);
        Short_circuit = findViewById(R.id.short_Circuit);
        XR = findViewById(R.id.xr);
        TXR = findViewById(R.id.txr);
        Feeder_name = findViewById(R.id.feeder_Name);
        Source_layout = findViewById(R.id.source_Layout);
        Add_source_layout = findViewById(R.id.add_source_Layout);
        Make = findViewById(R.id.make);
        Model = findViewById(R.id.model);
        Rating = findViewById(R.id.rating);
        Primary_voltage = findViewById(R.id.primary_Voltage);
        SV1 = findViewById(R.id.sv1);
        SV2 = findViewById(R.id.sv2);
        Z = findViewById(R.id.z);
        PrimaryCT = findViewById(R.id.primaryCT);
        SecondaryCT = findViewById(R.id.secondaryCT);
        L1 = findViewById(R.id.l1);
        T1 = findViewById(R.id.t1);
        L2 = findViewById(R.id.l2);
        T2 = findViewById(R.id.t2);
        L3 = findViewById(R.id.l3);
        Trip = findViewById(R.id.trip);
        Plug = findViewById(R.id.plug);
        Ltpu = findViewById(R.id.ltpu);
        Ltd = findViewById(R.id.ltd);
        Stpu = findViewById(R.id.stpu);
        Std = findViewById(R.id.std);
        Instantaneous = findViewById(R.id.instantaneous);
        Connected_from = findViewById(R.id.connected_From);
        Connected_to = findViewById(R.id.connected_To);
        Cable_size = findViewById(R.id.cable_Size);
        No_runs = findViewById(R.id.no_Runs);
        Cable_length = findViewById(R.id.cable_Length);
        EPN = findViewById(R.id.epn);
        EPL = findViewById(R.id.epl);
        EPC = findViewById(R.id.epc);
        PTGround = findViewById(R.id.ptGround);
        PTGrid = findViewById(R.id.ptGrid);
        Earthpit_maintanance = findViewById(R.id.earthpit_Maintanance);
        Physical_structure = findViewById(R.id.physical_Structure);
        Pit_surroundings = findViewById(R.id.pit_Surroundings);
        Equipment_maintanance = findViewById(R.id.equipment_Maintanance);
        Equipment_type = findViewById(R.id.equipment_Type);
        Equipment_location = findViewById(R.id.equipment_Location);
        Equipment_number = findViewById(R.id.equipment_Number);
        Equipment_surroundings = findViewById(R.id.equipment_Surroundings);
        Quality_index = findViewById(R.id.quality_Index);
        Hazad = findViewById(R.id.hazad);
        Action = findViewById(R.id.action);
        Add_source = findViewById(R.id.add_Source);
        Add_image_layout = findViewById(R.id.add_image_layout);
        Add_layout = findViewById(R.id.add_layout);
        Checkbox_layout = findViewById(R.id.checkbox_layout);

        txtimg = findViewById(R.id.txtimage);
        Add = findViewById(R.id.add);
        linearLayout = findViewById(R.id.scroll);
        ivImage = findViewById(R.id.image);
        Remarks_layout = findViewById(R.id.remarks_layout);
        Remark = findViewById(R.id.remarks);
        Submit = findViewById(R.id.submit);
        Add = findViewById(R.id.add);
        Checkbox = findViewById(R.id.checkBox);

        Add_image_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seletImage();
            }
        });


        final View utility = (View) findViewById(R.id.layoututility);
        final View transformer = (View) findViewById(R.id.layouttransformer);
        final View generator = (View) findViewById(R.id.layoutgenerator);
        final View bus = (View) findViewById(R.id.layoutbus);
        final View circuit = (View) findViewById(R.id.layoutcircuit);
        final View relay = (View) findViewById(R.id.layoutrelay);
        final View cable = (View) findViewById(R.id.layoutcable);
        final View load = (View) findViewById(R.id.layoutload);
        final View capacitor = (View) findViewById(R.id.layoutcapaciotr);
        final View motor = (View) findViewById(R.id.layoutmotor);
        final View earthpit = (View) findViewById(R.id.layoutearthpit);
        final View visual = (View) findViewById(R.id.layoutvisual);


        String[] assetSpinner = new String[]{
                "--select--", "Utility", "Transformer", "Generator", "Bus", "Circuit Breaker", "Relay", "Cable"
                , "Load", "Motor", "Earth pit", "Visual inspection", "Capacitor Bank"
        };

        ArrayAdapter<String> asset_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, assetSpinner);
        asset_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comp.setAdapter(asset_select);
        comp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                pos = position;
                if (position > 0) {
                    Add_image_layout.setVisibility(View.VISIBLE);
                    Checkbox_layout.setVisibility(View.VISIBLE);
                    Add_layout.setVisibility(View.VISIBLE);
                    Remarks_layout.setVisibility(View.VISIBLE);
                    if (position > 1 && position < 10) {
                        Source_layout.setVisibility(View.VISIBLE);
                        Add_source_layout.setVisibility(View.VISIBLE);
                    } else {
                        Source_layout.setVisibility(View.GONE);
                        Add_source_layout.setVisibility(View.GONE);
                    }
                    switch (position) {
                        case 1:
                            utility.setVisibility(View.VISIBLE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {

                                        SSource_name = Source_name.getText().toString();
                                        SRated_voltage = Rated_voltage.getText().toString();
                                        SComing_from = Coming_from.getText().toString();
                                        SShort_circuit = Short_circuit.getText().toString();
                                        SXR = XR.getText().toString();
                                        if (SSource_name.isEmpty()) {

                                            Source_name.setError("Enter Source Name");
                                            return;
                                        }
                                        if (SRated_voltage.isEmpty()) {

                                            Rated_voltage.setError("Enter rated voltage");
                                            return;
                                        }
                                        if (SComing_from.isEmpty()) {

                                            Coming_from.setError("Enter 'coming from' value");
                                            return;
                                        }
                                        if (SShort_circuit.isEmpty()) {

                                            Short_circuit.setError("Enter short circuit KA");
                                            return;
                                        }

                                        if (SXR.isEmpty()) {

                                            XR.setError("Enter X/R ratio");
                                            return;
                                        }

                                        String a = txtimg.getText().toString();
                                        if (a.isEmpty()) {
                                            Snackbar.make(linearLayout, "Add image", Snackbar.LENGTH_LONG).show();

                                            return;
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {

                                        SSource_name = Source_name.getText().toString();
                                        SRated_voltage = Rated_voltage.getText().toString();
                                        SComing_from = Coming_from.getText().toString();
                                        SShort_circuit = Short_circuit.getText().toString();
                                        SXR = XR.getText().toString();
                                        if (SSource_name.isEmpty()) {

                                            Source_name.setError("Enter Source Name");
                                            return;
                                        }
                                        if (SRated_voltage.isEmpty()) {

                                            Rated_voltage.setError("Enter rated voltage");
                                            return;
                                        }
                                        if (SComing_from.isEmpty()) {

                                            Coming_from.setError("Enter 'coming from' value");
                                            return;
                                        }
                                        if (SShort_circuit.isEmpty()) {

                                            Short_circuit.setError("Enter short circuit KA");
                                            return;
                                        }

                                        if (SXR.isEmpty()) {

                                            XR.setError("Enter X/R ratio");
                                            return;
                                        }
                                        String a = txtimg.getText().toString();
                                        if (a.isEmpty()) {
                                            Snackbar.make(linearLayout, "Add image", Snackbar.LENGTH_LONG).show();

                                            return;
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 2:
                            transformer.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SFeeder_name = Feeder_name.getText().toString();
                                        STransformer_Name = Transfromer_Name.getText().toString();
                                        SMake = Make.getText().toString();
                                        SModel = Model.getText().toString();
                                        SRating = Rating.getText().toString();
                                        SPrimary_voltage = Primary_voltage.getText().toString();
                                        SSV1 = SV1.getText().toString();
                                        SSV2 = SV2.getText().toString();
                                        SZ = Z.getText().toString();
                                        STXR = TXR.getText().toString();
                                        if (SFeeder_name.isEmpty()) {

                                            Feeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (STransformer_Name.isEmpty()) {

                                            Transfromer_Name.setError("Enter Transformer Name");
                                            return;
                                        }
                                        if (SMake.isEmpty()) {

                                            Make.setError("Enter maker name");
                                            return;
                                        }
                                        if (SModel.isEmpty()) {

                                            Model.setError("Enter model value");
                                            return;
                                        }
                                        if (SRating.isEmpty()) {

                                            Rating.setError("Enter rating value");
                                            return;
                                        }
                                        if (SPrimary_voltage.isEmpty()) {

                                            Primary_voltage.setError("Enter Primary Voltage value");
                                            return;
                                        }
                                        if (SSV1.isEmpty()) {

                                            SV1.setError("Enter Secondary Voltage-1");
                                            return;
                                        }
                                        if (SSV2.isEmpty()) {

                                            SV2.setError("Enter Secondary Voltage-2");
                                            return;
                                        }
                                        if (SZ.isEmpty()) {

                                            Z.setError("Enter %Z value");
                                            return;
                                        }
                                        if (STXR.isEmpty()) {

                                            TXR.setError("Enter X/R ratio");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {

                                        SFeeder_name = Feeder_name.getText().toString();
                                        STransformer_Name = Transfromer_Name.getText().toString();
                                        SMake = Make.getText().toString();
                                        SModel = Model.getText().toString();
                                        SRating = Rating.getText().toString();
                                        SPrimary_voltage = Primary_voltage.getText().toString();
                                        SSV1 = SV1.getText().toString();
                                        SSV2 = SV2.getText().toString();
                                        SZ = Z.getText().toString();
                                        SGXR = GXR.getText().toString();
                                        STXR = TXR.getText().toString();
                                        if (SFeeder_name.isEmpty()) {

                                            Feeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (STransformer_Name.isEmpty()) {

                                            Transfromer_Name.setError("Enter Transformer Name");
                                            return;
                                        }
                                        if (SMake.isEmpty()) {

                                            Make.setError("Enter maker name");
                                            return;
                                        }
                                        if (SModel.isEmpty()) {

                                            Model.setError("Enter model value");
                                            return;
                                        }
                                        if (SRating.isEmpty()) {

                                            Rating.setError("Enter rating value");
                                            return;
                                        }
                                        if (SPrimary_voltage.isEmpty()) {

                                            Primary_voltage.setError("Enter Primary Voltage value");
                                            return;
                                        }
                                        if (SSV1.isEmpty()) {

                                            SV1.setError("Enter Secondary Voltage-1");
                                            return;
                                        }
                                        if (SSV2.isEmpty()) {

                                            SV2.setError("Enter Secondary Voltage-2");
                                            return;
                                        }
                                        if (SZ.isEmpty()) {

                                            Z.setError("Enter %Z value");
                                            return;
                                        }
                                        if (STXR.isEmpty()) {

                                            TXR.setError("Enter X/R ratio");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 3:
                            generator.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SGFeeder_name = GFeeder_name.getText().toString();
                                        SGGenerator_name = GGenerator_name.getText().toString();
                                        SGMake = GMake.getText().toString();
                                        SGModel = GModel.getText().toString();
                                        SGRating = GRating.getText().toString();
                                        SGPrimary_voltage = GPrimary_voltage.getText().toString();
                                        SGXD = GXD.getText().toString();
                                        SGXR = GXR.getText().toString();
                                        if (SGFeeder_name.isEmpty()) {
                                            Toast.makeText(NewProject1.this, "hhuyguygysi", Toast.LENGTH_LONG).show();
                                            GFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SGGenerator_name.isEmpty()) {

                                            GGenerator_name.setError("Enter Generator name");
                                            return;
                                        }
                                        if (SGMake.isEmpty()) {

                                            GMake.setError("Enter maker name");
                                            return;
                                        }
                                        if (SGModel.isEmpty()) {

                                            GModel.setError("Enter model value");
                                            return;
                                        }
                                        if (SGRating.isEmpty()) {

                                            GRating.setError("Enter rating value");
                                            return;
                                        }
                                        if (SGPrimary_voltage.isEmpty()) {

                                            Primary_voltage.setError("Enter Primary Voltage value");
                                            return;
                                        }
                                        if (SGXD.isEmpty()) {

                                            GXD.setError("Enter Xd'' value");
                                            return;
                                        }
                                        if (SGXR.isEmpty()) {

                                            GXR.setError("Enter X/R ratio");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SGFeeder_name = GFeeder_name.getText().toString();
                                        SGGenerator_name = GGenerator_name.getText().toString();
                                        SGMake = GMake.getText().toString();
                                        SGModel = GModel.getText().toString();
                                        SGRating = GRating.getText().toString();
                                        SGPrimary_voltage = GPrimary_voltage.getText().toString();
                                        SGXD = GXD.getText().toString();
                                        SGXR = GXR.getText().toString();
                                        if (SGFeeder_name.isEmpty()) {
                                            GFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SGGenerator_name.isEmpty()) {

                                            GGenerator_name.setError("Enter Generator name");
                                            return;
                                        }
                                        if (SGMake.isEmpty()) {

                                            GMake.setError("Enter maker name");
                                            return;
                                        }
                                        if (SGModel.isEmpty()) {

                                            GModel.setError("Enter model value");
                                            return;
                                        }
                                        if (SGRating.isEmpty()) {

                                            GRating.setError("Enter rating value");
                                            return;
                                        }
                                        if (SGPrimary_voltage.isEmpty()) {

                                            GPrimary_voltage.setError("Enter Primary Voltage value");
                                            return;
                                        }
                                        if (SGXD.isEmpty()) {

                                            GXD.setError("Enter Xd'' value");
                                            return;
                                        }
                                        if (SGXR.isEmpty()) {

                                            GXR.setError("Enter X/R ratio");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });

                            break;
                        case 4:
                            bus.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {

                                        SBFeeder_name = BFeeder_name.getText().toString();
                                        SBVoltage = BVoltage.getText().toString();
                                        SBRating = BRating.getText().toString();
                                        SBShort_circuit = BShort_circuit.getText().toString();

                                        SBConductor_type = Conductor_type.getSelectedItem().toString();
                                        SBConfiguration = Bus_configuration.getSelectedItem().toString();
                                        SBType = Bus_type.getSelectedItem().toString();

                                        if (SBFeeder_name.isEmpty()) {
                                            BFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SBVoltage.isEmpty()) {

                                            BVoltage.setError("Enter voltage value");
                                            return;
                                        }
                                        if (SBRating.isEmpty()) {

                                            BRating.setError("Enter rating value");
                                            return;
                                        }
                                        if (SBShort_circuit.isEmpty()) {
                                            BShort_circuit.setError("Enter short circuit KA");
                                            return;
                                        }
                                        if (SBConductor_type.isEmpty()) {
                                            Toast.makeText(getApplicationContext(), "select one option from conductor type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SBConfiguration.isEmpty()) {
                                            Toast.makeText(getApplicationContext(), "select one option from configuration", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SBType.isEmpty()) {

                                            Toast.makeText(getApplicationContext(), "select one option from type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SBFeeder_name = BFeeder_name.getText().toString();
                                        SBVoltage = BVoltage.getText().toString();
                                        SBRating = BRating.getText().toString();
                                        SBShort_circuit = BShort_circuit.getText().toString();

                                        SBConductor_type = Conductor_type.getSelectedItem().toString();
                                        SBConfiguration = Bus_configuration.getSelectedItem().toString();
                                        SBType = Bus_type.getSelectedItem().toString();

                                        if (SBFeeder_name.isEmpty()) {

                                            BFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SBVoltage.isEmpty()) {

                                            BVoltage.setError("Enter voltage value");
                                            return;
                                        }
                                        if (SBRating.isEmpty()) {

                                            BRating.setError("Enter rating value");
                                            return;
                                        }
                                        if (SBShort_circuit.isEmpty()) {
                                            BShort_circuit.setError("Enter short circuit KA");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        if (SBConductor_type.isEmpty()) {
                                            Toast.makeText(getApplicationContext(), "select one option from conductor type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SBConfiguration.isEmpty()) {
                                            Toast.makeText(getApplicationContext(), "select one option from configuration", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SBType.isEmpty()) {

                                            Toast.makeText(getApplicationContext(), "select one option from type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 5:
                            circuit.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SCBFeeder_no = CBFeeder_no.getText().toString();
                                        SCBFeeder_name = CBFeeder_name.getText().toString();
                                        SCBMake = CBMake.getText().toString();
                                        SCBModel = CBModel.getText().toString();
                                        SCBMax_Voltage = CBMax_Voltage.getText().toString();
                                        SCBFrame_Rating = CBFrame_Rating.getText().toString();
                                        STrip = Trip.getText().toString();
                                        SPlug = Plug.getText().toString();
                                        SLtpu = Ltpu.getText().toString();
                                        SLtd = Ltd.getText().toString();
                                        SStpu = Stpu.getText().toString();
                                        SStd = Std.getText().toString();
                                        SInstantaneous = Instantaneous.getText().toString();
                                        if (SCBFeeder_no.isEmpty()) {
                                            CBFeeder_no.setError("Enter Feeder no");
                                            return;
                                        }
                                        if (SCBFeeder_name.isEmpty()) {

                                            CBFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SCBMake.isEmpty()) {

                                            CBMake.setError("Enter maker name");
                                            return;
                                        }
                                        if (SCBModel.isEmpty()) {

                                            CBModel.setError("Enter model value");
                                            return;
                                        }
                                        if (SCBMax_Voltage.isEmpty()) {

                                            CBMax_Voltage.setError("Enter Max Voltage value");
                                            return;
                                        }
                                        if (SCBFrame_Rating.isEmpty()) {

                                            CBFrame_Rating.setError("Enter Frame Rating value");
                                            return;
                                        }
                                        if (STrip.isEmpty()) {

                                            Trip.setError("Enter Trip value");
                                            return;
                                        }
                                        if (SPlug.isEmpty()) {

                                            Plug.setError("Enter Plug value");
                                            return;
                                        }
                                        if (SLtpu.isEmpty()) {

                                            Ltpu.setError("Enter Ltpu value");
                                            return;
                                        }
                                        if (SLtd.isEmpty()) {

                                            Ltd.setError("Enter Ltd value");
                                            return;
                                        }
                                        if (SStpu.isEmpty()) {

                                            Stpu.setError("Enter Stpu value");
                                            return;
                                        }
                                        if (SStd.isEmpty()) {

                                            Std.setError("Enter Std value");
                                            return;
                                        }
                                        if (SInstantaneous.isEmpty()) {

                                            Instantaneous.setError("Enter Instantaneous value");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SCBFeeder_no = CBFeeder_no.getText().toString();
                                        SCBFeeder_name = CBFeeder_name.getText().toString();
                                        SCBMake = CBMake.getText().toString();
                                        SCBModel = CBModel.getText().toString();
                                        SCBMax_Voltage = CBMax_Voltage.getText().toString();
                                        SCBFrame_Rating = CBFrame_Rating.getText().toString();
                                        STrip = Trip.getText().toString();
                                        SPlug = Plug.getText().toString();
                                        SLtpu = Ltpu.getText().toString();
                                        SLtd = Ltd.getText().toString();
                                        SStpu = Stpu.getText().toString();
                                        SStd = Std.getText().toString();
                                        SInstantaneous = Instantaneous.getText().toString();
                                        if (SCBFeeder_no.isEmpty()) {
                                            CBFeeder_no.setError("Enter Feeder no");
                                            return;
                                        }
                                        if (SCBFeeder_name.isEmpty()) {

                                            CBFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SCBMake.isEmpty()) {

                                            CBMake.setError("Enter maker name");
                                            return;
                                        }
                                        if (SCBModel.isEmpty()) {

                                            CBModel.setError("Enter model value");
                                            return;
                                        }
                                        if (SCBMax_Voltage.isEmpty()) {

                                            CBMax_Voltage.setError("Enter Max Voltage value");
                                            return;
                                        }
                                        if (SCBFrame_Rating.isEmpty()) {

                                            CBFrame_Rating.setError("Enter Frame Rating value");
                                            return;
                                        }
                                        if (STrip.isEmpty()) {

                                            Trip.setError("Enter Trip value");
                                            return;
                                        }
                                        if (SPlug.isEmpty()) {

                                            Plug.setError("Enter Plug value");
                                            return;
                                        }
                                        if (SLtpu.isEmpty()) {

                                            Ltpu.setError("Enter Ltpu value");
                                            return;
                                        }
                                        if (SLtd.isEmpty()) {

                                            Ltd.setError("Enter Ltd value");
                                            return;
                                        }
                                        if (SStpu.isEmpty()) {

                                            Stpu.setError("Enter Stpu value");
                                            return;
                                        }
                                        if (SStd.isEmpty()) {

                                            Std.setError("Enter Std value");
                                            return;
                                        }
                                        if (SInstantaneous.isEmpty()) {

                                            Instantaneous.setError("Enter Instantaneous value");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 6:
                            relay.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SRFeeder_no = RFeeder_no.getText().toString();
                                        SRFeeder_name = RFeeder_name.getText().toString();
                                        SRMake = RMake.getText().toString();
                                        SRModel = RModel.getText().toString();
                                        SRMax_Voltage = RMax_Voltage.getText().toString();
                                        SPrimaryCT = PrimaryCT.getText().toString();
                                        SSecondaryCT = SecondaryCT.getText().toString();
                                        SL1 = L1.getText().toString();
                                        ST1 = T1.getText().toString();
                                        SL2 = L2.getText().toString();
                                        ST2 = T2.getText().toString();
                                        SL3 = L3.getText().toString();
                                        if (SRFeeder_no.isEmpty()) {
                                            RFeeder_no.setError("Enter Feeder no");
                                            return;
                                        }
                                        if (SRFeeder_name.isEmpty()) {

                                            RFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SRMake.isEmpty()) {

                                            RMake.setError("Enter maker name");
                                            return;
                                        }
                                        if (SRModel.isEmpty()) {

                                            RModel.setError("Enter model value");
                                            return;
                                        }
                                        if (SRMax_Voltage.isEmpty()) {

                                            RMax_Voltage.setError("Enter Max Voltage value");
                                            return;
                                        }
                                        if (SPrimaryCT.isEmpty()) {

                                            PrimaryCT.setError("Enter Primary CT value");
                                            return;
                                        }
                                        if (SSecondaryCT.isEmpty()) {

                                            SecondaryCT.setError("Enter Secondary CT value");
                                            return;
                                        }
                                        if (SL1.isEmpty()) {

                                            L1.setError("Enter l> value");
                                            return;
                                        }
                                        if (ST1.isEmpty()) {

                                            T1.setError("Enter t> value");
                                            return;
                                        }
                                        if (SL2.isEmpty()) {

                                            L2.setError("Enter l>> value");
                                            return;
                                        }
                                        if (ST2.isEmpty()) {

                                            T2.setError("Enter t>> value");
                                            return;
                                        }
                                        if (SL3.isEmpty()) {

                                            L3.setError("Ente l>>> value");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SRFeeder_no = RFeeder_no.getText().toString();
                                        SRFeeder_name = RFeeder_name.getText().toString();
                                        SRMake = RMake.getText().toString();
                                        SRModel = RModel.getText().toString();
                                        SRMax_Voltage = RMax_Voltage.getText().toString();
                                        SPrimaryCT = PrimaryCT.getText().toString();
                                        SSecondaryCT = SecondaryCT.getText().toString();
                                        SL1 = L1.getText().toString();
                                        ST1 = T1.getText().toString();
                                        SL2 = L2.getText().toString();
                                        ST2 = T2.getText().toString();
                                        SL3 = L3.getText().toString();
                                        if (SRFeeder_no.isEmpty()) {
                                            RFeeder_no.setError("Enter Feeder no");
                                            return;
                                        }
                                        if (SRFeeder_name.isEmpty()) {

                                            RFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SRMake.isEmpty()) {

                                            RMake.setError("Enter maker name");
                                            return;
                                        }
                                        if (SRModel.isEmpty()) {

                                            RModel.setError("Enter model value");
                                            return;
                                        }
                                        if (SRMax_Voltage.isEmpty()) {

                                            RMax_Voltage.setError("Enter Max Voltage value");
                                            return;
                                        }
                                        if (SPrimaryCT.isEmpty()) {

                                            PrimaryCT.setError("Enter Primary CT value");
                                            return;
                                        }
                                        if (SSecondaryCT.isEmpty()) {

                                            SecondaryCT.setError("Enter Secondary CT value");
                                            return;
                                        }
                                        if (SL1.isEmpty()) {

                                            L1.setError("Enter l> value");
                                            return;
                                        }
                                        if (ST1.isEmpty()) {

                                            T1.setError("Enter t> value");
                                            return;
                                        }
                                        if (SL2.isEmpty()) {

                                            L2.setError("Enter l>> value");
                                            return;
                                        }
                                        if (ST2.isEmpty()) {

                                            T2.setError("Enter t>> value");
                                            return;
                                        }
                                        if (SL3.isEmpty()) {

                                            L3.setError("Ente l>>> value");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 7:
                            cable.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SCFeeder_name = CFeeder_name.getText().toString();
                                        SConnected_from = Connected_from.getText().toString();
                                        SConnected_to = Connected_to.getText().toString();
                                        SCable_size = Cable_size.getText().toString();
                                        SNo_runs = No_runs.getText().toString();
                                        SCable_length = Cable_length.getText().toString();
                                        SInsulation_type = Insulation_type.getText().toString();
                                        SCablecore = Cable_Core.getSelectedItem().toString();
                                        SCableMaterial = Cable_Material.getSelectedItem().toString();
                                        if (SCFeeder_name.isEmpty()) {

                                            CFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SConnected_from.isEmpty()) {

                                            Connected_from.setError("Enter connected from value");
                                            return;
                                        }
                                        if (SConnected_to.isEmpty()) {

                                            Connected_to.setError("Enter connected to value");
                                            return;
                                        }
                                        if (SCable_size.isEmpty()) {

                                            Cable_size.setError("Enter cable size");
                                            return;
                                        }
                                        if (SNo_runs.isEmpty()) {

                                            No_runs.setError("Enter no of runs");
                                            return;
                                        }
                                        if (SCable_length.isEmpty()) {

                                            Cable_length.setError("Enter cable length");
                                            return;
                                        }
                                        if (SCablecore.equals("Cable Core")) {
                                            Toast.makeText(getApplicationContext(), "Please select Cable Core type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SCableMaterial.equals("Cable Material")) {
                                            Toast.makeText(getApplicationContext(), "Please select Cable Material type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SInsulation_type.isEmpty()) {
                                            Insulation_type.setError("Enter Insulation Type");
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SCFeeder_name = CFeeder_name.getText().toString();
                                        SConnected_from = Connected_from.getText().toString();
                                        SConnected_to = Connected_to.getText().toString();
                                        SCable_size = Cable_size.getText().toString();
                                        SNo_runs = No_runs.getText().toString();
                                        SCable_length = Cable_length.getText().toString();
                                        SInsulation_type = Insulation_type.getText().toString();
                                        SCablecore = Cable_Core.getSelectedItem().toString();
                                        SCableMaterial = Cable_Material.getSelectedItem().toString();
                                        if (SCFeeder_name.isEmpty()) {

                                            CFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SConnected_from.isEmpty()) {

                                            Connected_from.setError("Enter connected from value");
                                            return;
                                        }
                                        if (SConnected_to.isEmpty()) {

                                            Connected_to.setError("Enter connected to value");
                                            return;
                                        }
                                        if (SCable_size.isEmpty()) {

                                            Cable_size.setError("Enter cable size");
                                            return;
                                        }
                                        if (SNo_runs.isEmpty()) {

                                            No_runs.setError("Enter no of runs");
                                            return;
                                        }
                                        if (SCable_length.isEmpty()) {

                                            Cable_length.setError("Enter cable length");
                                            return;
                                        }
                                        if (SCablecore.equals("Cable Core")) {
                                            Toast.makeText(getApplicationContext(), "Please select Cable Core type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SCableMaterial.equals("Cable Material")) {
                                            Toast.makeText(getApplicationContext(), "Please select Cable Material type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SInsulation_type.isEmpty()) {
                                            Insulation_type.setError("Enter Insulation Type");
                                            return;
                                        }

                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 8:
                            load.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SLFeeder_name = LFeeder_name.getText().toString();
                                        SLVoltage = LVoltage.getText().toString();
                                        SLRating = LRating.getText().toString();
                                        SLKWRating = LKWRating.getText().toString();
                                        SLoadtype = Load_type.getSelectedItem().toString();
                                        if (SLFeeder_name.isEmpty()) {

                                            LFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SLVoltage.isEmpty()) {

                                            LVoltage.setError("Enter voltage value");
                                            return;
                                        }
                                        if (SLRating.isEmpty()) {

                                            LRating.setError("Enter rating value");
                                            return;
                                        }
                                        if (SLKWRating.isEmpty()) {

                                            LKWRating.setError("Enter KW rating");
                                            return;
                                        }
                                        if (SLoadtype.equals("Load Type")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Load type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {

                                        SLFeeder_name = LFeeder_name.getText().toString();
                                        SLVoltage = LVoltage.getText().toString();
                                        SLRating = LRating.getText().toString();
                                        SLKWRating = LKWRating.getText().toString();
                                        SLoadtype = Load_type.getSelectedItem().toString();
                                        if (SLFeeder_name.isEmpty()) {

                                            LFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SLVoltage.isEmpty()) {

                                            LVoltage.setError("Enter voltage value");
                                            return;
                                        }
                                        if (SLRating.isEmpty()) {

                                            LRating.setError("Enter rating value");
                                            return;
                                        }
                                        if (SLKWRating.isEmpty()) {

                                            LKWRating.setError("Enter KW rating");
                                            return;
                                        }
                                        if (SLoadtype.equals("Load Type")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Load type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 9:
                            motor.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SMFeeder_name = MFeeder_name.getText().toString();
                                        SMMake = MMake.getText().toString();
                                        SMModel = MModel.getText().toString();
                                        SMVoltage = MVoltage.getText().toString();
                                        SMFLA_Rating = MFLA_Rating.getText().toString();
                                        SMKW_Rating = MKW_Rating.getText().toString();
                                        SMotorType = Motor_type.getSelectedItem().toString();
                                        if (SMFeeder_name.isEmpty()) {

                                            MFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SMMake.isEmpty()) {

                                            MMake.setError("Enter maker name");
                                            return;
                                        }
                                        if (SMModel.isEmpty()) {

                                            MModel.setError("Enter model value");
                                            return;
                                        }
                                        if (SMVoltage.isEmpty()) {

                                            MVoltage.setError("Enter voltage value");
                                            return;
                                        }
                                        if (SMFLA_Rating.isEmpty()) {

                                            MFLA_Rating.setError("Enter FLA rating");
                                            return;
                                        }
                                        if (SMKW_Rating.isEmpty()) {

                                            MKW_Rating.setError("Enter KW rating");
                                            return;
                                        }
                                        if (SMotorType.equals("Motor Type")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from motor type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }

                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SMFeeder_name = MFeeder_name.getText().toString();
                                        SMMake = MMake.getText().toString();
                                        SMModel = MModel.getText().toString();
                                        SMVoltage = MVoltage.getText().toString();
                                        SMFLA_Rating = MFLA_Rating.getText().toString();
                                        SMKW_Rating = MKW_Rating.getText().toString();
                                        SMotorType = Motor_type.getSelectedItem().toString();
                                        if (SMFeeder_name.isEmpty()) {

                                            MFeeder_name.setError("Enter Feeder Name");
                                            return;
                                        }
                                        if (SMMake.isEmpty()) {

                                            MMake.setError("Enter maker name");
                                            return;
                                        }
                                        if (SMModel.isEmpty()) {

                                            MModel.setError("Enter model value");
                                            return;
                                        }
                                        if (SMVoltage.isEmpty()) {

                                            MVoltage.setError("Enter voltage value");
                                            return;
                                        }
                                        if (SMFLA_Rating.isEmpty()) {

                                            MFLA_Rating.setError("Enter FLA rating");
                                            return;
                                        }
                                        if (SMKW_Rating.isEmpty()) {

                                            MKW_Rating.setError("Enter KW rating");
                                            return;
                                        }
                                        if (SMotorType.equals("Motor Type")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from motor type", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        SSoureceType = sr.getSelectedItem().toString();
                                        if (SSoureceType.equals("--select--")) {
                                            SAdd_source = Add_source.getText().toString();
                                            if (SAdd_source.isEmpty()) {
                                                Toast.makeText(getApplicationContext(), "Please select one option or add source", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 10:
                            earthpit.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            visual.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SEPN = EPN.getText().toString();
                                        SEPL = EPL.getText().toString();
                                        SEPC = EPC.getText().toString();
                                        SPTGround = PTGround.getText().toString();
                                        SPTGrid = PTGrid.getText().toString();
                                        SEpitMaintance = Earthpit_maintanance.getSelectedItem().toString();
                                        SphysicalStructure = Physical_structure.getSelectedItem().toString();
                                        SPitSurroundings = Pit_surroundings.getSelectedItem().toString();
                                        if (SEPN.isEmpty()) {

                                            EPN.setError("Enter Earth pit number");
                                            return;
                                        }
                                        if (SEPL.isEmpty()) {

                                            EPL.setError("Enter Earth pit location");
                                            return;
                                        }
                                        if (SEPC.isEmpty()) {

                                            EPC.setError("Enter earth pit connection");
                                            return;
                                        }
                                        if (SPTGround.isEmpty()) {

                                            PTGround.setError("Enter Pit to Ground Resistance");
                                            return;
                                        }
                                        if (SPTGrid.isEmpty()) {

                                            PTGrid.setError("Enter Pit to Grid Resistance");
                                            return;
                                        }

                                        if (SEpitMaintance.equals("Earthpit Maintance")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Earthpit Maintance", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SphysicalStructure.equals("Physical Structure")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Physical Structure", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SPitSurroundings.equals("Pit Surroundings")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Earthpit Surroundings", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SEPN = EPN.getText().toString();
                                        SEPL = EPL.getText().toString();
                                        SEPC = EPC.getText().toString();
                                        SPTGround = PTGround.getText().toString();
                                        SPTGrid = PTGrid.getText().toString();
                                        SEpitMaintance = Earthpit_maintanance.getSelectedItem().toString();
                                        SphysicalStructure = Physical_structure.getSelectedItem().toString();
                                        SPitSurroundings = Pit_surroundings.getSelectedItem().toString();
                                        if (SEPN.isEmpty()) {

                                            EPN.setError("Enter Earth pit number");
                                            return;
                                        }
                                        if (SEPL.isEmpty()) {

                                            EPL.setError("Enter Earth pit location");
                                            return;
                                        }
                                        if (SEPC.isEmpty()) {

                                            EPC.setError("Enter earth pit connection");
                                            return;
                                        }
                                        if (SPTGround.isEmpty()) {

                                            PTGround.setError("Enter Pit to Ground Resistance");
                                            return;
                                        }
                                        if (SPTGrid.isEmpty()) {

                                            PTGrid.setError("Enter Pit to Grid Resistance");
                                            return;
                                        }
                                        if (SEpitMaintance.equals("Earthpit Maintance")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Earthpit Maintance", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SphysicalStructure.equals("Physical Structure")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Physical Structure", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SPitSurroundings.equals("Pit Surroundings")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Earthpit Surroundings", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 11:
                            visual.setVisibility(View.VISIBLE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            capacitor.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SEquipment_type = Equipment_type.getText().toString();
                                        SEquipment_location = Equipment_location.getText().toString();
                                        SEquipment_number = Equipment_number.getText().toString();


                                        SEquipmain = Equipment_maintanance.getSelectedItem().toString();
                                        SEquipSurroundings = Equipment_surroundings.getSelectedItem().toString();
                                        SQualityIndex = Quality_index.getSelectedItem().toString();
                                        STypeofHazard = Hazad.getSelectedItem().toString();
                                        SRecommendedAction = Action.getSelectedItem().toString();

                                        if (SEquipment_type.isEmpty()) {
                                            Equipment_type.setError("");
                                            return;
                                        }
                                        if (SEquipment_location.isEmpty()) {
                                            Equipment_location.setError("");
                                            return;
                                        }
                                        if (SEquipment_number.isEmpty()) {
                                            Equipment_number.setError("");
                                            return;
                                        }
                                        if (SEquipmain.equals("Equipment Maintenance")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Equiment Maintenance", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SEquipSurroundings.equals("Equipment Surroundings")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Equiment Surroundings", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SQualityIndex.equals("Quality Index")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Quality Index", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (STypeofHazard.equals("Type of Hazard")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from type of hazard", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SRecommendedAction.equals("Recommended Action")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Recommended Action", Toast.LENGTH_SHORT).show();
                                            return;
                                        }

                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {
                                        SEquipment_type = Equipment_type.getText().toString();
                                        SEquipment_location = Equipment_location.getText().toString();
                                        SEquipment_number = Equipment_number.getText().toString();

                                        SEquipmain = Equipment_maintanance.getSelectedItem().toString();
                                        SEquipSurroundings = Equipment_surroundings.getSelectedItem().toString();
                                        SQualityIndex = Quality_index.getSelectedItem().toString();
                                        STypeofHazard = Hazad.getSelectedItem().toString();
                                        SRecommendedAction = Action.getSelectedItem().toString();
                                        if (SEquipment_type.isEmpty()) {
                                            Equipment_type.setText("");
                                            return;
                                        }
                                        if (SEquipment_location.isEmpty()) {
                                            Equipment_location.setText("");
                                            return;
                                        }
                                        if (SEquipment_number.isEmpty()) {
                                            Equipment_number.setText("");
                                            return;
                                        }
                                        if (SEquipmain.equals("Equipment Maintenance")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Equiment Maintenance", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SEquipSurroundings.equals("Equipment Surroundings")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Equiment Surroundings", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SQualityIndex.equals("Quality Index")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Quality Index", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (STypeofHazard.equals("Type of Hazard")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from type of hazard", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (SRecommendedAction.equals("Recommended Action")) {
                                            Toast.makeText(getApplicationContext(), "Please select one option from Recommended Action", Toast.LENGTH_SHORT).show();
                                            return;
                                        }


                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                        case 12:
                            capacitor.setVisibility(View.VISIBLE);
                            visual.setVisibility(View.GONE);
                            utility.setVisibility(View.GONE);
                            transformer.setVisibility(View.GONE);
                            generator.setVisibility(View.GONE);
                            bus.setVisibility(View.GONE);
                            circuit.setVisibility(View.GONE);
                            relay.setVisibility(View.GONE);
                            cable.setVisibility(View.GONE);
                            load.setVisibility(View.GONE);
                            motor.setVisibility(View.GONE);
                            earthpit.setVisibility(View.GONE);
                            Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {

                                        SCapacitor_ID = Capacitor_ID.getText().toString();
                                        SRated_KV = Rated_KV.getText().toString();
                                        SKvar = Kvar.getText().toString();
                                        SNo_Banks = No_Banks.getText().toString();
                                        SLoading = Loading.getText().toString();
                                        SEquipment_Cable = Equipment_Cable.getText().toString();
                                        if (SCapacitor_ID.isEmpty()) {

                                            Capacitor_ID.setError("Enter Equipment type");
                                            return;
                                        }
                                        if (SRated_KV.isEmpty()) {
                                            Rated_KV.setError("Enter Equipment location");
                                            return;
                                        }
                                        if (SKvar.isEmpty()) {

                                            Kvar.setError("Enter Equipment number");
                                            return;
                                        }
                                        if (SNo_Banks.isEmpty()) {

                                            No_Banks.setError("Enter Equipment number");
                                            return;
                                        }
                                        if (SLoading.isEmpty()) {

                                            Loading.setError("Enter Equipment number");
                                            return;
                                        }
                                        if (SEquipment_Cable.isEmpty()) {

                                            Equipment_Cable.setError("Enter Equipment number");
                                            return;
                                        }
                                        upload();
                                        Intent intent = new Intent(getApplicationContext(), NewProject.class);
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (Checkbox.isChecked()) {

                                        SCapacitor_ID = Capacitor_ID.getText().toString();
                                        SRated_KV = Rated_KV.getText().toString();
                                        SKvar = Kvar.getText().toString();
                                        SNo_Banks = No_Banks.getText().toString();
                                        SLoading = Loading.getText().toString();
                                        SEquipment_Cable = Equipment_Cable.getText().toString();
                                        if (SCapacitor_ID.isEmpty()) {

                                            Capacitor_ID.setError("Enter Equipment type");
                                            return;
                                        }
                                        if (SRated_KV.isEmpty()) {

                                            Rated_KV.setError("Enter Equipment location");
                                            return;
                                        }
                                        if (SKvar.isEmpty()) {

                                            Kvar.setError("Enter Equipment number");
                                            return;
                                        }
                                        if (SNo_Banks.isEmpty()) {

                                            No_Banks.setError("Enter Equipment number");
                                            return;
                                        }
                                        if (SLoading.isEmpty()) {

                                            Loading.setError("Enter Equipment number");
                                            return;
                                        }
                                        if (SEquipment_Cable.isEmpty()) {

                                            Equipment_Cable.setError("Enter Equipment number");
                                            return;
                                        }
                                        upload();
                                        Intent intent = getIntent();
                                        startActivity(intent);
                                    } else {
                                        Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });
                            break;
                    }

                } else {
                    Add_image_layout.setVisibility(View.GONE);
                    Checkbox_layout.setVisibility(View.GONE);
                    Add_layout.setVisibility(View.GONE);
                    Remarks_layout.setVisibility(View.GONE);
                    utility.setVisibility(View.GONE);
                    transformer.setVisibility(View.GONE);
                    generator.setVisibility(View.GONE);
                    bus.setVisibility(View.GONE);
                    circuit.setVisibility(View.GONE);
                    relay.setVisibility(View.GONE);
                    cable.setVisibility(View.GONE);
                    load.setVisibility(View.GONE);
                    motor.setVisibility(View.GONE);
                    earthpit.setVisibility(View.GONE);
                    visual.setVisibility(View.GONE);
                    capacitor.setVisibility(View.GONE);
                    Source_layout.setVisibility(View.GONE);
                    Add_source_layout.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        String[] sourceSpinner = new String[]{
                "--select--", "Utility", "Transformer", "Generator", "Bus", "Circuit Breaker", "Relay", "Cable"
                , "Load", "Motor"
        };

        ArrayAdapter<String> source_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, sourceSpinner);
        source_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sr.setAdapter(source_select);

        String[] earthSpinner = new String[]{
                "Earthpit Maintance", "Worst", "Bad", "Good"

        };

        ArrayAdapter<String> earth_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, earthSpinner);
        earth_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Earthpit_maintanance.setAdapter(earth_select);

        String[] physicalSpinner = new String[]{
                "Physical Structure", "Worst", "Bad", "Good"

        };

        ArrayAdapter<String> physical_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, physicalSpinner);
        physical_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Physical_structure.setAdapter(physical_select);

        String[] pitSpinner = new String[]{
                "Pit Surroundings", "Worst", "Bad", "Good"

        };

        ArrayAdapter<String> pit_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, pitSpinner);
        pit_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Pit_surroundings.setAdapter(pit_select);

        String[] equipmentSpinner = new String[]{
                "Equipment Maintenance", "Worst", "Bad", "Good"

        };

        ArrayAdapter<String> equipment_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, equipmentSpinner);
        equipment_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Equipment_maintanance.setAdapter(equipment_select);

        String[] equipSpinner = new String[]{
                "Equipment Surroundings", "Worst", "Bad", "Good"

        };

        ArrayAdapter<String> equip_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, equipSpinner);
        equip_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Equipment_surroundings.setAdapter(equip_select);

        String[] qualitySpinner = new String[]{
                "Quality Index", "Worst", "Bad", "Good"

        };

        ArrayAdapter<String> quality_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, qualitySpinner);
        quality_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Quality_index.setAdapter(quality_select);

        String[] hazardSpinner = new String[]{
                "Type of Hazard", "Electrical", "Civil", "Mechanical", "Behavioral", "Chemical", "Operational", "others"

        };

        ArrayAdapter<String> hazard_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, hazardSpinner);
        hazard_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Hazad.setAdapter(hazard_select);

        String[] actionSpinner = new String[]{
                "Recommended Action", "Replace", "Repair", "Regular Maintenance Required"

        };

        ArrayAdapter<String> action_select = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, actionSpinner);
        action_select.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Action.setAdapter(action_select);

        String[] motorSpinner = new String[]{
                "Motor Type", "Regular", "Standby", "Spare"

        };

        ArrayAdapter<String> motor_type = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, motorSpinner);
        motor_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Motor_type.setAdapter(motor_type);

        String[] loadSpinner = new String[]{
                "Load Type", "Regular", "Standby", "Spare"
        };

        ArrayAdapter<String> load_type = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, loadSpinner);
        load_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Load_type.setAdapter(load_type);
        Load_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        String[] cableSpinner = new String[]{
                "Cable Core", "1", "2", "3", "3.5", "4"

        };

        ArrayAdapter<String> core_type = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, cableSpinner);
        core_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Cable_Core.setAdapter(core_type);

        String[] cableMSpinner = new String[]{
                "Cable Material", "Copper", "Aluminium"

        };

        ArrayAdapter<String> core_material = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, cableMSpinner);
        core_material.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Cable_Material.setAdapter(core_material);

        String[] busSpinner = new String[]{
                "Conductor Type", "Copper", "Aluminium"

        };

        ArrayAdapter<String> bus_Conductor = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, busSpinner);
        bus_Conductor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Conductor_type.setAdapter(bus_Conductor);

        String[] busCSpinner = new String[]{
                "Configuration", "VCB", "HCB", "VCBB"

        };

        ArrayAdapter<String> bus_Configuration = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, busCSpinner);
        bus_Configuration.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bus_configuration.setAdapter(bus_Configuration);

        String[] busTSpinner = new String[]{
                "TYPE", "MCC", "Switchgear", "Switchboard", "Switchrack", "Panelboard", "Cable Bus", "Open Air"

        };

        ArrayAdapter<String> bus_type = new ArrayAdapter<String>(NewProject1.this,
                android.R.layout.simple_spinner_item, busTSpinner);
        bus_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bus_type.setAdapter(bus_type);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), NewProject.class);
        startActivity(intent);
    }

    private void upload() {

        String _imgName=System.currentTimeMillis()/10000+"_img.png";
        storageReference = FirebaseStorage.getInstance().getReference("Assests images").child(_imgName);

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        if (fuser != null) {
            pd = new ProgressDialog(NewProject1.this);
            pd.setMessage("Uploading");
            pd.show();

            fuser = FirebaseAuth.getInstance().getCurrentUser();
            auth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = auth.getCurrentUser();
            assert firebaseUser != null;
            final String userid = firebaseUser.getUid();
            Intent intent = getIntent();
            String a = intent.getStringExtra("Plant name");
            String b = intent.getStringExtra("Plant city");
            String c = intent.getStringExtra("Plant visit date");

            reference = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets").child(a + "_" + b + "_" + c);
            ref = reference.child("Assets").push();

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Intent intent = getIntent();
                    String a = intent.getStringExtra("Plant name");
                    String b = intent.getStringExtra("Plant city");
                    String c = intent.getStringExtra("Plant visit date");
                    String d = intent.getStringExtra("Plant type");
                    String e = intent.getStringExtra("latitude");
                    String f = intent.getStringExtra("longitude");
                    String g = intent.getStringExtra("Purpose of visit");
                    String h = intent.getStringExtra("Ref name");
                    String i = intent.getStringExtra("Ref phone");

                    dataSnapshot.getRef().child("Plantname").setValue(a);
                    dataSnapshot.getRef().child("Plantcity").setValue(b);
                    dataSnapshot.getRef().child("Plantvisitdate").setValue(c);
                    dataSnapshot.getRef().child("Planttype").setValue(d);
                    dataSnapshot.getRef().child("latitude").setValue(e);
                    dataSnapshot.getRef().child("longitude").setValue(f);
                    dataSnapshot.getRef().child("Purposeofvisit").setValue(g);
                    dataSnapshot.getRef().child("Refname").setValue(h);
                    dataSnapshot.getRef().child("Refphone").setValue(i);
                    dataSnapshot.getRef().child("id").setValue(userid);

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String pushid=dataSnapshot.getKey();
                            SRemark = Remark.getText().toString();
                            switch (pos) {
                                case 1:

                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    dataSnapshot.getRef().child("Asset_name").setValue("Utility");
                                    dataSnapshot.getRef().child("XR_ratio").setValue(SXR);
                                    dataSnapshot.getRef().child("Rated_voltage").setValue(SRated_voltage);
                                    dataSnapshot.getRef().child("Short_circuit").setValue(SShort_circuit);
                                    dataSnapshot.getRef().child("Source_name").setValue(SSource_name);
                                    dataSnapshot.getRef().child("Coming_from").setValue(SComing_from);
                                    break;
                                case 2:

                                    dataSnapshot.getRef().child("Asset_name").setValue("Transformer");
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    dataSnapshot.getRef().child("XR_ratio").setValue(STXR);
                                    dataSnapshot.getRef().child("Make").setValue(SMake);
                                    dataSnapshot.getRef().child("Model").setValue(SModel);
                                    dataSnapshot.getRef().child("Rating").setValue(SRating);
                                    dataSnapshot.getRef().child("Primary_voltage").setValue(SPrimary_voltage);
                                    dataSnapshot.getRef().child("Secondary_voltage_1").setValue(SSV1);
                                    dataSnapshot.getRef().child("Secondary_voltage_2").setValue(SSV2);
                                    dataSnapshot.getRef().child("Z").setValue(SZ);
                                    dataSnapshot.getRef().child("Feeder_name").setValue(SFeeder_name);
                                    dataSnapshot.getRef().child("Transformer_name").setValue(STransformer_Name);
                                    if (!SSoureceType.equals("--select--")) {
                                        dataSnapshot.getRef().child("Source_type").setValue(SSoureceType);
                                    } else {
                                        dataSnapshot.getRef().child("New_source_type").setValue(SAdd_source);
                                    }
                                    break;
                                case 3:
                                    dataSnapshot.getRef().child("Asset_name").setValue("Generator");
                                    dataSnapshot.getRef().child("XR_ratio").setValue(SGXR);
                                    dataSnapshot.getRef().child("Xd").setValue(SGXD);
                                    dataSnapshot.getRef().child("Make").setValue(SGMake);
                                    dataSnapshot.getRef().child("Model").setValue(SGModel);
                                    dataSnapshot.getRef().child("Rating").setValue(SGRating);
                                    dataSnapshot.getRef().child("Primary_voltage").setValue(SGPrimary_voltage);
                                    dataSnapshot.getRef().child("Feeder_name").setValue(SGFeeder_name);
                                    dataSnapshot.getRef().child("Generator_name").setValue(SGGenerator_name);

                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    if (!SSoureceType.equals("--select--")) {
                                        dataSnapshot.getRef().child("Source_type").setValue(SSoureceType);
                                    } else {
                                        dataSnapshot.getRef().child("New_source_type").setValue(SAdd_source);
                                    }
                                    break;
                                case 4:
                                    dataSnapshot.getRef().child("Asset_name").setValue("Bus");
                                    dataSnapshot.getRef().child("Short_circuit").setValue(SBShort_circuit);
                                    dataSnapshot.getRef().child("Rating").setValue(SBRating);
                                    dataSnapshot.getRef().child("Voltage").setValue(SBVoltage);
                                    dataSnapshot.getRef().child("Feeder_name").setValue(SBFeeder_name);
                                    dataSnapshot.getRef().child("Conductor_type").setValue(SBConductor_type);
                                    dataSnapshot.getRef().child("Configuration").setValue(SBConfiguration);
                                    dataSnapshot.getRef().child("Type").setValue(SBType);
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    if (!SSoureceType.equals("--select--")) {
                                        dataSnapshot.getRef().child("Source_type").setValue(SSoureceType);
                                    } else {
                                        dataSnapshot.getRef().child("New_source_type").setValue(SAdd_source);
                                    }
                                    break;
                                case 5:
                                    dataSnapshot.getRef().child("Asset_name").setValue("Circuit Breaker");
                                    dataSnapshot.getRef().child("Make").setValue(SCBMake);
                                    dataSnapshot.getRef().child("Model").setValue(SCBModel);
                                    dataSnapshot.getRef().child("Maximum_voltage").setValue(SCBMax_Voltage);
                                    dataSnapshot.getRef().child("Feeder_name").setValue(SCBFeeder_name);
                                    dataSnapshot.getRef().child("Feeder_no").setValue(SCBFeeder_no);
                                    dataSnapshot.getRef().child("Frame_rating").setValue(SCBFrame_Rating);
                                    dataSnapshot.getRef().child("Trip").setValue(STrip);
                                    dataSnapshot.getRef().child("Plug").setValue(SPlug);
                                    dataSnapshot.getRef().child("Ltpu").setValue(SLtpu);
                                    dataSnapshot.getRef().child("Ltd").setValue(SLtd);
                                    dataSnapshot.getRef().child("Stpu").setValue(SStpu);
                                    dataSnapshot.getRef().child("Std").setValue(SStd);
                                    dataSnapshot.getRef().child("Instaneous").setValue(SInstantaneous);
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    if (!SSoureceType.equals("--select--")) {
                                        dataSnapshot.getRef().child("Source_type").setValue(SSoureceType);
                                    } else {
                                        dataSnapshot.getRef().child("New_source_type").setValue(SAdd_source);
                                    }
                                    break;
                                case 6:

                                    dataSnapshot.getRef().child("Asset_name").setValue("Relay");
                                    dataSnapshot.getRef().child("Make").setValue(SRMake);
                                    dataSnapshot.getRef().child("Model").setValue(SRModel);
                                    dataSnapshot.getRef().child("Maximum_voltage").setValue(SRMax_Voltage);
                                    dataSnapshot.getRef().child("Feeder_no").setValue(SRFeeder_no);
                                    dataSnapshot.getRef().child("Feeder_name").setValue(SRFeeder_name);
                                    dataSnapshot.getRef().child("Primary_ct").setValue(SPrimaryCT);
                                    dataSnapshot.getRef().child("Secondary_ct").setValue(SSecondaryCT);
                                    dataSnapshot.getRef().child("l1").setValue(SL1);
                                    dataSnapshot.getRef().child("t1").setValue(ST1);
                                    dataSnapshot.getRef().child("l2").setValue(SL2);
                                    dataSnapshot.getRef().child("t2").setValue(ST2);
                                    dataSnapshot.getRef().child("l3").setValue(SL3);
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    if (!SSoureceType.equals("--select--")) {
                                        dataSnapshot.getRef().child("Source_type").setValue(SSoureceType);
                                    } else {
                                        dataSnapshot.getRef().child("New_source_type").setValue(SAdd_source);
                                    }
                                    break;
                                case 7:
                                    dataSnapshot.getRef().child("Asset_name").setValue("Cable");
                                    dataSnapshot.getRef().child("Insulation_type").setValue(SInsulation_type);
                                    dataSnapshot.getRef().child("Feeder_name").setValue(SCFeeder_name);
                                    dataSnapshot.getRef().child("Connected_from").setValue(SConnected_from);
                                    dataSnapshot.getRef().child("Connected_to").setValue(SConnected_to);
                                    dataSnapshot.getRef().child("Cable_size").setValue(SCable_size);
                                    dataSnapshot.getRef().child("No_of_runs").setValue(SNo_runs);
                                    dataSnapshot.getRef().child("Cable_length").setValue(SCable_length);
                                    dataSnapshot.getRef().child("Cable_core").setValue(SCablecore);
                                    dataSnapshot.getRef().child("Cable_material").setValue(SCableMaterial);

                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    if (!SSoureceType.equals("--select--")) {
                                        dataSnapshot.getRef().child("Source_type").setValue(SSoureceType);
                                    } else {
                                        dataSnapshot.getRef().child("New_source_type").setValue(SAdd_source);
                                    }
                                    break;
                                case 8:
                                    dataSnapshot.getRef().child("Asset_name").setValue("Load");
                                    dataSnapshot.getRef().child("Load_type").setValue(SLoadtype);

                                    dataSnapshot.getRef().child("KW_rating").setValue(SLKWRating);
                                    dataSnapshot.getRef().child("Rating").setValue(SLRating);
                                    dataSnapshot.getRef().child("Voltage").setValue(SLVoltage);
                                    dataSnapshot.getRef().child("Feeder_name").setValue(SLFeeder_name);
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    if (!SSoureceType.equals("--select--")) {
                                        dataSnapshot.getRef().child("Source_type").setValue(SSoureceType);
                                    } else {
                                        dataSnapshot.getRef().child("New_source_type").setValue(SAdd_source);
                                    }
                                    break;
                                case 9:

                                    dataSnapshot.getRef().child("Asset_name").setValue("Motor");
                                    dataSnapshot.getRef().child("Motor_type").setValue(SMotorType);
                                    dataSnapshot.getRef().child("FLA_rating").setValue(SMFLA_Rating);
                                    dataSnapshot.getRef().child("KW_rating").setValue(SMKW_Rating);
                                    dataSnapshot.getRef().child("Voltage").setValue(SMVoltage);
                                    dataSnapshot.getRef().child("Make").setValue(SMMake);
                                    dataSnapshot.getRef().child("Model").setValue(SMModel);
                                    dataSnapshot.getRef().child("Feeder_name").setValue(SMFeeder_name);
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    if (!SSoureceType.equals("--select--")) {
                                        dataSnapshot.getRef().child("Source_type").setValue(SSoureceType);
                                    } else {
                                        dataSnapshot.getRef().child("New_source_type").setValue(SAdd_source);
                                    }
                                    break;
                                case 10:
                                    dataSnapshot.getRef().child("Asset_name").setValue("Earthpit");
                                    dataSnapshot.getRef().child("Earthpit_maintenance").setValue(SEpitMaintance);
                                    dataSnapshot.getRef().child("Physical_structure").setValue(SphysicalStructure);
                                    dataSnapshot.getRef().child("Pit_surroundings").setValue(SPitSurroundings);

                                    dataSnapshot.getRef().child("Earthpit_number").setValue(SEPN);
                                    dataSnapshot.getRef().child("Earthpitlocation").setValue(SEPL);
                                    dataSnapshot.getRef().child("Earthpit_connectedto").setValue(SEPC);
                                    dataSnapshot.getRef().child("Pit_to_ground_resistance").setValue(SPTGround);
                                    dataSnapshot.getRef().child("Pit_to_grid_resistance").setValue(SPTGrid);
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    break;
                                case 11:
                                    dataSnapshot.getRef().child("Asset_name").setValue("Visual inspection");
                                    SEquipmain = Equipment_maintanance.getSelectedItem().toString();
                                    SEquipSurroundings = Equipment_surroundings.getSelectedItem().toString();
                                    SQualityIndex = Quality_index.getSelectedItem().toString();
                                    STypeofHazard = Hazad.getSelectedItem().toString();
                                    SRecommendedAction = Action.getSelectedItem().toString();

                                    dataSnapshot.getRef().child("Equipment_maintenance").setValue(SEquipmain);
                                    dataSnapshot.getRef().child("Equipment_surroundings").setValue(SEquipSurroundings);
                                    dataSnapshot.getRef().child("Quality_index").setValue(SQualityIndex);
                                    dataSnapshot.getRef().child("Equipment_type").setValue(SEquipment_type);

                                    dataSnapshot.getRef().child("Type_of_hazard").setValue(STypeofHazard);
                                    dataSnapshot.getRef().child("Recommended_action").setValue(SRecommendedAction);

                                    dataSnapshot.getRef().child("Equipment_location").setValue(SEquipment_location);
                                    dataSnapshot.getRef().child("Equipment_number").setValue(SEquipment_number);
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    break;
                                case 12:
                                    dataSnapshot.getRef().child("Asset_name").setValue("Capacitor bank");
                                    dataSnapshot.getRef().child("Capacitor_id").setValue(SCapacitor_ID);
                                    dataSnapshot.getRef().child("Rated_kv").setValue(SRated_KV);
                                    dataSnapshot.getRef().child("Kvar_bank").setValue(SKvar);
                                    dataSnapshot.getRef().child("No_of_banks").setValue(SNo_Banks);
                                    dataSnapshot.getRef().child("Loading").setValue(SLoading);
                                    dataSnapshot.getRef().child("Equipment_cable").setValue(SEquipment_Cable);
                                    if (!SRemark.isEmpty()) {
                                        dataSnapshot.getRef().child("Remark").setValue(SRemark);
                                    }
                                    break;
                            }


                            dataSnapshot.getRef().child("uploaderid").setValue(userid);

                            fuser = FirebaseAuth.getInstance().getCurrentUser();
                            auth = FirebaseAuth.getInstance();
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            final String userid = firebaseUser.getUid();
                            Intent intent = getIntent();
                            String w = intent.getStringExtra("Plant name");
                            String x = intent.getStringExtra("Plant city");
                            String z = intent.getStringExtra("Plant visit date");
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                            reference.child("projects").child(w + "_" + x + "_" + z).setValue(w + "_" + x + "_" + z);
                            storageReference = FirebaseStorage.getInstance().getReference("Assets");
                            reference.keepSynced(true);
                            SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(NewProject1.this);
                            final Uri ur = Uri.parse(preference.getString("uri", ""));
                            if (ur != null) {

                                Bitmap bitmap=null;
                                try{
                                    bitmap = MediaStore.Images.Media.getBitmap(NewProject1.this.getContentResolver(), ur);
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                                //compress image

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                                byte[] data = baos.toByteArray();


                                uploadTask = storageReference.putBytes(data);
                                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                        if (!task.isSuccessful()) {
                                            pd.dismiss();
                                            Snackbar.make(linearLayout, "failed", Snackbar.LENGTH_LONG).show();
                                            throw Objects.requireNonNull(task.getException());
                                        }
                                        return storageReference.getDownloadUrl();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            Uri downloadUri = task.getResult();
                                            String mUri = Objects.requireNonNull(downloadUri).toString();
                                            HashMap<String, Object> map = new HashMap<>();
                                            map.put("itemimage", "" + mUri);
                                            ref.updateChildren(map);
                                            pd.dismiss();
                                            Snackbar.make(linearLayout, "Uploaded successfully", Snackbar.LENGTH_LONG).show();

                                        } else {
                                            Toast.makeText(NewProject1.this, "Failed!", Toast.LENGTH_SHORT).show();
                                            pd.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(NewProject1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Snackbar.make(linearLayout, "failed", Snackbar.LENGTH_LONG).show();

                                        pd.dismiss();
                                    }
                                });
                            } else {

                                pd.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Snackbar.make(linearLayout, "failed", Snackbar.LENGTH_LONG).show();

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar.make(linearLayout, "failed", Snackbar.LENGTH_LONG).show();

                }
            });
        }
    }

    private void seletImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(NewProject1.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(NewProject1.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Uri uri = data.getData();
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(NewProject1.this);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("uri", String.valueOf(uri));
        editor.apply();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
            ivImage.setImageBitmap(bitmap);
            assert uri != null;
            txtimg.setText(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ivImage.setVisibility(View.VISIBLE);
        ivImage.setImageBitmap(bm);
        assert data != null;
        a=data.getData();
        assert a != null;
        File file= new File(Objects.requireNonNull(a.getPath()));
        txtimg.setText(file.getName());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(NewProject1.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("uri", String.valueOf(a));
        editor.apply();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("page","");
        if (name.equals("Surveyor")){
            getMenuInflater().inflate(R.menu.share, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.share) {

            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_menu_share)
                    .setTitle("Plant Id")
                    .setMessage("Are you sure you want to share project id ?" +
                            "\nNOTE: Don't share with out adding atleast asset.")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            whatsapp();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void whatsapp() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        Intent intent = getIntent();
        String a = intent.getStringExtra("Plant name");
        String b = intent.getStringExtra("Plant city");
        String c = intent.getStringExtra("Plant visit date");

        whatsappIntent.putExtra(Intent.EXTRA_TEXT,"Plant id :"+a+"_"+b+"_"+c+"\n"+"\n\n\nDo not share this with any one other then sub surveyors\n\nVB Tantra" );
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(),"Whatsapp have not been installed.",Toast.LENGTH_SHORT).show();
        }
    }

}