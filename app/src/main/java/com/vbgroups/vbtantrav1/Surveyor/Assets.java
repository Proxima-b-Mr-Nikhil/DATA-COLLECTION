package com.vbgroups.vbtantrav1.Surveyor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.vbgroups.vbtantrav1.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Assets extends AppCompatActivity {

    Button Submit,add;
    CheckBox checkbox;
    EditText Transformer_Name,Model,Rating;
    String STransformer_Name,SModel,SRating,img1;
    LinearLayout AddImage,linearLayout;
    Uri imageUri;
    TextView txtimg;
    ProgressDialog pd;
    DatabaseReference reference,ref,re;
    FirebaseAuth auth;
    FirebaseUser fuser;
    StorageReference storageReference;
    private StorageTask<UploadTask.TaskSnapshot> uploadTask;


    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageView ivImage;
    private String userChoosenTask;
    Uri a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_assests);

        Submit=findViewById(R.id.submit);
        checkbox=findViewById(R.id.check);
        Transformer_Name=findViewById(R.id.transformer_Name);
        Model=findViewById(R.id.model);
        Rating=findViewById(R.id.rating);
        ivImage=findViewById(R.id.image);
        add=findViewById(R.id.add);
        AddImage=findViewById(R.id.add_image_layout);
        txtimg=findViewById(R.id.txtimage);
        linearLayout=findViewById(R.id.linear);
        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               seletImage();
            }
        });



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkbox.isChecked()){
                    STransformer_Name = Transformer_Name.getText().toString();
                    SModel = Model.getText().toString();
                    SRating = Rating.getText().toString();

                    if (STransformer_Name.isEmpty()) {
                        Transformer_Name.setError("Enter Transformer Name");
                        return;
                    }
                    if (SModel.isEmpty()) {

                        Model.setError("This Field Cannot be Empty");
                        return;
                    }
                    if (SRating.isEmpty()) {

                        Rating.setError("This Field Cannot be Empty");
                        return;
                    }
                    if (txtimg.equals("")){
                        return;
                    }
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Assets.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("button", "submit");
                    editor.apply();
                    upload();

                }else {
                    Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkbox.isChecked()){
                    STransformer_Name = Transformer_Name.getText().toString();
                    SModel = Model.getText().toString();
                    SRating = Rating.getText().toString();

                    if (STransformer_Name.isEmpty()) {
                        Transformer_Name.setError("Enter Transformer Name");
                        return;
                    }
                    if (SModel.isEmpty()) {

                        Model.setError("This Field Cannot be Empty");
                        return;
                    }
                    if (SRating.isEmpty()) {

                        Rating.setError("This Field Cannot be Empty");
                        return;
                    }
                    if (txtimg.equals("")){
                        return;
                    }
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Assets.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("button", "add");
                    editor.apply();

                    upload();

                }else {
                    Snackbar.make(linearLayout, "Please allow the check box to proceed", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    private void upload() {


        storageReference = FirebaseStorage.getInstance().getReference("Assests images");

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        if (fuser!=null) {
            pd = new ProgressDialog(Assets.this);
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
            re=FirebaseDatabase.getInstance().getReference("Users").child(userid).child("projects");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                    Intent intent = getIntent();
                    String a = intent.getStringExtra("Plant name");
                    String b = intent.getStringExtra("Plant city");
                    String c = intent.getStringExtra("Plant visit date");
                    final String d = intent.getStringExtra("Plant type");
                    String e = intent.getStringExtra("latitude");
                    String f = intent.getStringExtra("longitude");
                    String g = intent.getStringExtra("Purpose of visit");
                    String h = intent.getStringExtra("Ref name");
                    String i = intent.getStringExtra("Ref phone");
                    final String j=a+"_"+b+"_"+c;

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

                    re.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            snapshot.getRef().child(j).setValue(j);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            dataSnapshot.getRef().child("transname").setValue(STransformer_Name);
                            dataSnapshot.getRef().child("model").setValue(SModel);
                            dataSnapshot.getRef().child("rating").setValue(SRating);
                            dataSnapshot.getRef().child("uploaderid").setValue(userid);

                            storageReference = FirebaseStorage.getInstance().getReference("Assets");
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Assets.this);
                            Uri ur = Uri.parse(preferences.getString("uri",""));

                            if (ur != null) {
                                final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                                        + "." + getFileExtension(ur));
                                uploadTask = fileReference.putFile(ur);
                                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                        if (!task.isSuccessful()) {

                                            pd.dismiss();
                                            Snackbar.make(linearLayout, "failed", Snackbar.LENGTH_LONG).show();

                                            throw Objects.requireNonNull(task.getException());

                                        }

                                        return fileReference.getDownloadUrl();
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
                                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Assets.this);
                                            String name = preferences.getString("button","");
                                            if (!name.isEmpty()) {
                                                if (name.equals("add")) {
                                                    Intent intent=getIntent();
                                                    startActivity(intent);
                                                }else {
                                                    Intent intent=new Intent(Assets.this,NewProject.class);
                                                    startActivity(intent);
                                                }
                                            }

                                            Snackbar.make(linearLayout, "Uploaded successfully", Snackbar.LENGTH_LONG).show();

                                        } else {
                                            Toast.makeText(Assets.this, "Failed!", Toast.LENGTH_SHORT).show();
                                            pd.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Assets.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void seletImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Assets.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(Assets.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
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


        Bitmap thumbnail = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        if (thumbnail != null) {
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

            String a=System.currentTimeMillis()+".jpg";
            File destination = new File(Environment.getExternalStorageDirectory()+ File.separator + " VB Tantra Assets", a);
            txtimg.setText(a);


            ivImage.setVisibility(View.VISIBLE);
            ivImage.setImageBitmap(thumbnail);
            b = getImageUri(getApplicationContext(), thumbnail);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Assets.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("uri", String.valueOf(b));
            editor.apply();
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Assets.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("uri", String.valueOf(a));
        editor.apply();
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage,"" , null);
        return Uri.parse(path);
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    @Override
    public void onBackPressed() {
        Intent intent =new Intent(getApplicationContext(),NewProject.class);
        startActivity(intent);
    }
}
