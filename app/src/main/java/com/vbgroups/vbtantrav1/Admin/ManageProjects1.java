package com.vbgroups.vbtantrav1.Admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vbgroups.vbtantrav1.Adapter.ManageDropDownListAdapter;
import com.vbgroups.vbtantrav1.MainActivity;
import com.vbgroups.vbtantrav1.R;
import com.vbgroups.vbtantrav1.Userpanel.UserPanel;
import com.vbgroups.vbtantrav1.Userpanel.UserPanel1;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Thread.sleep;

public class ManageProjects1 extends AppCompatActivity {
    TextView name, email, id, status, duration, job,assignedpro,dtxt;
    ProgressBar progressBar;
    String sid, semail, spassword, sname, sstatus="", sduration, sjob="",dur;
    Spinner ejob, estatus;
    LinearLayout linearLayout, smsg,fmsg,lnr,lnrasigned;
    CheckBox check;
    Button submit,edit,ok,closepro,dokk;
    ProgressBar mpro;


    private PopupWindow pw;
    private boolean expanded;
    Button createButto;
    FirebaseAuth auth;
    String[] mStrings;
    FirebaseUser fuser;
    ListView list;
    ListView myListView;
    CardView cardView;
    TextView txt;
    DatabaseReference reference,refdelete;
    ArrayList<String> items=new ArrayList<>();
    ArrayList<String> it=new ArrayList<>();

    //to  store information whether the selected values are displayed completely or in shortened representatn
    public static boolean[] checkSelected;	// store select/unselect information about the values in the list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_users);

        closepro=findViewById(R.id.closepro);
        lnrasigned=findViewById(R.id.lnrasigned);
        name = (TextView) findViewById(R.id.mname);
        email = (TextView) findViewById(R.id.memail);
        id = (TextView) findViewById(R.id.mid);
        assignedpro=(TextView)findViewById(R.id.assignedprojects);
        status = (TextView) findViewById(R.id.mstatus);
        estatus = findViewById(R.id.emstatus);
        ejob = findViewById(R.id.emjob);
        check = findViewById(R.id.echeckBox);
        submit = findViewById(R.id.esubmit);
        edit=findViewById(R.id.eedit);
        smsg=findViewById(R.id.successmsg);
        fmsg=findViewById(R.id.failmsg);
        lnr=findViewById(R.id.linearLayout1);
        ok=findViewById(R.id.ok);
        dokk=findViewById(R.id.okk);
        dtxt=findViewById(R.id.dtxt);
        cardView=(CardView) findViewById(R.id.procard);
        txt = (TextView) findViewById (R.id.nodata);
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

        if (sjob.equals("User")){
        lnrasigned.setVisibility(View.VISIBLE);
        }
        if (sjob.equals("Surveyor")){
            lnrasigned.setVisibility(View.VISIBLE);
        }

        assignedpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnr.setVisibility(View.GONE);
                ok.setVisibility(View.GONE);
                edit.setVisibility(View.GONE);
                myListView = (ListView) findViewById (R.id.listView);


                myListView.setVisibility(View.INVISIBLE);
                txt.setVisibility(View.VISIBLE);
                txt.setText("No Data Found");
                cardView.setVisibility(View.VISIBLE);
                closepro.setVisibility(View.VISIBLE);

                Query query;
                final ArrayList<String> myArrayList=new ArrayList<>();


                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {

                        TextView textView = (TextView) view.findViewById(R.id.textView);
                        final String text = textView.getText().toString();
                        if (sjob.equals("User")){
                            dtxt.setVisibility(View.VISIBLE);
                            dokk.setVisibility(View.VISIBLE);
                            dtxt.setText("Are you sure you want to remove"+" "+text+" "+"project");
                            dokk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    delete(text,position);
                                }
                            });
                        }else {
                            Intent intent=new Intent(ManageProjects1.this,UserPanel1.class);
                            intent.putExtra("id",text);
                            startActivity(intent);
                        }
                    }
                });

                if(sid != null) {
                   datalistview();
                }

            }

            private void delete(String text,int position) {
                refdelete= FirebaseDatabase.getInstance().getReference("Users").child(sid).child("projects").child(text);
                refdelete.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            datalistview();
                            myListView.setVisibility(View.INVISIBLE);
                            dokk.setVisibility(View.GONE);
                            dtxt.setVisibility(View.GONE);
                        }else {
                            dokk.setVisibility(View.GONE);
                            dtxt.setText("Try again");
                        }
                    }
                });

            }
        });

        if (!sjob.equals("Admin")&&!sjob.equals("Surveyor")){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setVisibility(View.GONE);
                lnr.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);

            }
        });
        }else {
            edit.setVisibility(View.GONE);
        }

    auth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = auth.getCurrentUser();
        if(firebaseUser != null) {
            String userid = firebaseUser.getUid();
            Query query = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets");
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String key = dataSnapshot.getKey();

                    items.add(key);
                    if (items != null) {
                        initialize();
                    }

                }


                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        closepro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dokk.setVisibility(View.GONE);
                dtxt.setVisibility(View.GONE);
                closepro.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                if (!sjob.equals("Admin")&&!sjob.equals("Surveyor")){
                    edit.setVisibility(View.VISIBLE);}
            }
        });
    }

    private void datalistview() {
        Query query;
        final ArrayList<String> myArrayList=new ArrayList<>();
        final CardView cardView=(CardView) findViewById(R.id.procard);
        myListView = (ListView) findViewById (R.id.listView);

        query = FirebaseDatabase.getInstance().getReference("Users").child(sid).child("projects");
        query.keepSynced(true);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {

                    String key = dataSnapshot.getKey();
                    cardView.setVisibility(View.VISIBLE);
                    closepro.setVisibility(View.VISIBLE);
                    myArrayList.add(key);
                    if (key!=null){
                        myListView.setVisibility(View.VISIBLE);
                    }
                    int a=myArrayList.size();

                        txt.setVisibility(View.GONE);
                        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_listview, R.id.textView,myArrayList);
                        myListView.setAdapter(myArrayAdapter);
                        myArrayAdapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void initialize(){

        checkSelected = new boolean[items.size()];
        //initialize all values of list to 'unselected' initially
        for (int i = 0; i < checkSelected.length; i++) {
            checkSelected[i] = false;
        }

        /*SelectBox is the TextView where the selected values will be displayed in the form of "Item 1 & 'n' more".
         * When this selectBox is clicked it will display all the selected values
         * and when clicked again it will display in shortened representation as before.
         * */
        final TextView tv = (TextView) findViewById(R.id.SelectBox);
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!expanded){
                    //display all selected values
                    String selected = "";
                    int flag = 0;
                    for (int i = 0; i < items.size(); i++) {
                        if (checkSelected[i] == true) {

                            selected += items.get(i);
                            selected += ",";
                            flag = 1;
                        }
                    }
                    if(flag==1)
                        tv.setText(selected);
                    expanded =true;
                }
                else{
                    //display shortened representation of selected values
                    tv.setText(ManageDropDownListAdapter.getSelected());
                    expanded = false;
                }
            }
        });

        createButto = (Button)findViewById(R.id.ok);
        createButto.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(!expanded){
                    //display all selected values
                    String selected = "";
                    for (int i = 0; i < items.size(); i++) {
                        if (checkSelected[i] == true) {
                            selected += items.get(i);
                            selected += ",";
                            it.add(items.get(i));
                            if (sid!=null) {
                                reference = FirebaseDatabase.getInstance().getReference("Users").child(sid);
                                final int finalI = i;
                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            String ite = items.get(finalI);
                                            dataSnapshot.getRef().child("projects").child(ite).setValue(ite).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                }
                                            });

                                                    smsg.setVisibility(View.VISIBLE);
                                                    smsg.postDelayed(new Runnable() {
                                                        public void run() {
                                                            smsg.setVisibility(View.GONE);
                                                        }
                                                    }, 3000);
                                                }else {
                                            fmsg.setVisibility(View.VISIBLE);
                                            fmsg.postDelayed(new Runnable() {
                                                public void run() {
                                                    smsg.setVisibility(View.GONE);
                                                }
                                            }, 3000);

                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        fmsg.setVisibility(View.VISIBLE);
                                        fmsg.postDelayed(new Runnable() {
                                            public void run() {
                                                smsg.setVisibility(View.GONE);
                                            }
                                        }, 3000);

                                    }
                                });

                            }
                        }
                    }
                }
            }
        });
        //onClickListener to initiate the dropDown list
        Button createButton = (Button)findViewById(R.id.create);
        createButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                initiatePopUp(items,tv);
            }
        });
    }

    /*
     * Function to set up the pop-up window which acts as drop-down list
     * */
    private void initiatePopUp(ArrayList<String> items, TextView tv){
        LayoutInflater inflater = (LayoutInflater)ManageProjects1.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //get the pop-up window i.e.  drop-down layout
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.pop_up_window, (ViewGroup)findViewById(R.id.PopUpView));

        //get the view to which drop-down layout is to be anchored
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.linearLayout1);
        pw = new PopupWindow(layout, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //Pop-up window background cannot be null if we want the pop-up to listen touch events outside its window
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setTouchable(true);

        //let pop-up be informed about touch events outside its window. This  should be done before setting the content of pop-up
        pw.setOutsideTouchable(true);
        pw.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        //dismiss the pop-up i.e. drop-down when touched anywhere outside the pop-up
        pw.setTouchInterceptor(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    pw.dismiss();
                    return true;
                }
                return false;
            }
        });
        pw.setContentView(layout);
        pw.showAsDropDown(layout1);
        list = (ListView) layout.findViewById(R.id.dropDownList);
        ManageDropDownListAdapter adapter = new ManageDropDownListAdapter(this, items, tv);
        list.setAdapter(adapter);
}
}