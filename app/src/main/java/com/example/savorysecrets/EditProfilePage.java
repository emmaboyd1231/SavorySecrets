package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

public class EditProfilePage extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Button updateProfileButton;
    private TextInputEditText editFirstName;
    private TextInputEditText editLastName;
    private TextInputEditText editEmail;
    private TextInputEditText editPhoneNumber;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    String user_email, first_name, last_name, phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_page);

        updateProfileButton=findViewById(R.id.updateProfileButton);
        editFirstName=findViewById(R.id.editFirstName);
        editLastName=findViewById(R.id.editLastName);
        editEmail=findViewById(R.id.editEmail);
        editPhoneNumber=findViewById(R.id.editPhoneNumber);

        //reference = FirebaseDatabase.getInstance().getReference().child("users");
        //user = FirebaseAuth.getInstance().getCurrentUser();

        //rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users").child(firebaseAuth.getCurrentUser().getUid());

        /*reference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user_email = dataSnapshot.child("user_email").getValue(String.class);
                first_name = dataSnapshot.child("first_name").getValue(String.class);
                last_name = dataSnapshot.child("last_name").getValue(String.class);
                phone_number = dataSnapshot.child("phone_number").getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/
    }
}