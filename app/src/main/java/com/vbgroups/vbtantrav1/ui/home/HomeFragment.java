package com.vbgroups.vbtantrav1.ui.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.vbgroups.vbtantrav1.Admin.AddUsers;
import com.vbgroups.vbtantrav1.Admin.AllUsers;
import com.vbgroups.vbtantrav1.Admin.ManageProjects;
import com.vbgroups.vbtantrav1.Helpdesk.UserMessagesActivity;
import com.vbgroups.vbtantrav1.Model.Attachfiles;
import com.vbgroups.vbtantrav1.R;
import com.vbgroups.vbtantrav1.Surveyor.MyProjects;
import com.vbgroups.vbtantrav1.Surveyor.NewProject;
import com.vbgroups.vbtantrav1.Surveyor.NewProject1;
import com.vbgroups.vbtantrav1.Userpanel.UserPanel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.LENGTH_SHORT;

public class HomeFragment extends Fragment {
 private Button b1,b2,b3;
 private TextView t1,t2,t3;
 private LinearLayout main,main2,L1,L2,L3,L4;
    private LinearLayout attach,myprojet,newproject,help,pdf,txt,image,excel,word,docx;
    private final static int PICK_PDF_CODE = 2342;
    private final static int PICK_TXT = 234;
    private final static int IMAGE_REQUEST = 23;
    private final static int PICK_WORD = 2;
    private final static int PICK_XL = 1;
    private final static int PICK_DOCX = 10;
    private EditText filename,plantname,city;
    private   Button submit;
    FirebaseAuth auth;
    private String fname,pname,pcity,id;
    private  LinearLayout lnr,customlnr;
    private Uri imageUri;
    private String currentdate;
    private StorageReference pdfStorageReference,imgstorageReference,txtstorageReference,docxstorageReference,docstorageReference,xlsstorageReference;
    private DatabaseReference pdfDatabaseReference,imgDatabaseRreference,txtDatabaseReference,docxDatabaseReference,docDatabaseReference,xlsDatabaseReference;
    private FirebaseUser fuser;
    private StorageTask<UploadTask.TaskSnapshot> uploadTask;
 private HomeViewModel homeViewModel;

    @SuppressLint("SimpleDateFormat")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        assert fuser != null;
        id = fuser.getUid();
        L4=root.findViewById(R.id.Lhelp);

        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), UserMessagesActivity.class);
                startActivity(intent);
            }
        });
        Date cDate = new Date();
        currentdate = new SimpleDateFormat("dd-MM-yyyy").format(cDate);



        main=root.findViewById(R.id.frag1);
        main2=root.findViewById(R.id.frag2);

        b1=root.findViewById(R.id.Bone);
        b2=root.findViewById(R.id.Btwo);
        b3=root.findViewById(R.id.Bthree);

        t1=root.findViewById(R.id.Tone);
        t2=root.findViewById(R.id.Ttwo);
        t3=root.findViewById(R.id.Tthree);

        L1=root.findViewById(R.id.Lone);
        L2=root.findViewById(R.id.Ltwo);
        L3=root.findViewById(R.id.Lthree);
        L4=root.findViewById(R.id.Lhelp);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireActivity());
        String name = preferences.getString("page","");
        if (!name.isEmpty()) {
            if (name.equals("Admin")) {
                admin();
            }
            if (name.equals("User")) {
                Intent intent=new Intent(requireActivity(), UserPanel.class);
                startActivity(intent);
            }
            if (name.equals("SuperAdmin")) {
                superadmin();
            }
            if (name.equals("Surveyor")) {
                surveyor();
            }

        }


        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), UserMessagesActivity.class);
                startActivity(intent);
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }


    private void superadmin() {
        b1.setBackground(getResources().getDrawable(R.drawable.ic_person_outline_black_24dp));
        b2.setBackground(getResources().getDrawable(R.drawable.ic_widgets_black_24dp));
        b3.setBackground(getResources().getDrawable(R.drawable.ic_account_balance_black_24dp));

        t1.setText("User Panel");
        t2.setText("Admin");
        t3.setText("Surveyor");

        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), UserPanel.class);
                startActivity(intent);
            }
        });
        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b1.setBackground(getResources().getDrawable(R.drawable.ic_playlist_add_black_24dp));
                b2.setBackground(getResources().getDrawable(R.drawable.ic_person_add_black_24dp));
                b3.setBackground(getResources().getDrawable(R.drawable.ic_person_outline_black_24dp));

                t1.setText("Manage Projects");
                t2.setText("Add Users");
                t3.setText("All Users");
                setMargins(t1, 50, -12,0,0);
                L1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(requireActivity(), ManageProjects.class);
                        startActivity(intent);
                    }
                });
                L2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(requireActivity(), AddUsers.class);
                        startActivity(intent);
                    }
                });
                L3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(requireActivity(), AllUsers.class);
                        startActivity(intent);
                    }
                });
                L4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(requireActivity(), UserMessagesActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackground(getResources().getDrawable(R.drawable.ic_person_outline_black_24dp));
                b2.setBackground(getResources().getDrawable(R.drawable.ic_attach_file_black_24dp));
                b3.setBackground(getResources().getDrawable(R.drawable.ic_account_balance_black_24dp));
                setMargins(t1, 22, -12,0,0);
                t1.setText("My Projects");
                t2.setText("Attach Files");
                t3.setText("New Project");
                L1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(requireActivity(), MyProjects.class);
                        startActivity(intent);
                    }
                });
                L2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        attachfiles(v);
                    }
                });
                L3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(requireActivity(), NewProject.class);
                        startActivity(intent);
                    }
                });
                L4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(requireActivity(), UserMessagesActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), UserMessagesActivity.class);
                startActivity(intent);
            }
        });


    }

    private void surveyor() {
        b1.setBackground(getResources().getDrawable(R.drawable.ic_person_outline_black_24dp));
        b2.setBackground(getResources().getDrawable(R.drawable.ic_attach_file_black_24dp));
        b3.setBackground(getResources().getDrawable(R.drawable.ic_account_balance_black_24dp));
        setMargins(t1, 22, 0,0,0);
        t1.setText("My Projects");
        t2.setText("Attach Files");
        t3.setText("New Project");

        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), MyProjects.class);
                startActivity(intent);
            }
        });
        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pdfStorageReference = FirebaseStorage.getInstance().getReference("Attached files"+"/"+"pdf");
                imgstorageReference = FirebaseStorage.getInstance().getReference("Attached files"+"/"+"images");
                txtstorageReference = FirebaseStorage.getInstance().getReference("Attached files"+"/"+"txt");
                docxstorageReference = FirebaseStorage.getInstance().getReference("Attached files"+"/"+"docx");
                docstorageReference = FirebaseStorage.getInstance().getReference("Attached files"+"/"+"doc");
                xlsstorageReference = FirebaseStorage.getInstance().getReference("Attached files"+"/"+"xls");

                attachfiles(v);
            }
        });
        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), NewProject.class);
                startActivity(intent);
            }
        });
        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), UserMessagesActivity.class);
                startActivity(intent);
            }
        });

    }

    private void attachfiles(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        ViewGroup viewGroup =v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.attachfiletype, viewGroup, false);
        filename=dialogView.findViewById(R.id.filename);
        plantname=dialogView.findViewById(R.id.plant);
        city=dialogView.findViewById(R.id.city);
        submit=dialogView.findViewById(R.id.submit);
        customlnr=dialogView.findViewById(R.id.c);


        pdf = (LinearLayout) dialogView.findViewById (R.id.pdf);
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    pdf.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg) );
                } else {
                    pdf.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg));
                }
                filename.setVisibility(View.VISIBLE);
                plantname.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fname=filename.getText().toString();
                        pname=plantname.getText().toString();
                        pcity=city.getText().toString();
                        if (fname.isEmpty()){
                            return;
                        }
                        if (pname.isEmpty()){
                            return;
                        }

                        if (pcity.isEmpty()){
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_CODE);

                    }
                });

                docx.setBackgroundResource(0);
                txt.setBackgroundResource(0);
                word.setBackgroundResource(0);
                excel.setBackgroundResource(0);
                image.setBackgroundResource(0);
            }
        });

        txt = (LinearLayout)dialogView. findViewById (R.id.txtfile);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    txt.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg) );
                } else {
                    txt.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg));
                }
                filename.setVisibility(View.VISIBLE);
                plantname.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fname=filename.getText().toString();
                        pname=plantname.getText().toString();
                        pcity=city.getText().toString();
                        if (fname.isEmpty()){
                            return;
                        }
                        if (pname.isEmpty()){
                            return;
                        }
                        if (pcity.isEmpty()){
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setType( "text/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select word"), PICK_TXT);

                    }
                });


                docx.setBackgroundResource(0);
                pdf.setBackgroundResource(0);
                word.setBackgroundResource(0);
                excel.setBackgroundResource(0);
                image.setBackgroundResource(0);
            }
        });
        excel = (LinearLayout) dialogView.findViewById (R.id.excelfile);
        excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename.setVisibility(View.VISIBLE);
                plantname.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    excel.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg) );
                } else {
                    excel.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg));
                }
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fname=filename.getText().toString();
                        pname=plantname.getText().toString();
                        pcity=city.getText().toString();
                        if (fname.isEmpty()){
                            return;
                        }
                        if (pname.isEmpty()){
                            return;
                        }

                        if (pcity.isEmpty()){
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setType( "application/vnd.ms-excel");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select xls"), PICK_XL);
                    }
                });

                docx.setBackgroundResource(0);
                txt.setBackgroundResource(0);
                word.setBackgroundResource(0);
                pdf.setBackgroundResource(0);
                image.setBackgroundResource(0);

            }
        });
        word = (LinearLayout) dialogView.findViewById (R.id.wordfile);
        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename.setVisibility(View.VISIBLE);
                plantname.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    word.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg));
                } else {
                    word.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg));
                }
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fname=filename.getText().toString();
                        pname=plantname.getText().toString();
                        pcity=city.getText().toString();
                        if (fname.isEmpty()){
                            return;
                        }
                        if (pname.isEmpty()){
                            return;
                        }

                        if (pcity.isEmpty()){
                            return;
                        }

                        Intent intent = new Intent();
                        intent.setType( "application/msword");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select word"), PICK_WORD);

                    }
                });

                docx.setBackgroundResource(0);
                txt.setBackgroundResource(0);
                excel.setBackgroundResource(0);
                pdf.setBackgroundResource(0);
                image.setBackgroundResource(0);
            }
        });
        image = (LinearLayout) dialogView.findViewById (R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    image.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg) );
                } else {
                    image.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg));
                }
                filename.setVisibility(View.VISIBLE);
                plantname.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fname=filename.getText().toString();
                        pname=plantname.getText().toString();
                        pcity=city.getText().toString();
                        if (fname.isEmpty()){
                            return;
                        }
                        if (pname.isEmpty()){
                            return;
                        }

                        if (pcity.isEmpty()){
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, IMAGE_REQUEST);

                    }
                });


                docx.setBackgroundResource(0);
                txt.setBackgroundResource(0);
                word.setBackgroundResource(0);
                pdf.setBackgroundResource(0);
                excel.setBackgroundResource(0);

            }
        });


        docx = (LinearLayout)dialogView. findViewById (R.id.docx);
        docx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    docx.setBackgroundDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg) );
                } else {
                    docx.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.edittextbg));
                }
                filename.setVisibility(View.VISIBLE);
                plantname.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fname=filename.getText().toString();
                        pname=plantname.getText().toString();
                        pcity=city.getText().toString();
                        if (fname.isEmpty()){
                            return;
                        }
                        if (pname.isEmpty()){
                            return;
                        }

                        if (pcity.isEmpty()){
                            return;
                        }
                        Intent intent = new Intent();
                        intent.setType( "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select docx"), PICK_WORD);


                    }
                });

                pdf.setBackgroundResource(0);
                txt.setBackgroundResource(0);
                word.setBackgroundResource(0);
                excel.setBackgroundResource(0);
                image.setBackgroundResource(0);
            }
        });

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void admin() {
        setMargins(t1, 50, -12,0,0);
        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), ManageProjects.class);
                startActivity(intent);
            }
        });
        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), AddUsers.class);
                startActivity(intent);
            }
        });
        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), AllUsers.class);
                startActivity(intent);
            }
        });
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {

            if (requestCode==PICK_PDF_CODE){
                if (data.getData() != null) {
                    uploadFile(data.getData());
                }else{
                    Toast.makeText(requireActivity(), "No file chosen", LENGTH_SHORT).show();
                }
            }
            if (requestCode==PICK_TXT){
                if (data.getData() != null) {
                    Uri txt=data.getData();
                    uploadTXT(txt);
                }else{
                    Toast.makeText(requireActivity(), "No file chosen", LENGTH_SHORT).show();
                }
            }
            if (requestCode==PICK_WORD){
                if (data.getData() != null) {
                    Uri doc=data.getData();
                    uploadDOC(doc);
                }else{
                    Toast.makeText(requireActivity(), "No file chosen", LENGTH_SHORT).show();
                }
            }
            if (requestCode==PICK_XL){
                if (data.getData() != null) {
                    Uri excel=data.getData();
                    uploadXLS(excel);
                }else{
                    Toast.makeText(requireActivity(), "No file chosen", LENGTH_SHORT).show();
                }
            }
            if (requestCode==IMAGE_REQUEST){
                imageUri = data.getData();

                if (uploadTask != null && uploadTask.isInProgress()){
                    Toast.makeText(requireActivity(),"Upload in Progress!",Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage();
                }
            }
            if (requestCode==PICK_DOCX){
                if (data.getData() != null) {
                    Uri docx=data.getData();
                    uploadDOCX(docx);
                }else{
                    Toast.makeText(requireActivity(), "No file chosen", LENGTH_SHORT).show();
                }
            }

        }
    }
    private void uploadDOCX(Uri docx) {
        final ProgressDialog pd = new ProgressDialog(requireActivity());
        pd.setMessage("Uploading");
        pd.show();

        final StorageReference sRef = docxstorageReference.child(System.currentTimeMillis() + ".xls");
        sRef.putFile(docx)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                        sRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String docxUrl=task.getResult().toString();
                                Log.i("URL",docxUrl);
                                String a=filename.getText().toString();
                                pname=plantname.getText().toString();
                                pcity=city.getText().toString();
                                Attachfiles upload = new Attachfiles(a,pname,pcity,id,docxUrl);
                                String b=pname+"_"+pcity+"_"+currentdate;
                                docxDatabaseReference = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets").child(b).child("Attachedfiles");
                                docxDatabaseReference.child(Objects.requireNonNull(docxDatabaseReference.push().getKey())).setValue(upload);
                                pd.dismiss();
                                filename.setVisibility(View.GONE);
                                submit.setVisibility(View.GONE);
                                Snackbar.make(customlnr, "Successfully Uploaded", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(requireActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        pd.setMessage(("Uploading..."));
                    }
                });
    }

    private void uploadDOC(Uri doc) {
        final ProgressDialog pd = new ProgressDialog(requireActivity());
        pd.setMessage("Uploading");
        pd.show();

        final StorageReference sRef = docstorageReference.child(System.currentTimeMillis() + ".xls");
        sRef.putFile(doc)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                        sRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String docUrl=task.getResult().toString();
                                Log.i("URL",docUrl);
                                String a=filename.getText().toString();
                                pname=plantname.getText().toString();
                                pcity=city.getText().toString();
                                Attachfiles upload = new Attachfiles(a,pname,pcity,id,docUrl);
                                String b=pname+"_"+pcity+"_"+currentdate;
                                docDatabaseReference = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets").child(b).child("Attachedfiles");
                                docDatabaseReference.child(Objects.requireNonNull(docDatabaseReference.push().getKey())).setValue(upload);
                                pd.dismiss();
                                filename.setVisibility(View.GONE);
                                submit.setVisibility(View.GONE);
                                Snackbar.make(customlnr, "Successfully Uploaded", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(requireActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        pd.setMessage(("Uploading..."));
                    }
                });
    }

    private void uploadXLS(Uri excel) {
        final ProgressDialog pd = new ProgressDialog(requireActivity());
        pd.setMessage("Uploading");
        pd.show();

        final StorageReference sRef = xlsstorageReference.child(System.currentTimeMillis() + ".xls");
        sRef.putFile(excel)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                        sRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String xlsUrl=task.getResult().toString();
                                Log.i("URL",xlsUrl);
                                String a=filename.getText().toString();
                                pname=plantname.getText().toString();
                                pcity=city.getText().toString();
                                Attachfiles upload = new Attachfiles(a,pname,pcity,id,xlsUrl);
                                String b=pname+"_"+pcity+"_"+currentdate;
                                xlsDatabaseReference =  FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets").child(b).child("Attachedfiles");
                                xlsDatabaseReference.child(Objects.requireNonNull(xlsDatabaseReference.push().getKey())).setValue(upload);
                                pd.dismiss();
                                filename.setVisibility(View.GONE);
                                submit.setVisibility(View.GONE);
                                Snackbar.make(customlnr, "Successfully Uploaded", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(requireActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        pd.setMessage(("Uploading..."));
                    }
                });
    }

    private void uploadTXT(Uri txt) {
        final ProgressDialog pd = new ProgressDialog(requireActivity());
        pd.setMessage("Uploading");
        pd.show();

        final StorageReference sRef = txtstorageReference.child(System.currentTimeMillis() + ".txt");
        sRef.putFile(txt)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                        sRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String txtUrl=task.getResult().toString();
                                Log.i("URL",txtUrl);
                                String a=filename.getText().toString();
                                pname=plantname.getText().toString();
                                pcity=city.getText().toString();
                                Attachfiles upload = new Attachfiles(a,pname,pcity,id,txtUrl);
                                String b=pname+"_"+pcity+"_"+currentdate;
                                txtDatabaseReference = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets").child(b).child("Attachedfiles");
                                txtDatabaseReference.child(Objects.requireNonNull(txtDatabaseReference.push().getKey())).setValue(upload);
                                pd.dismiss();
                                filename.setVisibility(View.GONE);
                                submit.setVisibility(View.GONE);
                                Snackbar.make(customlnr, "Successfully Uploaded", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(requireActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        pd.setMessage(("Uploading..."));
                    }
                });


    }


    private void uploadImage() {

        final ProgressDialog pd = new ProgressDialog(requireActivity());
        pd.setMessage("Uploading");
        pd.show();

        if (imageUri != null){
            final  StorageReference fileReference = imgstorageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){

                        throw  task.getException();

                    }

                    return  fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = Objects.requireNonNull(downloadUri).toString();
                        fuser = FirebaseAuth.getInstance().getCurrentUser();

                        if (fuser!=null) {

                            String a=filename.getText().toString();
                            pname=plantname.getText().toString();
                            pcity=city.getText().toString();
                            Attachfiles upload = new Attachfiles(a,pname,pcity,id,mUri);
                            String b=pname+"_"+pcity+"_"+currentdate;
                            imgDatabaseRreference=FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets").child(b).child("Attachedfiles");
                            imgDatabaseRreference.child(Objects.requireNonNull( imgDatabaseRreference.push().getKey())).setValue(upload);

                            pd.dismiss();
                            filename.setVisibility(View.GONE);
                            submit.setVisibility(View.GONE);
                            Snackbar.make(customlnr, "Successfully Uploaded", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(requireActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(requireActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        } else {

            Toast.makeText(requireActivity(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = requireActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadFile(Uri data) {
        final ProgressDialog pd = new ProgressDialog(requireActivity());
        pd.setMessage("Uploading");
        pd.show();

        final StorageReference sRef = pdfStorageReference.child(System.currentTimeMillis() + ".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {


                        sRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String ImageUrl=task.getResult().toString();
                                Log.i("URL",ImageUrl);
                                String a=filename.getText().toString();
                                pname=plantname.getText().toString();
                                pcity=city.getText().toString();
                                Attachfiles upload = new Attachfiles(a,pname,pcity,id,ImageUrl);
                                String b=pname+"_"+pcity+"_"+currentdate;
                                pdfDatabaseReference = FirebaseDatabase.getInstance().getReference("PlantdetailsandAssets").child(b).child("Attachedfiles");
                                pdfDatabaseReference.child(Objects.requireNonNull(pdfDatabaseReference.push().getKey())).setValue(upload);
                                pd.dismiss();
                                filename.setVisibility(View.GONE);
                                submit.setVisibility(View.GONE);
                                Snackbar.make(customlnr, "Successfully Uploaded", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(requireActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        pd.setMessage(" Uploading...");
                    }
                });
    }
}
