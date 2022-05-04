package com.example.savorysecrets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savorysecrets.databinding.FragmentNotificationsBinding;
import com.example.savorysecrets.ui.notifications.NotificationsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class ProfilePage extends AppCompatActivity {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private TextView firstName, lastName, username, emailAddress, phoneNumber, password;

    FirebaseAuth fAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase rootNode;
    StorageReference storageReference;
    Dialog dialog;
    UserHelperClass user;
    FirebaseUser firebaseUser;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        //binding = FragmentNotificationsBinding.inflate(getLayoutInflater());
        //setContentView(binding.root);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getCurrentUser().getUid();

        if(!uid.isEmpty()){
            getUserData();
        }

        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        username=findViewById(R.id.username);
        emailAddress=findViewById(R.id.emailAddress);
        phoneNumber=findViewById(R.id.phoneNumber);
        password=findViewById(R.id.password);

    }

    private void getUserData() {
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(UserHelperClass.class);
                firstName.setText(user.first_name);
                lastName.setText(user.last_name);
                username.setText(user.user_username);
                emailAddress.setText(user.user_email);
                phoneNumber.setText(user.phone_number);
                password.setText(user.user_password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilePage.this, "Failed to get user profile data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}