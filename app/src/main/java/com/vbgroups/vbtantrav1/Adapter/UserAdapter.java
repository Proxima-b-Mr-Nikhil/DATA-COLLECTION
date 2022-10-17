package com.vbgroups.vbtantrav1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.vbgroups.vbtantrav1.Fragments.ChatsFragment;
import com.vbgroups.vbtantrav1.Fragments.Main2Activity;
import com.vbgroups.vbtantrav1.Fragments.MessageActivity;
import com.vbgroups.vbtantrav1.Fragments.ProfileFragment;
import com.vbgroups.vbtantrav1.Fragments.UsersFragment;
import com.vbgroups.vbtantrav1.Model.Chat;
import com.vbgroups.vbtantrav1.Model.User;
import com.vbgroups.vbtantrav1.R;

import java.util.List;
import java.util.Objects;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;
    private boolean ischat;

    String theLastMessage;
    private int unread =0;
    private int count =0;

    public UserAdapter(Context mContext, List<User> mUsers, boolean ischat){
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.ischat = ischat;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final User user = mUsers.get(position);
        holder.username.setText(user.getName());

        if (user.getImageurl().equals("default")){
            holder.profile_image.setImageResource(R.drawable.defaultpic);
        } else {
            Glide.with(mContext).load(user.getImageurl()).into(holder.profile_image);
        }
        if (ischat){
            lastMessage(user.getId(), holder.last_msg);

        } else {
            holder.last_msg.setVisibility(View.GONE);
        }

            DatabaseReference  reference = FirebaseDatabase.getInstance().getReference("Chats");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        FirebaseUser firebaseUser;
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        Chat chat = snapshot.getValue(Chat.class);
                        assert firebaseUser != null;

                        if (Objects.requireNonNull(chat).getReceiver().equals(firebaseUser.getUid()) && !chat.isIsseen()&&chat.getSender().equals(user.getId())){
                            unread++;
                            holder.notificationbadge.setVisibility(View.VISIBLE);
                            holder.notificationbadge.setText("" + unread + "");
                        }
                        if (holder.notificationbadge.getVisibility()==View.VISIBLE){
                            count++;
                        }
                    }
                    unread=0;

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;

        public ImageView profile_image;
        private ImageView img_on;
        private ImageView img_off;
        private TextView last_msg;
        private TextView notificationbadge;

        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            notificationbadge = itemView.findViewById(R.id.badge);
            profile_image = itemView.findViewById(R.id.profile_image);
            img_on = itemView.findViewById(R.id.img_on);
            img_off = itemView.findViewById(R.id.img_off);
            last_msg = itemView.findViewById(R.id.last_msg);
            img_on.setVisibility(View.GONE);
            img_off.setVisibility(View.GONE);
        }
    }

    //check for last message
    private void lastMessage(final String userid, final TextView last_msg){
        theLastMessage = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                   if (firebaseUser != null && chat != null) {
                        if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid) ||
                                chat.getReceiver().equals(userid) && chat.getSender().equals(firebaseUser.getUid())) {
                            theLastMessage = chat.getMessage();
                        }
                    }
                }

                switch (theLastMessage){
                    case  "default":
                        last_msg.setText("No Message");
                        break;

                    default:
                        last_msg.setText(theLastMessage);
                        break;
                }

                theLastMessage = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
