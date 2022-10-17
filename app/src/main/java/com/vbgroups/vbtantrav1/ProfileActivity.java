package com.vbgroups.vbtantrav1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
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
import com.vbgroups.vbtantrav1.Model.User;

import java.util.HashMap;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {
    TextView name,phone,email,status,job,duration,id;
    ImageView dp;
    private FirebaseAuth auth;
    FirebaseUser fuser;
    Context mContext;
    DatabaseReference reference;
    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask<UploadTask.TaskSnapshot> uploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dp= findViewById(R.id.dp);
        name= findViewById(R.id.name);
        email=findViewById(R.id.email);
        job=findViewById(R.id.job);
        status=findViewById(R.id.status);
        phone=findViewById(R.id.mobile);
        duration=findViewById(R.id.duration);
        id=findViewById(R.id.id);

        auth = FirebaseAuth.getInstance();
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

                        name.setText(Objects.requireNonNull(user).getName());
                        phone.setText(Objects.requireNonNull(user).getPhone());
                        email.setText(Objects.requireNonNull(user).getEmail());
                        job.setText(Objects.requireNonNull(user).getJob());
                        id.setText(Objects.requireNonNull(user).getId());
                        status.setText(Objects.requireNonNull(user).getStatus());
                        duration.setText(Objects.requireNonNull(user).getDuration());
                        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                        String nam = preference.getString("page","");
                        if (!nam.isEmpty()) {
                            if (!nam.equals("SuperAdmin")) {

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("Name", user.getName().toUpperCase());
                                editor.putString("Phone", user.getPhone());
                                editor.putString("Email", user.getEmail());
                                editor.putString("Job", user.getJob());
                                editor.putString("userid", fuser.getUid());
                                editor.putString("status", user.getStatus());
                                editor.putString("duration", user.getDuration());
                                editor.apply();
                            }
                        }
                        if (user.getImageurl().equals("default")) {
                            dp.setImageResource(R.drawable.defaultpic);
                        } else {
                            if (!ProfileActivity.this.isFinishing()) {
                                Glide.with(dp.getContext()).load(user.getImageurl()).into(dp);

                            }
                        }

                    }reference = FirebaseDatabase.getInstance().getReference("SuperAdmin").child(fuser.getUid());
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
                                id.setText(fuser.getUid());

                                if (user.getImageurl().equals("default")) {
                                    dp.setImageResource(R.drawable.defaultpic);
                                } else {
                                    if (!ProfileActivity.this.isFinishing()) {
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

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        String nam = preferences.getString("page","");
        if (!nam.isEmpty()) {
            if (!nam.equals("SuperAdmin")) {

                SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                name.setText(preference.getString("Name", ""));
                phone.setText(preference.getString("Phone", ""));
                email.setText(preference.getString("Email", ""));
                job.setText(preference.getString("Job", ""));

                status.setText(preference.getString("status", ""));
                id.setText(preference.getString("Userid", ""));
                duration.setText(preference.getString("duration", ""));
            }
        }
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });
        id.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                assert cm != null;
                Objects.requireNonNull(cm).setText(id.getText().toString());
                Toast.makeText(getApplicationContext(), "Copied :)", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
        private void openImage() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                    && data != null && data.getData() != null){
                imageUri = data.getData();

                if (uploadTask != null && uploadTask.isInProgress()){
                    Toast.makeText(ProfileActivity.this,"Upload in Progress!",Toast.LENGTH_SHORT).show();
                } else {

                    uploadImage();
                }
            }
        }

        private void uploadImage() {
            final ProgressDialog pd = new ProgressDialog(ProfileActivity.this);
            pd.setMessage("Uploading");
            pd.show();

            if (imageUri != null){
                storageReference = FirebaseStorage.getInstance().getReference("Profile Pictures");

                final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                        +"."+getFileExtension(imageUri));

                uploadTask = fileReference.putFile(imageUri);
                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){

                            throw Objects.requireNonNull(task.getException());

                        }

                        return  fileReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            Uri downloadUri = task.getResult();
                            String mUri = Objects.requireNonNull(downloadUri).toString();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
                            String nam = preferences.getString("page","");
                            if (!nam.isEmpty()) {
                                if (!nam.equals("SuperAdmin")) {
                                    reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                                }else {
                                    reference = FirebaseDatabase.getInstance().getReference("SuperAdmin").child(fuser.getUid());
                                }
                            }

                            HashMap<String, Object> map = new HashMap<>();
                            map.put("imageurl", ""+mUri);
                            reference.updateChildren(map);

                            pd.dismiss();

                        } else {
                            Toast.makeText(ProfileActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });
            } else {

                Toast.makeText(ProfileActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
            }
        }
        private String getFileExtension(Uri uri){
            ContentResolver contentResolver = getApplicationContext().getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
        }
}
