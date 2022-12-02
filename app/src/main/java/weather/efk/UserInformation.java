package weather.efk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInformation extends AppCompatActivity {

    private FirebaseUser Fuser;
    FirebaseAuth my_auth;
    FirebaseDatabase database;
    private DatabaseReference mref;

    private EditText name_text, username_text, mail_text, password_text;
    private Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        name_text = findViewById(R.id.name_text);
        username_text = findViewById(R.id.username_text);
        mail_text = findViewById(R.id.mail_text);
        password_text = findViewById(R.id.password_text);
        save_button = findViewById(R.id.save_button);

        Fuser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = Fuser.getUid();

        my_auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mref = database.getReference();

        if (Fuser != null) {
            // Name, email address, and profile photo Url
            String name = Fuser.getDisplayName();
            String email = Fuser.getEmail();

            // Check if user's email is verified
            boolean emailVerified = Fuser.isEmailVerified();

            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    User user1 = dataSnapshot.getValue(User.class);
                    String mailAdd = user1.getEmail();
                    String fullName = user1.getFullname();
                    String userName = user1.getUsername();

                    name_text.setText(fullName);
                    username_text.setText(userName);
                    mail_text.setText(mailAdd);

                    // ..
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Toast.makeText(UserInformation.this, "Could Not Get User Information!", Toast.LENGTH_SHORT).show();
                }
            };
            mref.child("users").child(uid).addValueEventListener(postListener);
/*
            mref.child("users").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()){
                        if (task.getResult().exists()){
                            Toast.makeText(UserInformation.this, "User Found Successfully.", Toast.LENGTH_SHORT).show();
                            DataSnapshot dataSnapshot= task.getResult();

                        }else {
                            Toast.makeText(UserInformation.this, "User Does Not Exist.", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(UserInformation.this, "Failed To Read Data.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
*/

        }

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = String.valueOf(username_text.getText());
                String email1 = String.valueOf(mail_text.getText());
                String name1 = String.valueOf(name_text.getText());
                String password = password_text.getText().toString();

                User user = new User(username1,email1,name1);

                if (!(TextUtils.isEmpty(password))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserInformation.this);
                    builder.setMessage("Do you want to change your password ?");
                    builder.setTitle("Confirmation!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        mref.child("users").child(uid).setValue(user);
                        Fuser.updatePassword(password)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(UserInformation.this, "User password updated.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    });
                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // If user click no then dialog box is canceled.
                        dialog.cancel();
                    });

                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                }else{
                    mref.child("users").child(uid).setValue(user);
                    Toast.makeText(getApplicationContext(),"User Details Saved!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}